package mediator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DateCountryValue {
	
	private String countryCode;
	private Double value;
	private int date;
	private String tableName;
	
	public DateCountryValue(String countryCode, Double value, int date, String tableName) {
		this.countryCode = countryCode;
		this.value = value;
		this.date = date;
		this.tableName = tableName;
	}

	public void insert(Statement statement) throws SQLException {
		String sql;
		
		sql = "INSERT INTO TmpValues (date, value) VALUES (" + date + ", " + value + ");" ;
		System.out.print("[QUERY] " + sql);
		statement.executeUpdate(sql);
		System.out.println(" -> Done.");
		
		sql = "SELECT id FROM TmpValues ORDER BY id DESC LIMIT 1;";
		System.out.print("[QUERY] " + sql);
		ResultSet resultSet = statement.executeQuery(sql);
		resultSet.next();
		int lastAddedId = resultSet.getInt("id");
		System.out.println(" -> Done.");
		
		sql = "INSERT INTO Tmp" + tableName + " VALUES ('" + countryCode + "', " + lastAddedId + ");" ;
		System.out.print("[QUERY] " + sql);
		statement.executeUpdate(sql);
		System.out.println(" -> Done.");
	}
}
