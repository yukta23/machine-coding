package com.library.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import com.library.models.Book;
import com.library.models.Library;
import com.library.models.User;

public class LibraryServiceImpl implements LibraryService {
	private Library lib;

	@Override
	public void createLibrary(int libId, int numRacks) {
		this.lib = new Library(libId, numRacks);
		System.out.println("New library created with Lib ID " + lib.getId());
	}

	@Override
	public void addBook(int bookId, String title, ArrayList<String> authors, ArrayList<String> publishers,
			ArrayList<Integer> bookCopyIds) {
		ArrayList<HashSet<Integer>> racks = lib.getRacks();
		Book book = new Book(bookId, title, authors, publishers, bookCopyIds);
		System.out.println("New Book collection created with book id " + book.getId());
		
		// bookId should be unique in each rack such that copies don't repeat
		for( int i = 0; i < bookCopyIds.size(); i++ ) {
			for( int j = 0; j < lib.getNum_racks(); j++ ) {
				HashSet<Integer> rack = racks.get(j);
				if (!rack.contains(bookId)) {
					System.out.println("Book copy id "+ bookCopyIds.get(i) + " added to rack "
				+ (j + 1));
					rack.add(bookId);
					break;
				}
			}
		}
	}

	@Override
	public void removeBookCopy(int bookCopyId) {
		ArrayList<HashSet<Integer>> racks = this.lib.getRacks();
		for( HashSet<Integer> rack : racks) {
			if (rack.contains(bookCopyId)) {
				rack.remove(bookCopyId);
			}
		}
	}

	@Override
	public void borrowBookCopy(int bookCopyId, int userId, LocalDate dueDate) {
		HashMap<Integer, User> users = lib.getUsers();
		User u;
		if (users.containsKey(userId)) {
			u = users.get(userId);
		} else {
			u = new User(userId);
			users.put(userId, u);
		}
		HashMap<Integer, LocalDate> borrowed = u.getBooksBorrowed();
		if (borrowed.containsKey(bookCopyId)) {
			System.out.println("Book already borrowed");
			return;
		} else {
			borrowed.put(bookCopyId, dueDate);
		}
		u.setBooksBorrowed(borrowed);
		users.replace(userId, u);
		lib.setUsers(users);
	}

	@Override
	public void returnBookCopy(int bookCopyId) {
		HashMap<Integer, User> users = lib.getUsers();
		for (Map.Entry<Integer, User> mapElement : users.entrySet()) {
			User user = (User)mapElement.getValue();
			HashMap<Integer, LocalDate> borrowed = user.getBooksBorrowed();
			if (borrowed.containsKey(bookCopyId)) {
				System.out.println("Book Copy Id: "+ bookCopyId +" returned");
				borrowed.remove(bookCopyId);
				user.setBooksBorrowed(borrowed);
				return;
			}
		}
	}

	@Override
	public void printBorrowed(int userId) {
		HashMap<Integer, User> users = lib.getUsers();
		if (users.containsKey(userId)) {
			User user = users.get(userId);
			this.printUser(user);
		} else {
			System.out.println("User doesn't exist ");
			return;
		}
	}
	
	private void printUser(User u) {
		System.out.println("Books borrowed by user: "+u.getName());
		HashMap<Integer, LocalDate> borrowed = u.getBooksBorrowed();
		for(Map.Entry<Integer, LocalDate> mapElement : borrowed.entrySet()) {
			Integer bookCopyId = (Integer)mapElement.getKey();
			LocalDate dueDate = (LocalDate)mapElement.getValue();
			System.out.println("Book Copy Id: " + bookCopyId);
			System.out.println("Due Date: "+ dueDate);
		}
	}
	
	private void printAllUsers() {
		HashMap<Integer, User> users = lib.getUsers();
		for (Map.Entry<Integer, User> mapElement : users.entrySet()) {
			Integer userId = (Integer)mapElement.getKey();
			User user = (User)mapElement.getValue();
			System.out.println(userId + " " + user.getName());
		}
	}
}
