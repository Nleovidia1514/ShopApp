package com.labc.A3;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class Product {
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
			}
		}
	}
	/*if(connection!=null) {
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
		}*/

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
