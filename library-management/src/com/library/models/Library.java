package com.library.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Library {
	private int id;
	private int num_racks;
	// each rack stores bookId not bookCopyId
	private ArrayList<HashSet<Integer>> racks; 
	private HashMap<Integer, User> users;
	// hash code and equals already overridden for Book objects in order to facilitate comparison
	
	public Library(int id, int num_racks) {
		this.id = id;
		this.num_racks = num_racks;
		this.racks = new ArrayList<>();
		this.users = new HashMap<>();
		for( int i = 0; i < num_racks; i++ ) {
			this.racks.add(new HashSet<Integer>());
		}
	}
	public int getId() { // Only getter for this
		return id;
	}
	public int getNum_racks() {
		return this.num_racks;
	}
	public ArrayList<HashSet<Integer>> getRacks() {
		return racks;
	}
	public void setRacks(ArrayList<HashSet<Integer>> racks) {
		this.racks = racks;
	}
	public HashMap<Integer, User> getUsers() {
		return users;
	}
	public void setUsers(HashMap<Integer, User> users) {
		this.users = users;
	}
}
