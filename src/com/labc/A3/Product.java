package com.labc.A3;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class Product{
	private static Connection connection = ConnManager.getConnection();
	private int idproduct;
	private String pdescription;
	private double buyprice;
	private double sellprice;
	private int restock;
	private int stocked;
	private Provider provider;
	public static HashMap<Integer,Product> products = new HashMap<Integer,Product>();
	
	private Product(int idproduct,String pdescription,double buyprice,double sellprice, int restock,int stocked,Provider provider) {
		this.setIdproduct(idproduct);
		this.setPdescription(pdescription);
		this.setBuyprice(buyprice);
		this.setSellprice(sellprice);
		this.setRestock(restock);
		this.setStocked(stocked);
		this.setProvider(provider);
		Product.products.put(idproduct, this);
	}
	
	public static void innitProducts() {
		Statement stm = null;
		String query = "Select * from product";
		if(connection!=null) {
			try {
				stm = connection.createStatement();
				ResultSet rs = stm.executeQuery(query);
				while(rs.next()) {
					int idproduct = rs.getInt("idproduct");
					String pdescription = rs.getString("pdescription");
					double buyprice = rs.getDouble("buyprice");
					double sellprice = rs.getDouble("sellprice");
					int restock = rs.getInt("restock");
					int stocked = rs.getInt("stock");
					int idprovider = rs.getInt("idprovider");
					new Product(idproduct,pdescription,buyprice,sellprice,restock,stocked,Provider.providers.get(idprovider));
				}
			} catch (SQLException e) {
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
	
	public static void selectFromProduct(JComboBox Box, DefaultTableModel productsDtm) {
		Statement stm = null;
		String query;
		if(connection!=null) {
			try {
				stm = connection.createStatement();
				if(Box.getSelectedIndex()==0 || Box.getSelectedItem().equals("Products"))
					query = "Select * from product";
				else
					query = "Select * from product where idproduct = "+Box.getSelectedItem()+";";
				ResultSet rs = stm.executeQuery(query);
				productsDtm.setRowCount(0);
				while(rs.next()) {
					try{
						Object[] newRow = {rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getDouble(4),rs.getInt(5),
								rs.getInt(6),Provider.providers.get(rs.getInt(7)).getPname()};
						productsDtm.addRow(newRow);
					}catch(NullPointerException e) {
						Object[] newRow = {rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getDouble(4),rs.getInt(5),
								rs.getInt(6),"PROVIDER REMOVED"};
						productsDtm.addRow(newRow);
					}
				}
			} catch (SQLException e) {
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
	public static void insertProduct(int idproduct, String pdescription, double buyprice, double sellprice, int restock
			, int stocked, String pname, DefaultTableModel productsDtm) {
		int idprovider = 0;
		Statement stm = null;
		String query = String.format("select idprovider from provider where pname = '%s'",pname);
		if(connection!=null) {
			try {
				stm = connection.createStatement();
				ResultSet rs = stm.executeQuery(query);
				while(rs.next())
					 idprovider = rs.getInt(1);
				new Product(idproduct, pdescription, buyprice, sellprice, restock, stocked, Provider.providers.get(idprovider));
				query = String.format("insert into product values(%d,'%s',"+buyprice+","+sellprice+",%d,%d,%d)",idproduct,
						pdescription, restock, stocked, idprovider);
				stm.execute(query);
				Object[] newRow = {idproduct, pdescription, buyprice, sellprice, restock, stocked, pname};
				productsDtm.addRow(newRow);
				JOptionPane.showMessageDialog(Main.frame, "Product added succesfully", "Added", JOptionPane.INFORMATION_MESSAGE);
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
	public static void deleteFromProduct(JComboBox Box, DefaultTableModel productsDtm) {
		Statement stm = null;
		int toDelete = Integer.valueOf((String) Box.getSelectedItem());
		String query = "select idproduct from product where idproduct ="+toDelete+";";
		if(ConnManager.getConnection()!=null) {
			try {
				stm = ConnManager.getConnection().createStatement();
				ResultSet rs = stm.executeQuery(query);
				if(rs.next())
					Product.products.remove(rs.getInt(1));
				
				for(int i= productsDtm.getRowCount();i>0;i--) 	
					if(productsDtm.getValueAt(i-1, 0).equals(toDelete)) 
						productsDtm.removeRow(i-1);
					
				query = "delete from product where idproduct = "+toDelete+";";
				stm.execute(query);
				Box.removeItem(Box.getSelectedItem());
				JOptionPane.showMessageDialog(Main.frame,"Product removed succesfully","Removed",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (SQLException e) {
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
	
	public int getIdproduct() {
		return idproduct;
	}

	public void setIdproduct(int idproduct) {
		this.idproduct = idproduct;
	}

	public String getPdescription() {
		return pdescription;
	}

	public void setPdescription(String pdescription) {
		this.pdescription = pdescription;
	}

	public double getBuyprice() {
		return buyprice;
	}

	public void setBuyprice(double buyprice) {
		this.buyprice = buyprice;
	}

	public double getSellprice() {
		return sellprice;
	}

	public void setSellprice(double sellprice) {
		this.sellprice = sellprice;
	}

	public int getRestock() {
		return restock;
	}

	public void setRestock(int restock) {
		this.restock = restock;
	}

	public int getStocked() {
		return stocked;
	}

	public void setStocked(int stocked) {
		this.stocked = stocked;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

}
