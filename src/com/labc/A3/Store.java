package com.labc.A3;

import javax.swing.JFrame;

public class Store implements Runnable{

	@Override
	public void run() {
		Provider.innitProviders();
		Client.innitClients();
		Product.innitProducts();
		Ocupation.innitOcupations();
		Employee.innitEmployees();
	}
	
	public void start() {
		System.out.println("Initializing store...");
		Thread store = new Thread(this);
		store.start();
	}
}
