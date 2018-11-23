package com.labc.A3;

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
	public static void papocochino(String Pname) {
		if(ConnManager.getConnection()!=null) {
			Statement stm = null;
			String query = "delete from provider where Pname ='"+ Pname+"'";
			try {
				stm = ConnManager.getConnection().createStatement();
				stm.execute(query);
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
	public static void papolimpio(String Pname,int idPapo,String Address) {
		if(ConnManager.getConnection()!=null) {
			Statement stm = null;
			String query = String.format("insert into %s values (%d,'%s','%s')", "provider",idPapo,Pname,Address);
			try {
				stm = ConnManager.getConnection().createStatement();
				stm.execute(query);
				JOptionPane.showMessageDialog(Main.frame, "Tu papo esta listo", "Congrats Osc", JOptionPane.INFORMATION_MESSAGE);
			} catch (SQLException e) {
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
	public static void papoApestoso(JComboBox Box, JTextArea Area) {
		Statement stm = null;
		String query = null;
		if(connection != null) {
			try {
				stm = connection.createStatement();
				if(Box.getSelectedIndex()==0)
					query = "Select * from provider";
				else
					query = "Select * from provider where pname = '"+(String)Box.getSelectedItem()+"';";
				ResultSet rs = stm.executeQuery(query);
				Area.setText("PROVIDER ID\tPROVIDER NAME\tPROVIDER ADRESS\n");
				while(rs.next()) {
					String toShow = rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\n";
					Area.append(toShow);
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

