package com.labc.A3;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class Ocupation {
	private static Connection connection = ConnManager.getConnection();
	private int idocupation;
	private String oname;
	private double osalary;
	public static HashMap<Integer,Ocupation> ocupations = new HashMap<Integer,Ocupation>();
	
	private Ocupation(int idocupation,String oname,double osalary) {
		this.setIdocupation(idocupation);
		this.setOname(oname);
		this.setOsalary(osalary);
		Ocupation.ocupations.put(idocupation, this);
	}
	
	public static void innitOcupations() {
		Statement stm = null;
		String query = "Select * from ocupation";
		if(connection != null) {
			try {
				stm = connection.createStatement();
				ResultSet rs = stm.executeQuery(query);
				while(rs.next()) {
					int idOcupation = rs.getInt("idocupation");
					String oName = rs.getString("oname");
					double oSalary = rs.getDouble("osalary");
					new Ocupation(idOcupation,oName,oSalary);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public int getIdocupation() {
		return idocupation;
	}

	public void setIdocupation(int idocupation) {
		this.idocupation = idocupation;
	}

	public String getOname() {
		return oname;
	}

	public void setOname(String oname) {
		this.oname = oname;
	}

	public double getOsalary() {
		return osalary;
	}

	public void setOsalary(double osalary) {
		this.osalary = osalary;
	}
}
