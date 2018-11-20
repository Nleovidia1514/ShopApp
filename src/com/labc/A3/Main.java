package com.labc.A3;

import java.text.SimpleDateFormat;
import java.util.Random;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Main {
	public static Random rnd = new Random();
	public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy HH:mm:ss");
	public static JTextField employeeID;
	public static JTextField cedulaID;
	public static JTextField clientName;
	public static JTextArea clientAdr;
	
	public static void main(String[] args) {
		ConnManager.getConnection();
		new UserInterface().frame.setVisible(true);
	}

}
