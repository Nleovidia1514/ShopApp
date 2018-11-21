package com.labc.A3;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Product {
	private static Connection connection = ConnManager.getConnection();
	public Product(long productID, Bill bill) {
		Statement stm = null;
		String query = String.format("Select * from Detail where IdBill = %d and idProduct = %d",bill.billNmbr, productID);
		if(connection!=null) {
			if(checkIfProductExists(productID)) {
				try {
					stm = connection.createStatement();
					ResultSet rs = stm.executeQuery(query);
					if(!rs.next()) {
						query = String.format("Insert into Detail values(%d,%d,1,(Select SellPrice from Product where IdProduct = %d"
								+ "),(Select SellPrice from Product where IdProduct = %d)) ",bill.billNmbr,productID,productID,productID);
						stm.execute(query);
					}
					else {
						query = String.format("Update Detail set Quantity = Quantity + 1, total = (Quantity+1)*sellprice where IdProduct = %d "
								+ "and IdBill = %d",productID,bill.billNmbr);
						stm.execute(query);
					}
					query = String.format("Select * from Detail,Product where IdBill = %d and Detail.idProduct = %d "
							+ "and Product.idProduct = %d",bill.billNmbr, productID,productID);
					rs = stm.executeQuery(query);
					while(rs.next()) {
						String productDesc = rs.getString("DescriptionP");
						int productPrice = rs.getInt("SellPrice");
						int productQuantity = rs.getInt("Quantity");
						int totalPrice = rs.getInt("Total");
						Main.productsArea.append(String.format("\n%1$-30s %2$-30s %3$-30s %4$-30s",productDesc,productPrice,productQuantity,totalPrice));
					}
				} catch (SQLException e) {
					e.printStackTrace();;
				}finally {
					if(stm!=null) {
						try {stm.close();}catch(Exception e) {e.printStackTrace();}
					}
				}
			}
		}
	}
	
	private boolean checkIfProductExists(long productID) {
		Statement stm = null;
		String query = String.format("Select * from Product where IdProduct = %d", productID);
		try {
			stm = connection.createStatement();
			ResultSet rs = stm.executeQuery(query);
			if(rs.next())
				return true;
			else
				JOptionPane.showMessageDialog(Main.frame, "Product does not exists.", "Error",JOptionPane.ERROR_MESSAGE);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(stm!=null) {
				try {
					stm.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
}
