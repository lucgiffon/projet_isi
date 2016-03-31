package mediator;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;

public class Mediator {

	private MainDataWrapper[] dataWrappers= new MainDataWrapper[3];
	private CountryWrapper countryWrapper = null;
	private Connection connection;
	private Statement statement;
	private String[] tableNames;
	
	public Mediator(Connection connection, String[] tableNames) throws SQLException {
		// TODO Auto-generated method stub
		this.tableNames = tableNames;
		this.connection = connection;
		emptyTmpTables();
		for (int i = 0; i < dataWrappers.length; i++) {
			dataWrappers[i] = new MainDataWrapper(tableNames[i], connection);
		}
		countryWrapper = new CountryWrapper("CoordCountries", connection);		
	}

	public void setupDB(Hashtable<String, Integer> dates, ArrayList<String> pays) throws SQLException {
		emptyTmpTables();
		for (MainDataWrapper dataWrapper: dataWrappers) {
			dataWrapper.fillTmpTable(dates.get(dataWrapper.getTableName()), pays);
		}		
	}

	private void emptyTmpTables() throws SQLException {
		String sql;
		try {
			for (String tableName: tableNames) {
				
					statement = connection.createStatement();
					sql = "DELETE FROM Tmp" + tableName + ";";
					System.out.print("[QUERY] " + sql);
					statement.executeUpdate(sql);
					System.out.println(" -> Done.");
				
			}
			sql = "DELETE FROM TmpValues;";
			System.out.print("[QUERY] " + sql);
			statement.executeUpdate(sql);
			System.out.println(" -> Done.");
		} catch (SQLException e) {
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

}
