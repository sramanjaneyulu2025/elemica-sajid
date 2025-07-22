package com.elemica.assignment.two;

import java.io.Serializable;

public class Address implements Serializable, Comparable<Address> {
	private static final long serialVersionUID = 2L;
	
	private String houseName, city, pinCode;

	public String getHouseName() {
		return houseName;
	}

	public String getCity() {
		return city;
	}

	public String getPinCode() {
		return pinCode;
	}
	
	
	
	public Address(String houseName, String city, String pinCode) throws Exception {
		Address.validateDetails(houseName, city, pinCode);
		this.houseName = houseName.replaceAll("[\\t ]+", " ").trim();
		this.city = city.replaceAll("[\\t ]+", " ").trim();
		this.pinCode = pinCode;
	}

	public static void validateDetails(Address address) throws Exception {
		validateDetails(address.getHouseName(), address.getCity(), address.getPinCode());
		
	}
	
	public static void validateDetails(String houseName, String city, String pinCode) throws Exception {
		if(!houseName.matches("^(?=.*[A-Za-z0-9])[A-Za-z0-9 \\-]+$")) {
			throw new Exception("House name should be non-empty alphanumeric");
		}
		if(!city.matches("^(?=.*[A-Za-z])[A-Za-z ]+$")) {
			throw new Exception("City name should be non-empty alphabet");
		}
		if(!pinCode.matches("^\\d{6}$")) {
			throw new Exception("PIN Code must be a 6 digit number");
		}
	}
	
	@Override
	public int compareTo (Address a2) {
		String s1 = this.getHouseName() + this.getCity() + this.getPinCode();
		String s2 = a2.getHouseName() +  a2.getCity() + a2.getPinCode();
		return s1.compareTo(s2);
	}
	
	public String stringForPrint() {
		return "House Name::" + this.getHouseName() + "  City::" + this.getCity() + "  PIN Code::" + this.getPinCode();
	}
	
	
	
}
