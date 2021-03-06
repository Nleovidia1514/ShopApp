package com.labc.A3;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Bill {
	public static Client client;
	static double TAX = 0.12;
	private static Connection connection = ConnManager.getConnection();
	private Random rnd = Main.rnd;
	private SimpleDateFormat sdf = Main.sdf;
	private int billNmbr = 0;
	public HashMap<Integer,Bill_Product> productsInBill;
	public ArrayList<Bill_Product> productsToBuy = new ArrayList<Bill_Product>();
	private Client owner;
	private Employee employee;
	private double subtotal;
	private double total;
	
	public Bill(Client client, Employee employee) {
		this.billNmbr = rnd.nextInt(10000);
		if(connection!=null) {
			Statement stm = null;
			try {
				String query = "Select idbill from bill where idbill = "+this.billNmbr;
				stm = connection.createStatement();
				ResultSet rs = stm.executeQuery(query);
				while (rs.next()) {
					this.billNmbr = rnd.nextInt(10000);
					query = "Select idbill from bill where idbill = "+this.billNmbr;
					rs = stm.executeQuery(query);
				}
				this.productsInBill = new HashMap<Integer,Bill_Product>();
				this.owner = client;
				this.employee = employee;
				this.subtotal = 0;
				this.total = 0;
			}catch(SQLException e) {
				e.printStackTrace();
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
	
	public void addProduct(int idproduct) {
		Bill_Product.addProducts(idproduct, this);
	}
	
	public static boolean generateBill(Bill bill) {
		Statement stm = null;
		if(connection != null) {
			String query = String.format("Select * from client where idclient = '%s';",bill.owner.getIdclient());
			try
			{
				stm = connection.createStatement();
				ResultSet rs = stm.executeQuery(query);
				if(!rs.next()) {
					query = String.format("Insert into client values ('%s','%s','%s',0);",bill.owner.getIdclient(),
							bill.owner.getCName(),
							bill.owner.getCAdress());
					stm.execute(query);
				}
				query = String.format("Insert into bill values (%d,'%s','%s',%d,0,0);",bill.billNmbr,
						bill.sdf.format(System.currentTimeMillis()),
						bill.owner.getIdclient(),
						bill.employee.getIdemployee());
				stm.execute(query);
					
				for(int i = 0; i<bill.productsToBuy.size() ; i++) {
					Bill_Product product = bill.productsToBuy.get(i);
					query = String.format("Insert into bill_product values(%d,%d,%d,"+product.getTotal()+");",product.getBill().billNmbr,
							product.getProduct().getIdproduct(),
							product.getQuantity());
					stm.execute(query);
					query = String.format("Update bill set subtotal = subtotal +"+product.getTotal()+" where idbill = %d;",
								bill.billNmbr);
					stm.execute(query);
					query = String.format("Update product set stock = stock-%d where idproduct = %d;",product.getQuantity(),
							product.getProduct().getIdproduct());
					stm.execute(query);
				}
				query = String.format("Update bill set total = subtotal+(subtotal*+"+Bill.TAX+") where idbill = %d;",bill.billNmbr);
				stm.execute(query);
				double moneyToPay = 0;
				//String cMoney = JOptionPane.showInputDialog(Main.frame, "Enter the amount of currency you received");
				double cMoney;
				query = String.format("Select total from bill where idbill = %d;",bill.billNmbr);
				ResultSet rs1 = stm.executeQuery(query);
				if(rs1.next())
					moneyToPay = rs1.getDouble("total");
				cMoney = moneyToPay;
				if(cMoney>=moneyToPay) {
					JOptionPane.showMessageDialog(Main.frame, "Thanks for your purchase!", "THANKS", JOptionPane.INFORMATION_MESSAGE);
					Main.total = 0;
					Main.totalPane.setText("TOTAL:\n\t"+Main.total);
					return true;
				}
				else {
					query = String.format("delete from bill_product where idbill = %d;"
							+ "delete from bill where idbill = %d;",bill.billNmbr,bill.billNmbr);
					stm.execute(query);
					JOptionPane.showMessageDialog(Main.frame, "That is so sad :(", "SADFACE", JOptionPane.INFORMATION_MESSAGE);
					return true;
				}
			}catch(SQLException e) {
				e.printStackTrace();
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
		return false;
	}
	
	@SuppressWarnings("rawtypes")
	public static void selectFromBills(JComboBox Box, DefaultTableModel billsDtm) {
		Statement stm = null;
		String query = null;
		if(connection != null) {
			try {
				stm = connection.createStatement();
				if(Box.getSelectedIndex()==0 || Box.getSelectedItem().equals("Bills"))
					query = "Select * from bill";
				else
					query = "Select * from bill where idbill = '"+(String)Box.getSelectedItem()+"';";
				ResultSet rs = stm.executeQuery(query);
				billsDtm.setRowCount(0);
				while(rs.next()) {
					Object[] newRow = {rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),
							rs.getDouble(5), rs.getDouble(6)};
					billsDtm.addRow(newRow);
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
}
