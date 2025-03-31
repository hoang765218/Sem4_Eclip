package com.mytech.shopmgmt.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
	static String jdbcURL = "jdbc:mysql://localhost:3306/sem4_shop?useSSL=false";
	static String jdbcUsername = "hoang";
	static String jdbcPassword = "12345678";
	
	public static Connection getConnection() {
		Connection connection = null; ;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
