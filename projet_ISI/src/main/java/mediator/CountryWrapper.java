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

public class CountryWrapper {
	
	private String csvFile;
	private String tableName;
	private Connection connection;
	private Statement statement;
	
	public CountryWrapper(String table, Connection connection) throws SQLException {
		this.tableName = table;
        File jar= new File(CountryWrapper.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        this.csvFile = jar.getParent() + "/classes/sources/" + table + ".csv";
        this.connection = connection; 
        fillTmpTable();
	}
	
	private ArrayList<CoordCountry> getCSVContent() {
		ArrayList<CoordCountry> values = new ArrayList<CoordCountry>();
    	BufferedReader br = null;
    	String line = "";
    	String cvsSplitBy = ";";
    	String [] splittedLine = null;
    	int lineIndex = 1;    	
    	try {

    		br = new BufferedReader(new FileReader(csvFile));
    		while ((line = br.readLine()) != null) {

				splittedLine = line.split(cvsSplitBy);
	    			try {
	    				values.add(new CoordCountry(splittedLine[0], splittedLine[1], Double.parseDouble(splittedLine[2]), Double.parseDouble(splittedLine[3]), tableName));
	    			}
	    			catch (NumberFormatException e) {
	    				System.out.println("[WARNING] Un nombre est mal formaté ou absent dans la table " + tableName + ": " + (lineIndex + 1));
	    			}
				}
    			lineIndex++;
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
	
	private void fillTmpTable() throws SQLException {
		emptyTmpTable();
		ArrayList<CoordCountry> values;		
		statement = connection.createStatement();
		values = getCSVContent();
		for (CoordCountry v: values) {
			v.insert(statement);
		}
		if (statement != null) {
			statement.close();
		}
	}
	
	private void emptyTmpTable() throws SQLException {
		// TODO héritage
		statement = connection.createStatement();
		String sql = "DELETE FROM Tmp" + tableName + ";";
		System.out.print("[QUERY] " + sql);
		statement.executeUpdate(sql);
		System.out.println(" -> Done.");
		if (statement != null) {
			statement.close();
		}
	}
}
