package com.labc.A3;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JScrollBar;

public class UserInterface extends JFrame {

	public JFrame frame = this;
	private JTabbedPane tabbedPane;
	private JPanel contentPane, panel;
	private JTextField cedulaID, idProductTF, employeeIDTF, clientNameTF;
	private JScrollBar scrollBar;
	private JComboBox comboBox;
	private JTextArea productsTA, adressTF, textArea_2, textArea_3, idProductTA, employeeIDTA, nameTA;
	private JButton billButton, resetButton, clearButton, addCustButton;

	/**
	 * Launch the application.

	/**
	 * Create the frame.
	 * @return 
	 */
	
	public UserInterface() {
		innitGui();
		addActions();
	}
	
	public void innitGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 858, 631);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 852, 603);
		contentPane.add(tabbedPane);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		tabbedPane.addTab("BILLING", null, panel, null);
		
		comboBox = new JComboBox();
		comboBox.setBounds(35, 30, 45, 22);
		panel.add(comboBox);
		
		cedulaID = new JTextField();
		cedulaID.setColumns(10);
		cedulaID.setBounds(75, 30, 140, 22);
		Main.cedulaID = cedulaID;
		panel.add(cedulaID);
		
		productsTA = new JTextArea();
		productsTA.setBounds(35, 164, 770, 333);
		panel.add(productsTA);
		
		adressTF = new JTextArea();
		adressTF.setWrapStyleWord(true);
		adressTF.setToolTipText("Insert direction");
		adressTF.setBounds(251, 30, 211, 61);
		Main.clientAdr = adressTF;
		panel.add(adressTF);
		
		billButton = new JButton("BILL");
		billButton.setBounds(712, 508, 91, 45);
		panel.add(billButton);
		
		resetButton = new JButton("RESET");
		resetButton.setBounds(611, 507, 91, 46);
		panel.add(resetButton);
		
		clearButton = new JButton("CLEAR");
		clearButton.setBounds(510, 507, 91, 46);
		panel.add(clearButton);
		
		textArea_2 = new JTextArea();
		textArea_2.setFocusable(false);
		textArea_2.setText("Cedula:");
		textArea_2.setOpaque(false);
		textArea_2.setEditable(false);
		textArea_2.setBounds(35, 11, 66, 20);
		panel.add(textArea_2);
		
		textArea_3 = new JTextArea();
		textArea_3.setFocusable(false);
		textArea_3.setText("Direccion :");
		textArea_3.setOpaque(false);
		textArea_3.setEditable(false);
		textArea_3.setBounds(251, 11, 91, 20);
		panel.add(textArea_3);
		
		scrollBar = new JScrollBar();
		scrollBar.setBounds(788, 164, 17, 333);
		productsTA.add(scrollBar);
		
		idProductTF = new JTextField();
		idProductTF.setBounds(142, 520, 147, 20);
		panel.add(idProductTF);
		idProductTF.setColumns(10);
		
		idProductTA = new JTextArea();
		idProductTA.setFocusable(false);
		idProductTA.setText("Product ID :");
		idProductTA.setOpaque(false);
		idProductTA.setEditable(false);
		idProductTA.setBounds(35, 519, 166, 20);
		panel.add(idProductTA);
		
		addCustButton = new JButton("ADD COSTUMER\r\n");
		addCustButton.setBounds(35, 117, 180, 23);
		panel.add(addCustButton);
		
		employeeIDTA = new JTextArea();
		employeeIDTA.setFocusable(false);
		employeeIDTA.setText("Employee ID :\r\n");
		employeeIDTA.setOpaque(false);
		employeeIDTA.setBounds(523, 11, 106, 20);
		panel.add(employeeIDTA);
		
		employeeIDTF = new JTextField();
		employeeIDTF.setColumns(10);
		employeeIDTF.setBounds(633, 12, 172, 20);
		Main.employeeID = employeeIDTF;
		panel.add(employeeIDTF);
		
		nameTA = new JTextArea();
		nameTA.setFocusable(false);
		nameTA.setText("Nombre :");
		nameTA.setOpaque(false);
		nameTA.setEditable(false);
		nameTA.setBounds(35, 52, 66, 20);
		panel.add(nameTA);
		
		clientNameTF = new JTextField();
		clientNameTF.setColumns(10);
		clientNameTF.setBounds(35, 73, 180, 22);
		Main.clientName = clientNameTF;
		panel.add(clientNameTF);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("ADMINISTRATOR", null, panel_1, null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	private void addActions(){

		billButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}});
		
		addCustButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Client();
				Main.cedulaID.setEditable(false);
				Main.clientAdr.setEditable(false);
				Main.clientName.setEditable(false);
			}});
		
		employeeIDTF.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Main.employeeID.setEditable(false);
			}});
		
		cedulaID.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Client.getClient();
			}
			
		});
		
		
	}
}
