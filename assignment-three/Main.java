package com.elemica.assignment.three;

import java.util.Scanner;

public class Main {
	public static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		Heirarchy family = new Heirarchy();
		String option = new String();
		do {
			try {
				System.out.println("Select valid option number:\n"
						+ "1 - Add Person\n"
						+ "2 - Add Relation\n"
						+ "3 - Delete Relation\n"
						+ "4 - Get Parent\n"
						+ "5 - Get Children\n"
						+ "6 - Get Ancestors\n"
						+ "7 - Get Descendents\n");
				option = scanner.nextLine();
				
				switch (option) {
				case "1":
					family.addNode();
					break;
				case "2":
					family.addDependency();
					break;
				case "3":
					family.deleteDependency();
					break;
				case "4":
					family.getParent();
					break;
				case "5":
					family.getChildren();
					break;
				case "6":
					System.out.println("Enter descendent id: ");
					String dId = scanner.nextLine();
					System.out.println("Ancestors:");
					family.getAncestors(dId);
					break;
				case "7":
					System.out.println("Enter ancestor id: ");
					String aId = scanner.nextLine();
					System.out.println("Descendents:");
					family.getDescendents(aId);
					break;
				default:
					System.out.println("Invalid option. Exiting program");
					option = new String("EXIT");
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}while(!option.equals("EXIT"));
		
		scanner.close();
	}

}
