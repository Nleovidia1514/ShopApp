package com.labc.A3;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.SwingConstants;

public class UserInterface extends JFrame {

	private static final long serialVersionUID = 1L;
	public JFrame frame = this;
	private JTabbedPane tabbedPane;
	private JPanel contentPane;
	private JTextField cedulaID, idProductTF, employeeIDTF, clientNameTF;
	private JScrollBar scrollBar;
	private JComboBox<String> comboBox;
	private JTextArea productsTA, adressTF, textArea_2, textArea_3, idProductTA, employeeIDTA, nameTA;
	private JButton billButton, resetButton, clearButton, addCustButton;
	public static String format = String.format("%1$-30s %2$-30s %3$-30s %4$-30s","Product","Price","Quantity","Total");
	private JTextField Esclavo2;
	private JTextField Esclavito1;
	private JTextField Esclavito2;
	private JTextField Esclavito3;
	private JButton InsertEsclavito;
	private JTextField textField;
	private JTextField textField_1;
	private JTextArea textArea;
	private JComboBox comboBox_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JButton button;
	private JTextField textField_5;
	private JTextArea plox;
	private JComboBox comboBox_2;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JButton button_1;
	private JTextArea txtrConsultas;

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
		
		JPanel panel = new JPanel();
		innitBillingTab(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setVisible(false);
		tabbedPane.addTab("ADMINISTRATOR", null, panel_1, null);
		panel_1.setLayout(null);
		Main.adminpanel = panel_1;
		
		JComboBox Esclavo1 = new JComboBox();
		Esclavo1.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 15));
		Esclavo1.setBackground(Color.WHITE);
		Esclavo1.setBounds(40, 68, 136, 39);
		panel_1.add(Esclavo1);
		Esclavo1.addItem("Nombre");
		addProviderstoGui(Esclavo1);
		
		JTextArea txtrProveedores = new JTextArea();
		txtrProveedores.setEditable(false);
		txtrProveedores.setFont(new Font("Monospaced", Font.PLAIN, 16));
		txtrProveedores.setBackground(SystemColor.menu);
		txtrProveedores.setText("Proveedores:");
		txtrProveedores.setBounds(40, 40, 136, 26);
		panel_1.add(txtrProveedores);
		
		Esclavo2 = new JTextField();
		Esclavo2.setBounds(186, 68, 90, 39);
		panel_1.add(Esclavo2);
		Esclavo2.setColumns(10);
		Esclavo2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(Esclavo2.getText().equalsIgnoreCase("insert")) {
				Esclavo2.setEnabled(false);
					Esclavito1.setVisible(true);
						Esclavito2.setVisible(true);
							Esclavito3.setVisible(true);
								InsertEsclavito.setVisible(true);
				}	
				else if(Esclavo2.getText().equalsIgnoreCase("delete")) {
					Provider.papocochino((String) Esclavo1.getSelectedItem());
				}
			}
			
		});
		
		
		Esclavito1 = new JTextField();
		Esclavito1.setVisible(false);
		Esclavito1.setToolTipText("Insert Code");
		Esclavito1.setBounds(40, 118, 62, 39);
		panel_1.add(Esclavito1);
		Esclavito1.setColumns(10);
		
		Esclavito2 = new JTextField();
		Esclavito2.setVisible(false);
		Esclavito2.setToolTipText("Insert Name");
		Esclavito2.setColumns(10);
		Esclavito2.setBounds(112, 118, 62, 39);
		panel_1.add(Esclavito2);
		
		Esclavito3 = new JTextField();
		Esclavito3.setVisible(false);
		Esclavito3.setToolTipText("Insert address");
		Esclavito3.setColumns(10);
		Esclavito3.setBounds(188, 118, 90, 39);
		panel_1.add(Esclavito3);
		
		InsertEsclavito = new JButton("Insert");
		InsertEsclavito.setVisible(false);
		InsertEsclavito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Provider.papolimpio(Esclavito2.getText(), "Provider",Integer.valueOf (Esclavito1.getText()), Esclavito3.getText());
				Esclavito1.setText(null);
				Esclavito1.setVisible(false);
				Esclavito2.setText(null);
				Esclavito2.setVisible(false);
				Esclavito3.setText(null);
				Esclavito3.setVisible(false);
				InsertEsclavito.setVisible(false);
				Esclavo2.setText(null);
				Esclavo2.setEnabled(true);
			}
		});
		InsertEsclavito.setBounds(40, 168, 236, 23);
		panel_1.add(InsertEsclavito);
		
		textField = new JTextField();
		textField.setBounds(355, 118, 407, 415);
		panel_1.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setToolTipText("Ci");
		textField_1.setColumns(10);
		textField_1.setBounds(40, 289, 64, 39);
		panel_1.add(textField_1);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setText("Empleados:");
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
		textArea.setBackground(SystemColor.menu);
		textArea.setBounds(40, 202, 136, 26);
		panel_1.add(textArea);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 15));
		comboBox_1.setBackground(Color.WHITE);
		comboBox_1.setBounds(40, 239, 136, 39);
		panel_1.add(comboBox_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(186, 239, 90, 39);
		panel_1.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setToolTipText("Name");
		textField_3.setColumns(10);
		textField_3.setBounds(114, 289, 62, 39);
		panel_1.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(186, 289, 90, 39);
		panel_1.add(textField_4);
		
		button = new JButton("Insert");
		button.setBounds(40, 339, 236, 23);
		panel_1.add(button);
		
		textField_5 = new JTextField();
		textField_5.setToolTipText("Ci");
		textField_5.setColumns(10);
		textField_5.setBounds(40, 460, 64, 39);
		panel_1.add(textField_5);
		
		plox = new JTextArea();
		plox.setEditable(false);
		plox.setText("Productos:");
		plox.setFont(new Font("Monospaced", Font.PLAIN, 16));
		plox.setBackground(SystemColor.menu);
		plox.setBounds(40, 373, 136, 26);
		panel_1.add(plox);
		
		comboBox_2 = new JComboBox();
		comboBox_2.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 15));
		comboBox_2.setBackground(Color.WHITE);
		comboBox_2.setBounds(40, 410, 136, 39);
		panel_1.add(comboBox_2);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(186, 410, 90, 39);
		panel_1.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setToolTipText("Name");
		textField_7.setColumns(10);
		textField_7.setBounds(114, 460, 62, 39);
		panel_1.add(textField_7);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(186, 460, 90, 39);
		panel_1.add(textField_8);
		
		button_1 = new JButton("Insert");
		button_1.setBounds(40, 510, 236, 23);
		panel_1.add(button_1);
		
		txtrConsultas = new JTextArea();
		txtrConsultas.setEditable(false);
		txtrConsultas.setText("                        Consultas");
		txtrConsultas.setFont(new Font("Stencil", Font.PLAIN, 22));
		txtrConsultas.setBackground(SystemColor.menu);
		txtrConsultas.setBounds(355, 68, 407, 39);
		panel_1.add(txtrConsultas);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	private void addProviderstoGui(JComboBox Box) {
		if(ConnManager.getConnection()!=null) {
			Statement stm = null;
			String query = "select Pname from provider;";
			try {
				stm = ConnManager.getConnection().createStatement();
				ResultSet rs = stm.executeQuery(query);
				while(rs.next()) {
					String Pname = rs.getString("Pname");
					Box.addItem(Pname);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				if(stm!=null) {
					try {
						stm.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		}
}
	private void innitBillingTab(JPanel panel) {
		ButtonAction.format = UserInterface.format;
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		tabbedPane.addTab("BILLING", null, panel, null);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(35, 30, 45, 22);
		comboBox.addItem("V");
		comboBox.addItem("J");
		comboBox.addItem("E");
		Main.idType = comboBox;
		panel.add(comboBox);
		
		cedulaID = new JTextField();
		cedulaID.setColumns(10);
		cedulaID.setBounds(79, 30, 136, 22);
		Main.cedulaID = cedulaID;
		panel.add(cedulaID);
		
		productsTA = new JTextArea(format);
		productsTA.setBounds(35, 164, 770, 333);
		productsTA.setEditable(false);
		Main.productsArea = productsTA;
		panel.add(productsTA);
		
		adressTF = new JTextArea();
		adressTF.setLineWrap(true);
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
		
		addCustButton = new JButton("ADD CUSTOMER");
		addCustButton.setBounds(35, 117, 180, 23);
		panel.add(addCustButton);
		
		employeeIDTA = new JTextArea();
		employeeIDTA.setFocusable(false);
		employeeIDTA.setText("Employee ID :\r\n");
		employeeIDTA.setOpaque(false);
		employeeIDTA.setBounds(523, 11, 106, 20);
		panel.add(employeeIDTA);
		
		employeeIDTF = new JTextField("34095746");
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
		
		JButton cancelButton = new JButton("CANCEL");
		cancelButton.setBounds(409, 508, 91, 46);
		panel.add(cancelButton);
		
		JTextPane totalPane = new JTextPane();
		totalPane.setText("TOTAL:\n\t"+Main.total);
		totalPane.setFont(new Font("Cambria Math", Font.BOLD, 28));
		totalPane.setBackground(SystemColor.menu);
		totalPane.setBounds(523, 52, 282, 94);
		Main.totalPane = totalPane;
		panel.add(totalPane);
	}
	
	private void addActions(){
		ButtonAction ba = new ButtonAction();
		billButton.addActionListener(ba);
		resetButton.addActionListener(ba);
		addCustButton.addActionListener(ba);
		clearButton.addActionListener(ba);
		

		employeeIDTF.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Integer.valueOf(employeeIDTF.getText());
					Main.employeeID.setEditable(false);
				}catch(NumberFormatException o) {
					employeeIDTF.setText(null);
					JOptionPane.showMessageDialog(Main.frame, o+"\nPlease insert a valid input", "Warning", JOptionPane.WARNING_MESSAGE);
				}
			}});
		
		cedulaID.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Client client = Client.getClient(cedulaID.getText(),(String)comboBox.getSelectedItem());
				Bill.client = client;
			}});
				
		idProductTF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Bill.client!=null) {
					try {
						Bill.client.addProductToBill(Integer.valueOf(employeeIDTF.getText()),Integer.valueOf(idProductTF.getText()));
					}catch(NumberFormatException e) {
						JOptionPane.showMessageDialog(Main.frame, e+"\nPlease insert a valid input", "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
				else
					JOptionPane.showMessageDialog(Main.frame, "Please do things correctly >.>", "Warning", JOptionPane.WARNING_MESSAGE);
				idProductTF.setText(null);
			}
		});
	}
}

class ButtonAction implements ActionListener{
	public static String format;
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("BILL")) {
			if(Bill.client!=null && Bill.client.getBill()!=null) {
				if(Bill.generateBill(Bill.client.getBill())) {
					Bill.client = null;
					Main.cedulaID.setText(null);
					Main.cedulaID.setEditable(true);
					Main.clientName.setText(null);
					Main.clientName.setEditable(true);
					Main.clientAdr.setText(null);
					Main.clientAdr.setEditable(true);
					Main.productsArea.setText(format);
				}		
			}
			else
				JOptionPane.showMessageDialog(Main.frame, "Please dude behave yourself!", "WARNING", JOptionPane.WARNING_MESSAGE);
		}
		else if(e.getActionCommand().equalsIgnoreCase("ADD CUSTOMER")) {
			Client client = Client.addCustomer((String)Main.idType.getSelectedItem()+Main.cedulaID.getText());
			Bill.client = client;
		}
		
		else if(e.getActionCommand().equals("RESET")) {
			Bill.client = null;
			Main.cedulaID.setText(null);
			Main.cedulaID.setEditable(true);
			Main.clientName.setText(null);
			Main.clientName.setEditable(true);
			Main.clientAdr.setText(null);
			Main.clientAdr.setEditable(true);
			Main.productsArea.setText(format);
			Main.employeeID.setText(null);
			Main.employeeID.setEditable(true);
		}
		else if(e.getActionCommand().equals("CLEAR")) {
			Bill.client = null;
			Main.cedulaID.setText(null);
			Main.cedulaID.setEditable(true);
			Main.clientName.setText(null);
			Main.clientName.setEditable(true);
			Main.clientAdr.setText(null);
			Main.clientAdr.setEditable(true);
			Main.productsArea.setText(format);
		}
		else if(e.getActionCommand().equals("CRUD")) {
			Main.CRUD.setVisible(false);
			Main.adminpanel.setVisible(true);
			
		}
	}
	
}
