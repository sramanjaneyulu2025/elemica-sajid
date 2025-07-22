package com.elemica.assignment.two;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class User implements Serializable, Comparable<User> {
	private static final long serialVersionUID = 1L;
	public static final String FILE_PATH = "records.txt";
	
	private static Map<Integer, Boolean> rollNoTaken = new HashMap<Integer, Boolean>();
	private static ArrayList<User> listOfUsers = new ArrayList<>();
	
	private String name; // no setter as name is not expected to change
	private Integer age, rollNo; // no setter for rollNo as roll number is not expected to change
	private Address address;
	private Set<String> courses;
	
	public String getName() {
		return name;
	}
	public Integer getRollNo() {
		return rollNo;
	}
	public Integer getAge() {
		return age;
	}
	public Address getAddress() {
		return address;
	}
	public Set<String> getCourses() {
		return courses;
	}
	
	public void setAge(Integer age) {
		this.age = age;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public void setCourses(Set<String> courses) {
		this.courses = courses;
	}
	
	public User(String name, String rollNoString, String ageString, Address address, Set<String> courses) throws Exception{
		User.validateDetails(name, rollNoString, ageString, address, courses);
		
		this.name = name.replaceAll("[\\t ]+", " ").trim();
		this.rollNo = Integer.parseInt(rollNoString);
		this.age = Integer.parseInt(ageString);
		this.courses = courses;
		this.address = address;
		rollNoTaken.put(rollNo, true);
	}
	
	public static void loadFile() {
		File file = new File(User.FILE_PATH);
		
		if(file.exists()) {
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
				User.listOfUsers = (ArrayList<User>) ois.readObject();
				
				User.listOfUsers.forEach(user ->{
					rollNoTaken.put(user.getRollNo(), true);
				});
			} catch (IOException | ClassNotFoundException e) { //ois is automatically closed here
                e.printStackTrace();
                listOfUsers = new ArrayList<>();
            }	
		}
		else {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                oos.writeObject(listOfUsers); // write empty list
                System.out.println("File not found. Created new file with empty user list.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
	
	public static void addUser() throws Exception{
		System.out.println("Enter User Details in the given, and follow the rules mentioned:");
		
		System.out.println("Enter name. Name cannot be empty and should only contain letters. Name = ");
		String name = Main.scanner.nextLine();
		
		System.out.println("Enter roll number. Roll number has to be a positive Integer. Roll Number = ");
		String rollNo = Main.scanner.nextLine();
		
		System.out.println("Enter age. Age has to be a positive Integer. Age = ");
		String age = Main.scanner.nextLine();
		
		System.out.println("Enter Address Details:");
		System.out.println("Enter house name. House name should be non-empty alphanumeric. House name = ");
		String houseName = Main.scanner.nextLine();
		System.out.println("Enter city. City name should be non-empty alphabet. City = ");
		String city = Main.scanner.nextLine();
		System.out.println("Enter PIN Code. PIN Code should be a 6 digit number");
		String pinCode = Main.scanner.nextLine();
		Address address = new Address(houseName, city, pinCode);
		
		System.out.println("Enter Course Details. A student must enroll in atleast 4 courses");
		Set<String> courses = new HashSet<>();
		String addCourse = "y";
		while(addCourse.equals("y")) {
			System.out.println("Enter Course name: A, B, C, D, E or F");
			courses.add(Main.scanner.nextLine());
			System.out.println("Do you want to add another course? (y/n)\n");
			addCourse = Main.scanner.nextLine();
		}
		
		User user = new User(name, rollNo, age, address, courses);
		
		listOfUsers.add(user);
	}
	
	public static void displayUserDetails() throws Exception{
			if(User.listOfUsers.size() == 0) throw new Exception("No Users to display");
			
			System.out.println("Sort in ascending or descending order? Type d for descending.");
			String reverseSort = Main.scanner.nextLine();
			
			System.out.println("Sort based on name (default), rollNo (r), age (a), address (d) ? (r/a/d) ");
			String sortFactor = Main.scanner.nextLine();
			
			switch (sortFactor)
			{
			case "r":
				User.listOfUsers.sort(Comparator.comparing(User::getRollNo));
				break;
			case "a":
				User.listOfUsers.sort(Comparator.comparing(User::getAge));
				break;
			case "d":
				User.listOfUsers.sort(Comparator.comparing(User::getAddress));
				break;
			default:
				Collections.sort(User.listOfUsers);
			
			}
			if(reverseSort.equals("d")) Collections.reverse(User.listOfUsers);
			
			System.out.println("------------------------------------------------------------------------------");
			System.out.println("Name\tRoll Number\tAge\t\tAddress\t\t\t\t\t\t\tCourses");
			System.out.println("------------------------------------------------------------------------------");
			User.listOfUsers.forEach(user -> System.out.println(user.getName() + "\t" + user.getRollNo()
			+ "\t\t" + user.getAge() + "\t" + user.getAddress().stringForPrint() +
			"\t\t" + user.printCourses()));
	}
	
	public static void deleteUser() throws Exception {
		System.out.println("Enter rollNo of student to delete: ");
		
		String rollNoString = Main.scanner.nextLine();
		if(!rollNoString.matches("^\\d+$")) {
			throw new Exception("Roll number has to be a positive Integer");
		}
		Integer rollNo = Integer.parseInt(rollNoString);
		
		if(!User.rollNoTaken.getOrDefault(rollNo, false)) {
			throw new Exception ("Roll number does not exist!");
		}
		else {
			User.listOfUsers.removeIf(user -> user.getRollNo().equals(rollNo));
			rollNoTaken.put(rollNo, false);
		}
		
	}
	
	public static void saveUsers() {
	    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(User.FILE_PATH))) {
	        oos.writeObject(listOfUsers);
	        System.out.println("Users saved to file.");
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	private static void validateDetails(String name, String rollNoString, String ageString, Address address, Set<String> courses) 
			throws Exception{
				//checking correct name;
				if (name == null || name.trim().isEmpty() || !name.matches("[A-Za-z\\t ]+")) {
				    throw new Exception("Name cannot be empty and should only contain letters");
				}
				if(!rollNoString.matches("^\\d+$")) {
					throw new Exception("Roll number has to be a positive Integer");
				}
				if(rollNoTaken.getOrDefault(Integer.parseInt(rollNoString), false)) {
					throw new Exception("Roll number has already been taken");
				}
				
				if(!ageString.matches("^\\d+$")) {
					throw new Exception("Age has to be a positive Integer");
				}
				if(courses.size() < 4) {
					throw new Exception("Need to enroll in atleast 4 courses.");
				}

				for(String course : courses) {
				    if (course.charAt(0) < 'A' || course.charAt(0) > 'F') {
				        throw new Exception("Invalid course. Valid courses are only A to F");
				    }
				}
	}
	
	@Override
	public int compareTo (User u2) { //sort by name, then by rollNo
		if(this.getName().equals(u2.getName())) return this.getRollNo().compareTo(u2.getRollNo());
			return this.getName().compareToIgnoreCase(u2.getName());
	}
	
	public String printCourses() {
		StringBuffer s = new StringBuffer();
		this.getCourses().forEach(course -> s.append(course + ", "));
		return s.substring(0, s.length() -2);
	}
	
}
