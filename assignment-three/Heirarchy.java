package com.elemica.assignment.three;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public final class Heirarchy {
	private Map<String, Node> idsTaken = new HashMap<String,Node>();
	private Map<Node, HashSet<Node>> children = new HashMap<Node, HashSet<Node>>();
	private Map<Node, HashSet<Node>> parents = new HashMap<Node, HashSet<Node>>();
	
	public void addNode() throws Exception{
		System.out.println("Enter node name: ");
		String name = Main.scanner.nextLine();
		System.out.println("Enter id: ");
		String id = Main.scanner.nextLine();
		
		if((!(idsTaken.get(id) == null))) throw new Exception("ID already taken!");
		Node node = new Node(id, name);
		idsTaken.put(id, node);
		children.put(node, new HashSet<>());
	    parents.put(node, new HashSet<>());
	}
	
	public void addDependency() throws Exception{
		System.out.println("Enter parent id: ");
		String pId = Main.scanner.nextLine();
		if(idsTaken.get(pId) == null) throw new Exception("Parent does not exist");
		
		System.out.println("Enter child id: ");
		String cId = Main.scanner.nextLine();
		if(idsTaken.get(cId) == null) throw new Exception("Child does not exist");
		
		
		checkForCycle(pId, cId);
		
		children.get(idsTaken.get(pId)).add(idsTaken.get(cId));
		parents.get(idsTaken.get(cId)).add(idsTaken.get(pId));
	}
	
	public void deleteDependency() throws Exception{
		System.out.println("Enter parent id: ");
		String pId = Main.scanner.nextLine();
		if(idsTaken.get(pId) == null) throw new Exception("Parent does not exist");
		
		System.out.println("Enter child id: ");
		String cId = Main.scanner.nextLine();
		if(idsTaken.get(cId) == null) throw new Exception("Child does not exist");
		
		children.get(idsTaken.get(pId)).remove(idsTaken.get(cId));
		parents.get(idsTaken.get(cId)).remove(idsTaken.get(pId));
	}
	
	public void getParent() throws Exception{
		System.out.println("Enter child id: ");
		String cId = Main.scanner.nextLine();
		if(idsTaken.get(cId) == null) throw new Exception("Child does not exist");
		
		parents.get(idsTaken.get(cId)).forEach(parent -> {
			System.out.println("Parent details (id, name): " + parent.getId() + ", " + parent.getName());
		});
				
	}
	
	public void getChildren() throws Exception{
		System.out.println("Enter parent id: ");
		String pId = Main.scanner.nextLine();
		if(idsTaken.get(pId) == null) throw new Exception("Parent does not exist");
				
		children.get(idsTaken.get(pId)).forEach(child -> {
			System.out.println("Child details (id, name): " + child.getId() + ", " + child.getName());
		});
	}
	
	public void getAncestors(String cId) throws Exception{
		if(idsTaken.get(cId) == null) throw new Exception("Descendent does not exist");	
		
		parents.get(idsTaken.get(cId)).forEach(parent -> {
			try{
				System.out.println(parent.getId() + ", " + parent.getName());
				this.getAncestors(parent.getId());
			}catch (Exception e) {
				e.printStackTrace();
			}
		});
				
	}
	
	public void getDescendents(String pId) throws Exception{
		if(idsTaken.get(pId) == null) throw new Exception("Ancestor does not exist");
		
		children.get(idsTaken.get(pId)).forEach(child -> {
			try{
				System.out.println(child.getId() + ", " + child.getName());
				this.getDescendents(child.getId());
			}catch (Exception e) {
				e.printStackTrace();
			}
		});
				
	}
	
	private void checkForCycle(String pId, String cId) throws Exception{
		if(checkIsDescendent(cId, pId)) throw new Exception ("Dependency impossible.");
	}
	
	private Boolean checkIsDescendent(String pId, String cId) {
		AtomicBoolean answer = new AtomicBoolean(false);
		children.get(idsTaken.get(pId)).forEach(child -> {
			if(child.getId().equals(cId)) answer.set(true);
			else answer.set(answer.get() | checkIsDescendent(child.getId(), cId));
		});
		 return answer.get();
	}
}
