package com.library.models;

import java.util.ArrayList;
import java.util.HashMap;

public class Library {
	private int id;
	private int numRacks;
	// stores bookIds NOT bookCopyIds which should not repeat per rack
	private ArrayList<Rack> racks;
	private HashMap<Integer, User> users; // to keep track of all users
	private HashMap<Integer, Book> books; // to keep track of all books
	
	public Library(int id, int numRacks) {
		this.id = id;
		this.numRacks = numRacks;
		this.racks = new ArrayList<>();
		this.users = new HashMap<>();
		this.books = new HashMap<>();
		for(int i = 0; i < numRacks; i++ ) { // Important
 			this.racks.add(new Rack());
		}
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumRacks() {
		return numRacks;
	}
	public void setNumRacks(int numRacks) {
		this.numRacks = numRacks;
	}
	public ArrayList<Rack> getRacks() {
		return racks;
	}
	public void setRacks(ArrayList<Rack> racks) {
		this.racks = racks;
	}
	public HashMap<Integer, User> getUsers() {
		return users;
	}
	public void setUsers(HashMap<Integer, User> users) {
		this.users = users;
	}
	public HashMap<Integer, Book> getBooks() {
		return books;
	}
	public void setBooks(HashMap<Integer, Book> books) {
		this.books = books;
	}
}
