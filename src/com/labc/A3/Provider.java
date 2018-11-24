package com.labc.A3;

import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

public class Provider {
	private static Connection connection = ConnManager.getConnection();
	private int idprovider;
	private String pname;
	private String padress;
	public static HashMap<Integer,Provider> providers = new HashMap<Integer,Provider>();

	private Provider(int idprovider,String pname,String padress) {
		this.setIdprovider(idprovider);
		this.setPname(pname);
		this.setPadress(padress);
		Provider.providers.put(idprovider, this);
	}
	
	public static void innitProviders() {
		Statement stm = null;
		String query = "Select * from provider";
		if(connection != null) {
			try {
				stm = connection.createStatement();
				ResultSet rs = stm.executeQuery(query);
				while(rs.next()) {
					int idprovider = rs.getInt("idprovider");
					String pname = rs.getString("pname");
					String padress = rs.getString("padress");
					new Provider(idprovider,pname,padress);
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
	}
	public static void papocochino(String Pname,DefaultTableModel providersDtm,JComboBox box) {
		Statement stm = null;
		String query = "select idprovider from provider where Pname ='"+ Pname+"'";
		if(ConnManager.getConnection()!=null) {
			try {
				stm = ConnManager.getConnection().createStatement();
				ResultSet rs = stm.executeQuery(query);
				if(rs.next())
					Provider.providers.remove(rs.getInt(1));
				
				for(int i= providersDtm.getRowCount();i>0;i--) {		
					if(providersDtm.getValueAt(i-1, 1).equals(Pname))
						providersDtm.removeRow(i-1);
				}
				query = "delete from provider where pname = '"+Pname+"';";
				stm.execute(query);
				box.removeItem(Pname);
				JOptionPane.showMessageDialog(Main.frame,"Provider removed succesfully","Removed",
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
	public static void papolimpio(String Pname,int idPapo,String Address) {
		if(ConnManager.getConnection()!=null) {
			Statement stm = null;
			String query = String.format("insert into %s values (%d,'%s','%s')", "provider",idPapo,Pname,Address);
			try {
				stm = ConnManager.getConnection().createStatement();
				stm.execute(query);
				JOptionPane.showMessageDialog(Main.frame, "Tu papo esta listo", "Congrats Osc", JOptionPane.INFORMATION_MESSAGE);
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
	public static void papoApestoso(JComboBox Box,DefaultTableModel providersDtm) {
		Statement stm = null;
		String query = null;
		if(connection != null) {
			try {
				stm = connection.createStatement();
				if(Box.getSelectedIndex()==0 || Box.getSelectedItem().equals("Providers"))
					query = "Select * from provider";
				else
					query = "Select * from provider where pname = '"+(String)Box.getSelectedItem()+"';";
				ResultSet rs = stm.executeQuery(query);
				providersDtm.setRowCount(0);
				while(rs.next()) {
					Object[] newRow = {rs.getInt(1),rs.getString(2),rs.getString(3)};
					providersDtm.addRow(newRow);
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
	public static void papoPeluo(String text, DefaultTableModel providersDtm) {
		Statement stm = null;
		JComboBox box = new JComboBox();
		box.addItem("idProvider");
		box.addItem("pName");
		box.addItem("pAdress");
		box.setBackground(Color.WHITE);
		String toUpdate = JOptionPane.showInputDialog(Main.frame, box, "Which field do you wish"
				+ " to update?", JOptionPane.QUESTION_MESSAGE);
		String query = "update provider set "+box.getSelectedItem()+" = ";
		if(box.getSelectedItem().equals("pName") || box.getSelectedItem().equals("pAdress"))
			query = query +"'"+ toUpdate+"' where pname ='"+text+"';";
		else
			query = query + toUpdate+" where pname ='"+text+"';";
		if(connection != null && toUpdate!=null) {
			try {
				for(int i= providersDtm.getRowCount();i>0;i--) 	{
					if(providersDtm.getValueAt(i-1, 1).equals(text)) 
						providersDtm.setValueAt(toUpdate, i-1, box.getSelectedIndex());
					}
				stm = connection.createStatement();
				stm.execute(query);
				ResultSet rs = stm.executeQuery("Select * from provider where "+
				box.getSelectedItem()+" = '"+toUpdate+"';");
				while(rs.next()) {
					Provider.providers.remove(rs.getInt(1));
					new Provider(rs.getInt(1),rs.getString(2),rs.getString(3));
				}
				JOptionPane.showMessageDialog(Main.frame, "Updated succesfully", "Updated",
						JOptionPane.INFORMATION_MESSAGE);
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				if (stm!=null) {
					try {
						stm.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	public int getIdprovider() {
		return idprovider;
	}

	public void setIdprovider(int idprovider) {
		this.idprovider = idprovider;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPadress() {
		return padress;
	}

	public void setPadress(String padress) {
		this.padress = padress;
	}
}

