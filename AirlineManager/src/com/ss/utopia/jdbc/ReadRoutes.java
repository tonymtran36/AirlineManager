package com.ss.utopia.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReadRoutes {
	
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/utopia";
	private static final String username = "root";
	private static final String password = "Tonytran6474";

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//1. Register the driver (MySQL)
		Class.forName(driver);
		//2. Connection
		Connection conn = DriverManager.getConnection(url, username, password);
		//3. Statement
		Statement stmt = conn.createStatement();

		String query2 = "select * from Flight";
		//4. Execution.
		ResultSet rs2 = stmt.executeQuery(query2);
		while(rs2.next()) {
			System.out.println("Flight id: "+rs2.getInt("id"));
			System.out.println("flight: "+rs2.getString("route_id"));			
		}
	}

}