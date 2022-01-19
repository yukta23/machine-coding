package com.library.models;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Objects;

public class User {
	private int id;
	private String name;
	// bookCopyId, due date
	private HashMap<Integer, LocalDate> booksBorrowed;
	public User(int id) {
		this.id = id;
		this.name = "NA";
		this.booksBorrowed = new HashMap<>();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public HashMap<Integer, LocalDate> getBooksBorrowed() {
		return booksBorrowed;
	}
	public void setBooksBorrowed(HashMap<Integer, LocalDate> booksBorrowed) {
		this.booksBorrowed = booksBorrowed;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return id == other.id;
	}
}
