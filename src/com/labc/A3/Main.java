package com.labc.A3;

import java.text.SimpleDateFormat;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class Main {
	public static Random rnd = new Random();
	public static SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyy HH:mm:ss");
	public static JTextField employeeID;
	public static JTextField cedulaID;
	public static JTextField clientName;
	public static JTextArea clientAdr;
	public static JComboBox idType;
	public static JFrame frame;
	public static JTextArea productsArea;
	public static JTextPane totalPane;
	public static double total = 0;
	public static JButton CRUD;
	public static JPanel adminpanel;
	
	public static void main(String[] args) {
		ConnManager.getConnection();
		innitStuff();
		frame = new UserInterface();
		frame.setVisible(true);
	}
	
	private static void innitStuff(){
		Provider.innitProviders();
		Client.innitClients();
		Product.innitProducts();
		Ocupation.innitOcupations();
		Employee.innitEmployees();
	}

}
