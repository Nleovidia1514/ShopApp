package com.labc.A3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnManager {
	public static Connection connection = null;
	
	public static Connection getConnection() {
		if(connection == null) {
			try
			{
				Class.forName("org.postgresql.Driver");
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
				System.out.println("You must have JDBC Driver installed in the JVM.");
				return null;
			}
			try
			{
				connection = DriverManager.getConnection("jdbc:postgresql:testing", "postgres", "masterkey");
			}catch(SQLException e) {
				System.out.println("Connection failed!");
				e.printStackTrace();
				return null;
			}
			if(connection!=null) {
				System.out.println("Connection successful!");
				return connection;
			}
			else
				System.out.println("Connection failed!");
		}
		else
			return connection;
		return null;
	}
}
