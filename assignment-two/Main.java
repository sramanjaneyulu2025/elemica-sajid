package com.elemica.assignment.two;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	protected static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args){
		User.loadFile();
		String option = "";
	
		do {
			System.out.println("Choose your option.\n"
					+ "1 - Add User Details\n"
					+ "2 - Display User Details\n"
					+ "3 - Delete User Details\n"
					+ "4 - Save User Details\n"
					+ "5 - Exit");
			
			try {
				option = scanner.nextLine();
				switch (option) {
					case "1":
						User.addUser();
						break;
					case "2":
						User.displayUserDetails();
						break;
					case "3":
						User.deleteUser();
						break;
					case "4":
						User.saveUsers();
						break;
					case "5":
						System.out.println("Do you want to save your changes? (y/n)\n");
						String saveChange = scanner.nextLine();
						if(saveChange.equals("y")) {
							User.saveUsers();
						}
						break;
					default:
						System.out.println("Exiting as an invalid option was chosen.");
						option = "5";
						break;
				
				}
			}catch(Exception e) {
				 System.err.println(e.getMessage());
			}
		}while(!(option.equals("5")));
		
		scanner.close();
	}

}
