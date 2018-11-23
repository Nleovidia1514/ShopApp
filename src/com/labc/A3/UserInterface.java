package com.labc.A3;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class UserInterface extends JFrame {

	public JFrame frame = this;
	private JTabbedPane tabbedPane;
	private JPanel contentPane;
	private JTextField cedulaID, idProductTF, employeeIDTF, clientNameTF;
	private JComboBox<String> comboBox;
	private JTextArea adressTF, textArea_2, textArea_3, idProductTA, employeeIDTA, nameTA;
	private JButton billButton, resetButton, clearButton, addCustButton;
	public static String format = String.format("%1$-30s %2$-30s %3$-30s %4$-30s","Product","Price","Quantity","Total");
	private JTextField Esclavo2,Esclavito1,Esclavito2,Esclavito3;
	private JButton InsertEsclavito;
	private JTextArea textArea;
	private JComboBox Mano1,Manito4,Manito5;
	private JTextField Mano2,Manito1,Manito2,Manito3;
	private JButton InsertManito;
	private JTextField Perrito1;
	private JTextArea plox;
	private JComboBox Perro1, perrito7;
	private JTextField Perro2;
	private JTextField Perrito2;
	private JTextField Perrito3;
	private JButton InsertPerrito;
	private JTextArea txtrConsultas, queryArea;
	private JTextField perrito4;
	private JTextField perrito5;
	private JTextField perrito6;
	private JTable table;
	private JButton cancelButton;

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
		ButtonAction.format = UserInterface.format;
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		tabbedPane.addTab("BILLING", null, panel, null);
		innitBillingTab(panel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(35, 160, 768, 336);
		panel.add(panel_2);
		
		table = new JTable();
		DefaultTableModel dtm = new DefaultTableModel(
				new Object[][] {
					{"  PRODUCT", "  Price", "  Quantity", "  Total"},
				},
				new String[] {
					" Product", " Price", "  Quantity", "  Total"
				}
			) {
				Class[] columnTypes = new Class[] {
					String.class, Object.class, Object.class, Object.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				boolean[] columnEditables = new boolean[] {
					false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		table.setModel(dtm);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(305);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(176);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(64);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(176);
		Main.productsArea = dtm;
		panel_2.add(table);
		
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
				else if(Esclavo2.getText().equalsIgnoreCase("delete")) 
					Provider.papocochino((String) Esclavo1.getSelectedItem());
				
				else if(Esclavo2.getText().equalsIgnoreCase("read")) {
					Provider.papoApestoso(Esclavo1, queryArea);
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
				Provider.papolimpio(Esclavito2.getText(),Integer.valueOf (Esclavito1.getText()), Esclavito3.getText());
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
		
		Manito1 = new JTextField();
		Manito1.setVisible(false);
		Manito1.setToolTipText("Ci");
		Manito1.setColumns(10);
		Manito1.setBounds(40, 289, 64, 39);
		panel_1.add(Manito1);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setText("Empleados:");
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
		textArea.setBackground(SystemColor.menu);
		textArea.setBounds(40, 202, 136, 26);
		panel_1.add(textArea);
		
		Mano1 = new JComboBox();
		Mano1.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 15));
		Mano1.setBackground(Color.WHITE);
		Mano1.setBounds(40, 239, 136, 39);
		Mano1.addItem("Employees");
		panel_1.add(Mano1);
		
		Mano2 = new JTextField();
		Mano2.setColumns(10);
		Mano2.setBounds(186, 239, 90, 39);
		Mano2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(Mano2.getText().equalsIgnoreCase("insert")) {
					Mano2.setEnabled(false);
						Manito1.setVisible(true);
							Manito2.setVisible(true);
								Manito3.setVisible(true);
									InsertManito.setVisible(true);
										Manito4.setVisible(true);
											Manito5.setVisible(true);
					}	
					else if(Mano2.getText().equalsIgnoreCase("delete")) {
						
					}
					else if(Mano2.getText().equalsIgnoreCase("read"))
						Employee.selectEmployee(Mano1, queryArea);
			}
		});
		panel_1.add(Mano2);
		
		Manito2 = new JTextField();
		Manito2.setVisible(false);
		Manito2.setToolTipText("Name");
		Manito2.setColumns(10);
		Manito2.setBounds(114, 289, 62, 39);
		panel_1.add(Manito2);
		
		Manito3 = new JTextField();
		Manito3.setVisible(false);
		Manito3.setColumns(10);
		Manito3.setBounds(186, 289, 90, 39);
		panel_1.add(Manito3);
		
		InsertManito = new JButton("Insert");
		InsertManito.setVisible(false);
		InsertManito.setBounds(40, 339, 382, 23);
		InsertManito.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Employee.insertEmployee(Integer.valueOf(Manito1.getText()), Manito2.getText(), Manito3.getText(),
						(String)Manito5.getSelectedItem(),(String)Manito4.getSelectedItem());
				Manito1.setText(null);
				Manito1.setVisible(false);
				Manito2.setText(null);
				Manito2.setVisible(false);
				Manito3.setText(null);
				Manito3.setVisible(false);
				Manito4.setSelectedItem(null);
				Manito4.setVisible(false);
				Manito5.setSelectedItem(null);
				Manito5.setVisible(false);
				InsertManito.setVisible(false);
				Mano2.setText(null);
				Mano2.setEnabled(true);
			}
		});
		panel_1.add(InsertManito);
		
		Perrito1 = new JTextField();
		Perrito1.setVisible(false);
		Perrito1.setToolTipText("ID");
		Perrito1.setColumns(10);
		Perrito1.setBounds(40, 460, 64, 39);
		panel_1.add(Perrito1);
		
		plox = new JTextArea();
		plox.setEditable(false);
		plox.setText("Productos:");
		plox.setFont(new Font("Monospaced", Font.PLAIN, 16));
		plox.setBackground(SystemColor.menu);
		plox.setBounds(40, 373, 136, 26);
		panel_1.add(plox);
		
		Perro1 = new JComboBox();
		Perro1.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 15));
		Perro1.setBackground(Color.WHITE);
		Perro1.setBounds(40, 410, 136, 39);
		Perro1.addItem("ProdID");
		panel_1.add(Perro1);
		
		Perro2 = new JTextField();
		Perro2.setColumns(10);
		Perro2.setBounds(186, 410, 90, 39);
		Perro2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(Perro2.getText().equalsIgnoreCase("insert")) {
					Perro2.setEnabled(false);
						Perrito1.setVisible(true);
						Perrito2.setVisible(true);
						Perrito3.setVisible(true);
						perrito4.setVisible(true);
						perrito5.setVisible(true);
						perrito6.setVisible(true);
						perrito7.setVisible(true);
				}
				else if(Perro2.getText().equalsIgnoreCase("Delete"));
				
			}
		});
		panel_1.add(Perro2);
		
		Perrito2 = new JTextField();
		Perrito2.setVisible(false);
		Perrito2.setToolTipText("Description");
		Perrito2.setColumns(10);
		Perrito2.setBounds(114, 460, 62, 39);
		panel_1.add(Perrito2);
		
		Perrito3 = new JTextField();
		Perrito3.setVisible(false);
		Perrito3.setToolTipText("buyprice");
		Perrito3.setColumns(10);
		Perrito3.setBounds(186, 460, 46, 39);
		panel_1.add(Perrito3);
		
		InsertPerrito = new JButton("Insert");
		InsertPerrito.setVisible(false);
		InsertPerrito.setBounds(40, 510, 382, 23);
		panel_1.add(InsertPerrito);
		
		txtrConsultas = new JTextArea();
		txtrConsultas.setEditable(false);
		txtrConsultas.setText("                        Consultas");
		txtrConsultas.setFont(new Font("Stencil", Font.PLAIN, 22));
		txtrConsultas.setBackground(SystemColor.menu);
		txtrConsultas.setBounds(420, 68, 342, 32);
		panel_1.add(txtrConsultas);
		
		Manito4 = new JComboBox();
		Manito4.setBackground(Color.WHITE);
		Manito4.setVisible(false);
		Manito4.setBounds(286, 241, 46, 39);
		Manito4.addItem("SEX");
		Manito4.addItem("M");
		Manito4.addItem("F");
		Manito4.addItem("U");
		panel_1.add(Manito4);
		
		Manito5 = new JComboBox();
		Manito5.setBackground(Color.WHITE);
		Manito5.setVisible(false);
		Manito5.setBounds(286, 289, 136, 39);
		panel_1.add(Manito5);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		queryArea = new JTextArea();
		queryArea.setEditable(false);
		queryArea.setBounds(449, 102, 364, 431);
		panel_1.add(queryArea);
		
		perrito4 = new JTextField();
		perrito4.setVisible(false);
		perrito4.setToolTipText("sellprice");
		perrito4.setColumns(10);
		perrito4.setBounds(242, 460, 46, 39);
		panel_1.add(perrito4);
		
		perrito5 = new JTextField();
		perrito5.setVisible(false);
		perrito5.setToolTipText("restock");
		perrito5.setColumns(10);
		perrito5.setBounds(298, 460, 46, 39);
		panel_1.add(perrito5);
		
		perrito6 = new JTextField();
		perrito6.setVisible(false);
		perrito6.setToolTipText("stock");
		perrito6.setColumns(10);
		perrito6.setBounds(354, 460, 68, 39);
		panel_1.add(perrito6);
		
		perrito7 = new JComboBox();
		perrito7.setVisible(false);
		perrito7.setFont(new Font("Dialog", Font.PLAIN, 15));
		perrito7.setBackground(Color.WHITE);
		perrito7.setBounds(286, 410, 136, 39);
		perrito7.addItem("Provider");
		panel_1.add(perrito7);
		addProviderstoGui(Esclavo1,Mano1,Manito5,Perro1,perrito7);
		
		
		
		
	}
	private void addProviderstoGui(JComboBox Box, JComboBox Box1, JComboBox Box2, JComboBox Box3, JComboBox Box4) {
		if(ConnManager.getConnection()!=null) {
			Statement stm = null;
			String query = "select Pname from provider;";
			try {
				stm = ConnManager.getConnection().createStatement();
				ResultSet rs = stm.executeQuery(query);
				while(rs.next()) {
					String pname = rs.getString("Pname");
					Box.addItem(pname);
					Box4.addItem(pname);
				}
					
				query = "Select idemployee from employee;";
				rs = stm.executeQuery(query);
				while(rs.next()) 
					Box1.addItem(rs.getInt("idemployee"));
				
				query = "select oname from ocupation;";
				rs = stm.executeQuery(query);
				while(rs.next())
					Box2.addItem(rs.getString("oname"));
				
				query = "select idproduct from product;";
				rs = stm.executeQuery(query);
				while(rs.next())
					Box3.addItem(rs.getInt("idproduct"));
			
				
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
		DefaultTableModel dtm = new DefaultTableModel(
				new Object[][] {null,null,null,null
				},
				new String[] {
					"  Product", "  Price", " Quantity", " Total"
				}
			) {
				Class[] columnTypes = new Class[] {
					String.class, Double.class, Integer.class, Double.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				boolean[] columnEditables = new boolean[] {
					false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		Main.productsArea = dtm;
		
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
		
		cancelButton = new JButton("CANCEL");
		cancelButton.setBounds(409, 508, 91, 46);
		panel.add(cancelButton);
		
		JTextPane totalPane = new JTextPane();
		totalPane.setEditable(false);
		totalPane.setText("TOTAL:\n\t"+Main.total);
		totalPane.setFont(new Font("Cambria Math", Font.BOLD, 28));
		totalPane.setBackground(SystemColor.menu);
		totalPane.setBounds(489, 52, 336, 94);
		Main.totalPane = totalPane;
		panel.add(totalPane);
	}
	
	private void addActions(){
		ButtonAction ba = new ButtonAction();
		billButton.addActionListener(ba);
		resetButton.addActionListener(ba);
		addCustButton.addActionListener(ba);
		clearButton.addActionListener(ba);
		cancelButton.addActionListener(ba);

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
					for(int i = Main.productsArea.getRowCount()-1; i>0; i--) {
						Main.productsArea.removeRow(i);
					}
						
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
			if(Bill.client!=null && Bill.client.getBill()!=null) 
				for(int i = 0; i<Bill.client.getBill().productsToBuy.size(); i++) {
					Bill.client.getBill().productsToBuy.get(i).getProduct().setStocked(
							Bill.client.getBill().productsToBuy.get(i).getProduct().getStocked()+
							Bill.client.getBill().productsToBuy.get(i).getQuantity());
				}
			Bill.client = null;
			Main.cedulaID.setText(null);
			Main.cedulaID.setEditable(true);
			Main.clientName.setText(null);
			Main.clientName.setEditable(true);
			Main.clientAdr.setText(null);
			Main.clientAdr.setEditable(true);
			Main.employeeID.setText(null);
			Main.employeeID.setEditable(true);
			for(int i = Main.productsArea.getRowCount()-1; i>0; i--) {
				Main.productsArea.removeRow(i);
			}
				
		}
		else if(e.getActionCommand().equals("CLEAR")) {
			if(Bill.client.getBill()!=null && Bill.client.getBill()!=null) 
				for(int i = 0; i<Bill.client.getBill().productsToBuy.size(); i++) {
					Bill.client.getBill().productsToBuy.get(i).getProduct().setStocked(
							Bill.client.getBill().productsToBuy.get(i).getProduct().getStocked()+
							Bill.client.getBill().productsToBuy.get(i).getQuantity());
				}
			Bill.client = null;
			Main.cedulaID.setText(null);
			Main.cedulaID.setEditable(true);
			Main.clientName.setText(null);
			Main.clientName.setEditable(true);
			Main.clientAdr.setText(null);
			Main.clientAdr.setEditable(true);
			for(int i = Main.productsArea.getRowCount()-1; i>0; i--)
				Main.productsArea.removeRow(i);
		}
		else if(e.getActionCommand().equals("CANCEL")) {
			if(Bill.client!=null && Bill.client.getBill()!=null && Bill.client.getBill().productsToBuy.size()>0) {
				Bill_Product product =Bill.client.getBill().productsToBuy.get(Bill.client.getBill().productsToBuy.size()-1);
				if(product.getQuantity()==1) {
					Bill.client.getBill().productsInBill.remove(product.getProduct().getIdproduct());
					Bill.client.getBill().productsToBuy.remove(product);
					Main.productsArea.removeRow(Main.productsArea.getRowCount()-1);
				}
				else {
					product.setQuantity(product.getQuantity()-1);
					boolean ok = false;
					for(int i = 0; i<Main.productsArea.getColumnCount(); i++)
						for(int j = 0; j<Main.productsArea.getRowCount(); j++) {
							if(Main.productsArea.getValueAt(j, i).equals(product.getProduct().getPdescription())) {
								Main.productsArea.setValueAt(product.getQuantity(),j,2);
								Main.productsArea.setValueAt(product.getTotal()+(product.getTotal()*Bill.TAX),j,3);
								ok = true;
								break;
							}
							if(ok)
								break;
						}
				}
				Main.total = Main.total - (product.getProduct().getSellprice()+(product.
						getProduct().getSellprice()*Bill.TAX));
				Main.totalPane.setText("TOTAL:\n\t"+Main.total);
				product.getProduct().setStocked(product.getProduct().getStocked()+1);
			}
		}
	}
	
}
