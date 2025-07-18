package com.elemica.assignment.one;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		String nextItem = "y";
		
		
		do {
			System.out.println("Enter item details: ");
			try {
				String input = scanner.nextLine();
				List<String> arguments = CheckArgument.checkArgument(input);
				
				Map<String, String> optionMapper = OptionMapper.map(arguments);
				String type = optionMapper.get("-type");
				CheckType.checkType(type);
				
				String name = optionMapper.get("-name");
				double price = Double.parseDouble(optionMapper.get("-price"));
				int quantity = Integer.parseInt(optionMapper.get("-quantity"));
				Item item = new RawItem();
				
				switch (type) {
			    		case "raw":
			    			item = new RawItem(name, price, quantity);
			    			break;
			    		case "manufactured":
			    			item = new ManufacturedItem(name, price, quantity);
			    		case "imported":
			    			item = new ImportedItem(name, price, quantity);
				}
				item.calculateSalesTax();
				item.printOutput();
				
			}catch(Exception e) {
				 System.err.println("Input error: " + e.getMessage());
			}finally {
				System.out.println("Do you want to enter details of any other item (y/n):");
				nextItem = scanner.nextLine();
			}
		}while(nextItem.contentEquals("y"));
		
		scanner.close();
	}

}
