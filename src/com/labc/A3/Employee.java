package com.labc.A3;

import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

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
	public static void updateEmployee(int text, DefaultTableModel employeesDtm) {
		Statement stm = null;
		int idemployee = 0;
		JComboBox<String> box = new JComboBox<String>();
		box.addItem("ename");
		box.addItem("eadress");
		box.addItem("idocupation");
		box.addItem("sex");
		box.setBackground(Color.WHITE);
		String toUpdate = JOptionPane.showInputDialog(Main.frame, box, "Which field do you wish"
				+ " to update?", JOptionPane.QUESTION_MESSAGE);
		String query = "update employee set "+box.getSelectedItem()+" = ";
		if(box.getSelectedItem().equals("ename") || box.getSelectedItem().equals("eadress") || box.getSelectedItem().equals("sex"))
			query = query +"'"+ toUpdate+"' where idemployee ="+text+";";
		else
			query = query + toUpdate+" where idemployee ="+text+";";
		if(connection != null && toUpdate!=null) {
			try {
				for(int i= employeesDtm.getRowCount();i>0;i--) 	{
					if(employeesDtm.getValueAt(i-1, 0).equals(text)) {
						idemployee = (int) employeesDtm.getValueAt(i-1, 0);
						employeesDtm.setValueAt(toUpdate, i-1, box.getSelectedIndex()+1);
						break;
					}		
				}
				stm = connection.createStatement();
				stm.execute(query);
				ResultSet rs = stm.executeQuery("Select * from employee where idemployee = "+idemployee+";");
				while(rs.next()) {
					Employee.employees.remove(idemployee);
					new Employee(rs.getInt(1),rs.getString(2),rs.getString(3),Ocupation.ocupations.get(rs.getInt(4)),rs.getString(5));
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
	public static void selectEmployee(JComboBox<?> Box, DefaultTableModel employeesDtm) {
		Statement stm = null;
		String query = null;
		if(connection != null) {
			try {
				stm = connection.createStatement();
				if(Box.getSelectedIndex()==0 || Box.getSelectedItem().equals("Employees"))
					query = "Select * from employee";
				else
					query = "Select * from employee where idemployee = "+Box.getSelectedItem()+";";
				ResultSet rs = stm.executeQuery(query);
				employeesDtm.setRowCount(0);
				while(rs.next()) {
					Object[] newRow = {rs.getInt(1),rs.getString(2),rs.getString(3),Ocupation.ocupations.get(rs.getInt(4)).getOname(),
							rs.getString(5)};
					employeesDtm.addRow(newRow);
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
	public static void deleteEmployee(int idemployee) {
		
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
