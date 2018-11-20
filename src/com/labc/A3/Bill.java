package com.labc.A3;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Random;

import javax.swing.JTextField;

public class Bill {
	private Connection connection = ConnManager.getConnection();
	private Random rnd = Main.rnd;
	private SimpleDateFormat sdf = Main.sdf;
	private JTextField employeeID = Main.employeeID;
	private JTextField cedulaID = Main.cedulaID;
	
	public Bill() {
		Statement stm = null;
		int billNmbr = rnd.nextInt(100);
		String query = "Insert into Factura values("+billNmbr+",'"+sdf.format(System.currentTimeMillis()
				+"',"+cedulaID.getText()+","+employeeID.getText()+",0,0");
		try
		{
			stm = connection.createStatement();
			stm.execute(query);
			System.out.println("Bill created successfully!");
		}catch(SQLException e) {
			System.out.println(e);
		}finally {
	        if (stm != null) { try {
	        	System.out.println("Closing statement");
				stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} }
	    
	}
	
	/*public static void generateBill() {
		Statement stm;
		String query = "INSERT INTO Detalle VALUES()"
	}
	
	public static void addProducts(long productID) {
		Statement stm;
		String query = ""
	}*/
	}
	
}
