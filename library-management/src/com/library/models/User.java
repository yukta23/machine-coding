package com.library.models;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Objects;

public class User {
	private int id;
	private String name;
	private HashMap<Integer, LocalDate> booksBorrowed; // BookCopyId of book borrowed, due date
	public User(int userId) {
		this.id = userId;
		this.name = "DEFAULT";
		this.booksBorrowed = new HashMap<>();
	}
	public User(int userId, String name) {
		this(userId);
		this.name = name;
	}
	public int getId() {
		return id;
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
