package com.elemica.assignment.one;

public abstract class Item {
	private String name;
	private 	double price;
	private int quantity;
	private double salesTaxLiability;
	private double finalPrice;
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public double getSalesTaxLiability() {
		return salesTaxLiability;
	}


	public void setSalesTaxLiability(double salesTaxLiability) {
		this.salesTaxLiability = salesTaxLiability;
	}


	public double getFinalPrice() {
		return finalPrice;
	}


	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}


	public abstract void calculateSalesTax();
	
	public void printOutput() {
		System.out.println("Name::" + this.getName() +
				"  Price::" + this.getPrice() +
				"  Sales_Tax_Liability::" + this.getSalesTaxLiability() +
				" Final Price::" + this.getFinalPrice());
	}
	
	
	
}
