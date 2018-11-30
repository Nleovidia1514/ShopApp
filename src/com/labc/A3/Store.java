package com.labc.A3;

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
		new Thread(this).start();;
	}
}
