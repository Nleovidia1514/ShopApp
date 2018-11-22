package com.labc.A3;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class Employee {
	private static Connection connection = ConnManager.getConnection();
	private int idemployee;
	private String ename;
	private String eadress;
	private Ocupation ocupation;
	private String sex;
	public static HashMap<Integer,Employee> employees = new HashMap<Integer,Employee>();

	private Employee(int idemployee,String ename,String eadress, Ocupation ocupation, String sex) {
		this.setIdemployee(idemployee);
		this.setEname(ename);
		this.setEadress(eadress);
		this.setOcupation(ocupation);
		this.setSex(sex);
		Employee.employees.put(idemployee, this);
	}
	
	public static void innitEmployees() {
		Statement stm = null;
		String query = "Select * from employee";
		if(connection != null) {
			try {
				stm = connection.createStatement();
				ResultSet rs = stm.executeQuery(query);
				while(rs.next()) {
					int idemployee = rs.getInt("idemployee");
					String ename = rs.getString("ename");
					String eadress = rs.getString("eadress");
					int idocupation = rs.getInt("idocupation");
					String sex = rs.getString("sex");
					new Employee(idemployee,ename,eadress,Ocupation.ocupations.get(idocupation),sex);
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

	public int getIdemployee() {
		return idemployee;
	}

	public void setIdemployee(int idemployee) {
		this.idemployee = idemployee;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getEadress() {
		return eadress;
	}

	public void setEadress(String eadress) {
		this.eadress = eadress;
	}

	public Ocupation getOcupation() {
		return ocupation;
	}

	public void setOcupation(Ocupation ocupation) {
		this.ocupation = ocupation;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
}
