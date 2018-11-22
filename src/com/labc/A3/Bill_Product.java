package com.labc.A3;

import java.sql.Connection;

public class Bill_Product {
	private static Connection connection = ConnManager.getConnection();
	private Bill bill;
	private Product product;
	private int quantity;
	private double total;
	
	public Bill_Product(Bill bill, Product product) {
		this.bill = bill;
		this.product = product;
		this.setQuantity(1);
		this.setTotal(this.product.getSellprice());
		this.bill.productsInBill.put(this.product.getIdproduct(),this);
	}
	
	public static void addProducts(int idproduct, Bill bill) {
		if(bill.productsInBill.get(idproduct) == null) {
			Bill_Product idk = new Bill_Product(bill,Product.products.get(idproduct));
			bill.productsToBuy.add(idk);
			Main.total = Main.total+(idk.total+(idk.total*Bill.TAX));
			Main.totalPane.setText("TOTAL:\n\t"+Main.total);
		}
		else {
			bill.productsInBill.get(idproduct).setQuantity(bill.productsInBill.get(idproduct).getQuantity() + 1);
			bill.productsInBill.get(idproduct).setTotal(bill.productsInBill.get(idproduct).getTotal() + bill.productsInBill.get(idproduct).product.getSellprice());
			Main.total = Main.total+(bill.productsInBill.get(idproduct).getProduct().getSellprice()+(bill.productsInBill.get(idproduct).getProduct().getSellprice()*Bill.TAX));
			Main.totalPane.setText("TOTAL:\n\t"+Main.total);
		}
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public Product getProduct() {
		return this.product;
	}
	
	public Bill getBill() {
		return this.bill;
	}
}
