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
import javax.swing.table.DefaultTableModel;

public class Main {
	public static Random rnd = new Random();
	public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy HH:mm:ss");
	public static JTextField employeeID;
	public static JTextField cedulaID;
	public static JTextField clientName;
	public static JTextArea clientAdr;
	public static JComboBox<String> idType;
	public static JFrame frame;
	public static DefaultTableModel productsArea;
	public static JTextPane totalPane;
	public static double total = 0;
	public static JButton CRUD;
	public static JPanel adminpanel;
	
	public static void main(String[] args) {
		startAll();
	}
	
	private static void startAll() {
		new Store().start();
		new UserInterface().start();
	}
}
