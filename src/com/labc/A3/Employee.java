package com.labc.A3;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

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
	
	public static void insertEmployee(int idemployee, String ename, String eadress,String oname, String sex) {
		if(connection != null){
			int ido = 0;
			Statement stm = null;
			String query = "select idocupation from ocupation where oname = '"+oname+"';";
			try {
				stm = connection.createStatement();
				ResultSet rs = stm.executeQuery(query);
				if(rs.next())
					ido = rs.getInt("idocupation");
				
				query = String.format("insert into employee values (%d,'%s','%s',%d,'%s');",idemployee,
						ename,eadress,ido,sex);
				stm.execute(query);
				JOptionPane.showMessageDialog(Main.frame,"New employee added.","Success!",JOptionPane.INFORMATION_MESSAGE);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
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
	public static void selectEmployee(JComboBox Box, JTextArea Area) {
		Statement stm = null;
		String query = null;
		if(connection != null) {
			try {
				stm = connection.createStatement();
				if(Box.getSelectedIndex()==0)
					query = "Select * from employee";
				else
					query = "Select * from employee where idemployee = "+Box.getSelectedItem()+";";
				ResultSet rs = stm.executeQuery(query);
				Area.setText("EMPLOYEE ID\tEMPLOYEE NAME\tEMPLOYEE ADRESS\tEMPLOYEEO\tEMPLOYEESEX\n");
				Statement st1 = null;
				st1 = connection.createStatement();
				while(rs.next()) {
					String oname = null;
					int eid = rs.getInt("idemployee");String ename = rs.getString("ename");String adress = rs.getString("eadress");
					String sex = rs.getString("sex");int ocupation = rs.getInt("idocupation");
					query = "Select oname from ocupation where idocupation = "+ocupation+";";
					ResultSet rs1 = st1.executeQuery(query);
					if(rs1.next())
						 oname = rs1.getString("oname");
					String toShow = String.format("%d\t%s\t%s\t%s\t%s\n", eid,ename,adress,oname,sex);
					Area.append(toShow);
				}
				st1.close();
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
