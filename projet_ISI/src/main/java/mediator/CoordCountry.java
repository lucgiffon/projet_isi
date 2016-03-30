package mediator;

import java.sql.SQLException;
import java.sql.Statement;

public class CoordCountry {
	
	String codeCountry;
	Double longitude;
	Double latitude;
	String countryName;
	String tableName;
	
	public CoordCountry(String codeCountry, String countryName, Double longitude, Double latitude, String tableName) {
		this.codeCountry = codeCountry;
		this.countryName = countryName;
		this.longitude = longitude;
		this.latitude = latitude;
		this.tableName = tableName;
	}
	
	public void insert(Statement statement) throws SQLException {
		// TODO heritage
		String sql;
		
		sql = "INSERT INTO Tmp" + tableName + " VALUES ('" + codeCountry + "', \"" + countryName + "\", " + longitude + ", " + latitude + ");" ;
		System.out.print("[QUERY] " + sql);
		statement.executeUpdate(sql);
		System.out.println(" -> Done.");
	}
}
