package com.elemica.assignment.one;

public class ImportedItem extends Item {
	
	private double importDuty, surcharge;
	
	public double getImportDuty() {return this.importDuty;}
	public void setImportDuty(double importDuty){this.importDuty = importDuty;}
	
	public double getSurcharge() {return this.surcharge;}
	public void setSurcharge(double surcharge){this.surcharge = surcharge;}
	
	public ImportedItem(String name, double price) {
		this.setName(name);
		this.setPrice(price);
		
		//calculating Sales tax upon object creation itself.
		this.calculateSalesTax();
	}
	public ImportedItem(String name, double price, int quantity) {
		this.setName(name);
		this.setPrice(price);
		this.setQuantity(quantity);
		
		//calculating Sales tax upon object creation itself.
			this.calculateSalesTax();
	}
	public void calculateSalesTax() {
		this.setImportDuty(0.1 * this.getPrice());
		this.setSalesTaxLiability(0.125 * this.getPrice());
		double dutyAndTaxAddedCost = this.getImportDuty() + this.getSalesTaxLiability() + this.getPrice(); // 12.5% tax + 10% import duty
		
		if(dutyAndTaxAddedCost > 200) {this.setSurcharge(0.05*dutyAndTaxAddedCost);}
		else if(dutyAndTaxAddedCost > 100) {this.setSurcharge(10);}
		else {this.setSurcharge(5);}
		
		this.setFinalPrice(this.getPrice() + this.getSalesTaxLiability() + this.getImportDuty() + this.getSurcharge());
		
	}
}
