package com.labc.A3;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

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

