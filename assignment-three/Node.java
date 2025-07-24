package com.elemica.assignment.three;

import java.util.HashMap;
import java.util.Map;

public class Node {
	private String id, name;
	private Map<String, String> info = new HashMap<String,String>();
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInfo(String key) {
		return info.get(key);
	}
	public void addInfo(String key, String value) {
		info.put(key, value);
	}
	public Node(String id, String name) throws Exception {
		validate(id, name);
		this.id = id;
		this.name = name;
	}
	
	public void validate(String id, String name) throws Exception {
	    if (id == null || id.trim().isEmpty())
	        throw new Exception("ID must not be empty.");

	    if (name == null || name.trim().isEmpty())
	        throw new Exception("Name must not be empty.");

	    if (!id.matches("^[a-zA-Z0-9 ]+$"))
	        throw new Exception("ID must contain only letters, digits, and spaces.");

	    if (!name.matches("^[a-zA-Z ]+$"))
	        throw new Exception("Name must contain only letters and spaces.");
	}
	
	
	
	
}
