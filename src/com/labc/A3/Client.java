package com.labc.A3;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Random;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client {
	private static Connection connection = ConnManager.getConnection();
	private static Random rnd = Main.rnd;
	private static SimpleDateFormat sdf = Main.sdf;
	private static JTextField employeeID = Main.employeeID;
	private static JTextField cedulaID = Main.cedulaID;
	private static JTextArea clientAdr = Main.clientAdr;
	private static JTextField clientName = Main.clientName;

	public Client() {
		Statement stm = null;
		String query = "INSERT INTO Client VALUES("+cedulaID.getText()+",'"+clientName.getText()+"','"
				+clientAdr.getText()+"',0)";
		if(connection!=null) {
			try
			{
				stm = connection.createStatement();
				stm.execute(query);
			}catch(SQLException e) {
				System.out.println("SQL syntax error!");
				e.printStackTrace();
			}finally {
				 try {
			        	System.out.println("Closing statement");
						stm.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
			}
		}
	}
	
	public static void getClient() {
		Statement stm = null;
		String query = "SELECT * FROM Client WHERE idClient = "+cedulaID.getText();
		try
		{
			stm = connection.createStatement();
			ResultSet rs = stm.executeQuery(query);
			while(rs.next()) {
				clientName.setText(rs.getString("NombreC"));
				clientAdr.setText(rs.getString("DireccionC"));
				clientName.setEditable(false);
				clientAdr.setEditable(false);
				cedulaID.setEditable(false);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try
			{
				stm.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
