package com.elemica.assignment.one;

public class RawItem extends Item {
	
	public RawItem() {}
	
	public RawItem(String name, double price) {
		this.setName(name);
		this.setPrice(price);
		
		//calculating Sales tax upon object creation itself.
		this.calculateSalesTax();
	}
	public RawItem(String name, double price, int quantity) {
		this.setName(name);
		this.setPrice(price);
		this.setQuantity(quantity);
		
		//calculating Sales tax upon object creation itself.
		this.calculateSalesTax();
	}
	public void calculateSalesTax() {
		this.setSalesTaxLiability(0.125 * this.getPrice());
		this.setFinalPrice(this.getPrice() + this.getSalesTaxLiability());
	}
	
	
	
	
}
