package com.ss.utopia.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
	public Connection getConnection() throws ClassNotFoundException, SQLException { //add validation dont throw exceptions in dao
		Class.forName(getProperty("driver"));
		Connection conn =  DriverManager.getConnection(getProperty("url"), getProperty("username"), getProperty("password"));
		conn.setAutoCommit(false);
		return conn;
	}
	
	public String getProperty(String propertyName) {
		try (InputStream input = new FileInputStream("resources/db.properties")) {
			Properties prop = new Properties();
			prop.load(input);
			return prop.getProperty(propertyName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
