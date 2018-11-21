package com.labc.A3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client {
	private static Connection connection = ConnManager.getConnection();
	private static JTextField cedulaID = Main.cedulaID;
	private static JTextArea clientAdr = Main.clientAdr;
	private static JTextField clientName = Main.clientName;
	private static JComboBox<String> idType = Main.idType;
	private Bill bill;

	public Client(String action) {
		Statement stm = null;
		String query;
		if(connection!=null ) {
			if(!cedulaID.getText().equals("")) {
				if(!Client.getClient()) {
					if(!clientName.getText().equals("") && !clientAdr.getText().equals("")) {	
						query = String.format("Insert into Client values('%s%d','%s','%s',0);",
							idType.getSelectedItem(),Long.valueOf(cedulaID.getText()),clientName.getText(),clientAdr.getText()) ;
						try
						{
							System.out.println("hey");
							stm = connection.createStatement();
							stm.execute(query);
							cedulaID.setEditable(false);
							clientName.setEditable(false);
							clientAdr.setEditable(false);
							bill = new Bill();
						}catch(SQLException e) {
							System.out.println("SQL syntax error!");
							e.printStackTrace();
						}finally {
							 try {stm.close();} catch (SQLException e) {e.printStackTrace();} 
						}
					}
					else if(action.equals("cust"))
						JOptionPane.showMessageDialog(Main.frame, "Missing an obligatory field.", "Error", JOptionPane.WARNING_MESSAGE);
					else
						JOptionPane.showMessageDialog(Main.frame, "Client does not exist.","Error", JOptionPane.ERROR_MESSAGE);
				}
				else {
					bill = new Bill();
				}
			}
			else
				JOptionPane.showMessageDialog(Main.frame, "Missing an obligatory field.","Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public Bill getBill() {
		return this.bill;
	}
	
	public static boolean getClient() {
		Statement stm = null;
		String query = String.format("Select * from Client where IdClient = '%s%d'",idType.getSelectedItem(),Long.valueOf(cedulaID.getText()));
		try
		{
			stm = connection.createStatement();
			ResultSet rs = stm.executeQuery(query);
			if(rs.next()) {
				clientName.setText(rs.getString("NameC"));
				clientAdr.setText(rs.getString("AdressC"));
				clientName.setEditable(false);
				clientAdr.setEditable(false);
				cedulaID.setEditable(false);
				return true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try{stm.close();}catch (SQLException e) {e.printStackTrace();}
		}
		return false;
	}
	
	
}
