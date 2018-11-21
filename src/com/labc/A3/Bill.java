package com.labc.A3;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Random;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Bill {
	private Connection connection = ConnManager.getConnection();
	private Random rnd = Main.rnd;
	private SimpleDateFormat sdf = Main.sdf;
	private JTextField employeeID = Main.employeeID;
	private JTextField cedulaID = Main.cedulaID;
	private JComboBox idType = Main.idType;
	public long billNmbr = 0;
	public static Client currentClient = null;
	
	public Bill() {
		Statement stm = null;
		this.billNmbr = rnd.nextInt(200);
		try {
			String query = String.format("Insert into Bill Values (%d,'%s','%s%s',%d,0,0)",
					billNmbr,sdf.format(System.currentTimeMillis()),idType.getSelectedItem(),cedulaID.getText(),Long.valueOf(employeeID.getText()));
			stm = connection.createStatement();
			stm.execute(query);
			System.out.println("Bill created successfully!");
		}catch(Exception e) {
			JOptionPane.showMessageDialog(Main.frame, e, "Error", JOptionPane.ERROR_MESSAGE);
		}finally {
	        if (stm != null) { 
	        	try {stm.close();}catch (SQLException e) {e.printStackTrace();} 
	        }
		}
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
