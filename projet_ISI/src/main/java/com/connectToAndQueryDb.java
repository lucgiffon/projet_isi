package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class connectToAndQueryDb {

	private static Connection con = null;
	private static Statement stmt = null;
	private static ResultSet rs = null;
	
	public static void main(String[] args) 
		throws SQLException {
		
		String url = "jdbc:mysql://localhost:3306/world";
		String username = "root";
		String password = "";
		
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			con = DriverManager.getConnection(url, username, password);
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM City;");
			
			System.out.println("*\tID\t*\tName\t*\tCountryCode\t*");
			while (rs.next()) {
				int x = rs.getInt("ID");
				String s = rs.getString("Name");
				String f = rs.getString("CountryCode");
				System.out.println("*\t" + x + "\t*\t" + s + "\t*\t" + f + "\t*");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}

}
