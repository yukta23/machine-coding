package com.library.models;

import java.util.ArrayList;
import java.util.Objects;

public class Book {
	private int id;
	private String title;
	private ArrayList<String> authors;
	private ArrayList<String> publishers;
	private ArrayList<Integer> bookCopyIds;
	
	public Book(int id, String title, ArrayList<String> authors, ArrayList<String> publishers,
			ArrayList<Integer> bookCopyIds) {
		this.id = id;
		this.title = title;
		this.authors = authors;
		this.publishers = publishers;
		this.bookCopyIds = bookCopyIds;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public ArrayList<String> getAuthors() {
		return authors;
	}
	public void setAuthors(ArrayList<String> authors) {
		this.authors = authors;
	}
	public ArrayList<String> getPublishers() {
		return publishers;
	}
	public void setPublishers(ArrayList<String> publishers) {
		this.publishers = publishers;
	}
	public ArrayList<Integer> getBookCopyIds() {
		return bookCopyIds;
	}
	public void setBookCopyIds(ArrayList<Integer> bookCopyIds) {
		this.bookCopyIds = bookCopyIds;
	}
	
	// As you are using HashSet the add method behind the scene will call the equals() method and 
	// will also call hashCode() to decide the bucket in which the new object should be put. 
	// To maintain the contract of hashCode() and equals() Both should be overridden.
	// When ever you override equals(), it is recommended to override hashCode()
	// 
	// Contract for hashCode() and equals(): 
	// If for two objects say o1 and o2, o1.equals(o2) is true then hash of o1 and o2 should be same.
	// However, it is possible that for 2 objects, their hash is same but the o1.equals(o2) returns false!!
	
	@Override
	public int hashCode() {
		return Objects.hash(authors, title);
	}
	
	//java.util.Set compares two objects based on equals() method
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(authors, other.authors) && Objects.equals(title, other.title);
	}
}
