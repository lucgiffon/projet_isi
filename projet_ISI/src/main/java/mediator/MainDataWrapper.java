package mediator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import utils.CsvUtils;

public class MainDataWrapper {
	
	private String csvFile;
	private String tableName;
	private Connection connection;
	private Statement statement;
	
	public MainDataWrapper(String table, Connection connection) {
		this.tableName = table;
        File jar= new File(MainDataWrapper.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        this.csvFile = jar.getParent() + "/classes/sources/" + table + ".csv";
        this.connection = connection;
	}
	
	private ArrayList<DateCountryValue> getCSVContent(int date, ArrayList<String> pays) {
		
		ArrayList<DateCountryValue> values = new ArrayList<DateCountryValue>();
    	BufferedReader br = null;
    	String line = "";
    	String cvsSplitBy = ";";
    	String [] splittedLine = null;
    	int dateIndex = -1;
    	int lineIndex = 1;
    	try {
    		br = new BufferedReader(new FileReader(csvFile));
    		if ((line = br.readLine()) != null) {
    			splittedLine = line.split(cvsSplitBy);
    			dateIndex = CsvUtils.indexOf(Integer.toString(date), splittedLine);
    		}
    		while ((line = br.readLine()) != null && dateIndex != -1) {
				splittedLine = line.split(cvsSplitBy);
				if (pays.contains(splittedLine[1])) {
	    			try {
	    				values.add(new DateCountryValue(splittedLine[1], Double.parseDouble(splittedLine[dateIndex].replace(',', '.')), date, tableName));
	    			}
	    			catch (IndexOutOfBoundsException e) {
	    				System.out.println("[WARNING] Une ligne dans la table " + tableName + " ne contenait pas l'information demandée: " + (lineIndex + 1));
	    			}
	    			catch (NumberFormatException e) {
	    				System.out.println("[WARNING] Un nombre est mal formaté ou absent dans la table " + tableName + ": " + (lineIndex + 1) + " -> \"" + splittedLine[dateIndex] + "\"");
	    			}
				}
    			lineIndex++;
    		}

    	} catch (FileNotFoundException e) {
    		e.printStackTrace();
    	} catch (IOException e) {
    		e.printStackTrace();
    	} finally {
    		if (br != null) {
    			try {
    				br.close();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		}
    	}
    	return values;
	}
	
	public void fillTmpTable(int date, ArrayList<String> pays) {
		ArrayList<DateCountryValue> values;
		try {
			statement = connection.createStatement();
			values = getCSVContent(date, pays);
			for (DateCountryValue v: values) {
				v.insert(statement);
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public String getTableName() {
		return tableName;
	}
}
