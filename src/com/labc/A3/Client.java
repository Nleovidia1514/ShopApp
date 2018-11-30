package com.labc.A3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Client {
	private static Connection connection = ConnManager.getConnection();
	private String idclient;
	private String cname;
	private String cadress;
	private double cmoney;
	private Bill bill;
	public static HashMap<String,Client> clients = new HashMap<String,Client>();

	private Client(String idclient, String cname, String cadress) {
		this.setIdclient(idclient);
		this.cname = cname;
		this.cadress = cadress;
		this.setCmoney(0);
		this.bill = null;
		Client.clients.put(idclient, this);
	}
	
	public Bill getBill() {
		return this.bill;
	}
	
	public static void innitClients() {
		Statement stm = null;
		String query = "Select * from client";
		try
		{
			stm = connection.createStatement();
			ResultSet rs = stm.executeQuery(query);
			while(rs.next()) {
				String idClient = rs.getString("idclient");
				String cName = rs.getString("cname");
				String cAdress = rs.getString("cadress");
				new Client(idClient,cName,cAdress);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try{stm.close();}catch (SQLException e) {e.printStackTrace();}
		}
	}
	
	public static Client getClient(String idClient,String nationalitie) {
		Client client = Client.clients.get(nationalitie+idClient);
		if(client!=null) {
			Main.cedulaID.setEditable(false);
			Main.clientAdr.setText(client.cadress);
			Main.clientAdr.setEditable(false);
			Main.clientName.setText(client.cname);
			Main.clientName.setEditable(false);
			return client;
		}
		else
			JOptionPane.showMessageDialog(Main.frame, "Client does not exists", "Error", JOptionPane.ERROR_MESSAGE);
		return null;
	}
	
	public static Client addCustomer(String idclient) {
		String cedula = Main.cedulaID.getText();
		String name = Main.clientName.getText();
		String adress = Main.clientAdr.getText();
		if(!cedula.equals("") && !name.equals("") && !adress.equals("")) {
			Main.cedulaID.setEditable(false);
			Main.clientAdr.setEditable(false);
			Main.clientName.setEditable(false);
			return new Client(idclient,name,adress);
		}
		else
			JOptionPane.showMessageDialog(Main.frame, "Missing an obligatory field.", "Error", JOptionPane.ERROR_MESSAGE);
		return null;
	}

	public static void SelectFromClient(JComboBox Box, DefaultTableModel clientsDtm) {
		Statement stm = null;
		String query = null;
		if(connection != null) {
			try {
				stm = connection.createStatement();
				if(Box.getSelectedIndex()==0 || Box.getSelectedItem().equals("Clients"))
					query = "Select * from client";
				else
					query = "Select * from client where idclient = '"+(String)Box.getSelectedItem()+"';";
				ResultSet rs = stm.executeQuery(query);
				clientsDtm.setRowCount(0);
				while(rs.next()) {
					Object[] newRow = {rs.getString(1),rs.getString(2),rs.getString(3),rs.getDouble(4)};
					clientsDtm.addRow(newRow);
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(Main.frame, e, "ERROR", JOptionPane.ERROR_MESSAGE);
			}finally {
				if(stm!=null) {
					try {
						stm.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}	
		}
	}
	
	public void addProductToBill(int idemployee,int idproduct) {
		if(Product.products.get(idproduct)!=null) {
			if(this.bill==null) {
				this.bill = new Bill(this,Employee.employees.get(idemployee));
				this.bill.addProduct(idproduct);
			}
			else 
				this.bill.addProduct(idproduct);
			
			Bill_Product product = this.bill.productsInBill.get(idproduct);
			if(product!=null && product.getQuantity()>1){
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
			else if(product != null) {
				Object[] newRow = {product.getProduct().getPdescription(),
						product.getProduct().getSellprice(),
						product.getQuantity(),
						product.getTotal()+(product.getTotal()*Bill.TAX)};
				Main.productsArea.addRow(newRow);
			}
			
		}
		else
			JOptionPane.showMessageDialog(Main.frame, "Product does not exists.", "Error", JOptionPane.ERROR_MESSAGE);
	}

	public String getIdclient() {
		return idclient;
	}

	public void setIdclient(String idclient) {
		this.idclient = idclient;
	}

	public double getCmoney() {
		return cmoney;
	}

	public void setCmoney(double cmoney) {
		this.cmoney = cmoney;
	}
	
	public String getCName() {
		return this.cname;
	}
	
	public String getCAdress() {
		return this.cadress;
	}
	
	/*Statement stm = null;
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
				}
			}
			else
				JOptionPane.showMessageDialog(Main.frame, "Missing an obligatory field.","Error", JOptionPane.ERROR_MESSAGE);
		}
*/
}
