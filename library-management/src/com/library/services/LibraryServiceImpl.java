package com.library.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import com.library.models.Book;
import com.library.models.Library;
import com.library.models.Rack;
import com.library.models.User;

public class LibraryServiceImpl implements LibraryService {
private Library lib;
	
	@Override
	public void createLibrary(int libId, int numRacks) {
		lib = new Library(libId, numRacks);
		System.out.println("New library created with id " + lib.getId());
	}

	@Override
	public void addBook(int bookId, String title, ArrayList<String> authors, ArrayList<String> publishers,
			ArrayList<Integer> bookCopyIds) {
		Book book = new Book(bookId, title, authors, publishers, bookCopyIds);
		HashMap<Integer, Book> libBooks = lib.getBooks();
		libBooks.put(bookId, book);
		lib.setBooks(libBooks);
		this.printAllBooks();
		// now place the bookIds on the racks as well such that no rack has duplicate bookId
		for(int i = 0; i < bookCopyIds.size(); i++ ) {
			this.placeBookCopyOnRack(bookId, bookCopyIds.get(i));
		}
//		this.printAllBooks();
	}

	@Override
	public void removeBookCopyId(int bookCopyId) {
		ArrayList<Rack> racks = lib.getRacks();
		int bookId = this.getBookIdOfCopy(bookCopyId);
		int i = 0;
		for(Rack rack : racks) {
			HashSet<Integer> bookIds = rack.getBookIds();
			HashSet<Integer> bookCopyIds = rack.getBookCopyIds();
			if (bookCopyIds.contains(bookCopyId)) {
				// remove bookId as well from the rack tracker
				bookIds.remove(bookId);
				bookCopyIds.remove(bookCopyId);
				System.out.println(bookCopyId + " removed from Rack " + (i+1) );
				break;
			}
			i++;
		}
		lib.setRacks(racks);
	}

	@Override
	public void borrowBookCopy(int bookCopyId, int userId, LocalDate dueDate) {
		
		int bookId = this.getBookIdOfCopy(bookCopyId);
		if(bookId == -1) {
			System.out.println("Book copy dne");
			return;
		}
		HashMap<Integer, User> users = lib.getUsers();
		User user;
		if(!users.containsKey(userId)) {
			// create new user if user does not already exists
			System.out.println("New User: Welcome !!");
			user = new User(userId);
			users.put(userId, user);
		} else {
			user = users.get(userId);
		}
		HashMap<Integer, LocalDate> booksBorrowed = user.getBooksBorrowed();
		if (booksBorrowed.containsKey(bookCopyId)) {
			System.out.println("Book already borrowed");
			return;
		} else if (booksBorrowed.size() == 5 ) {
			System.out.println("Overlimit");
			return;
		}
		else {
			booksBorrowed.put(bookCopyId, dueDate);
			user.setBooksBorrowed(booksBorrowed);
		}
		users.replace(userId, user);
		lib.setUsers(users);
	}

	@Override
	public void returnBookCopy(int bookCopyId) {
		//remove from User's borrowed list
		//place back on the rack
		int bookId = this.getBookIdOfCopy(bookCopyId);
		if (bookId != -1)
			this.placeBookCopyOnRack(bookId, bookCopyId);
	}

	@Override
	public void printBorrowed(int userId) {
		HashMap<Integer, User> users = lib.getUsers();
		if (!users.containsKey(userId)) {
			System.out.println("User doe not exist");
			return;
		}
		User user = users.get(userId);
		System.out.println("For user: " + user.getId() + " " + user.getName());
		for(Map.Entry<Integer, LocalDate> mapElement : user.getBooksBorrowed().entrySet()) {
			int bookCopyId = (int)mapElement.getKey();
			LocalDate duedate = (LocalDate)mapElement.getValue();
			System.out.println(bookCopyId + " - " + duedate.toString());
		}
	}

	@Override
	public Book searchByBookId(int bookId) {
		HashMap<Integer, Book> books = lib.getBooks();
		if (books.containsKey(bookId))
			return books.get(bookId);
		return null;
	}

	@Override
	public ArrayList<Book> searchByAuthor(String author) {
		HashMap<Integer, Book> books = lib.getBooks();
		ArrayList<Book> booksList = new ArrayList<Book>();
		for(Map.Entry<Integer, Book> mapElement : books.entrySet()) {
			int bookId = (int)mapElement.getKey();
			Book b = (Book)mapElement.getValue();
			ArrayList<String> authors = b.getAuthors();
			//given author is present in authors list of the book
			if (authors.indexOf(author) != -1) {
				booksList.add(b);
			}
		}
		return booksList;
	}

	@Override
	public ArrayList<Book> searchByPublisher(String publisher) {
		HashMap<Integer, Book> books = lib.getBooks();
		ArrayList<Book> booksList = new ArrayList<Book>();
		for(Map.Entry<Integer, Book> mapElement : books.entrySet()) {
			int bookId = (int)mapElement.getKey();
			Book b = (Book)mapElement.getValue();
			ArrayList<String> publishers = b.getPublishers();
			//given author is present in authors list of the book
			if (publishers.indexOf(publisher) != -1) {
				booksList.add(b);
			}
		}
		return booksList;
	}
	
	private int getBookIdOfCopy(int bookCopyId) {
		HashMap<Integer, Book> books = lib.getBooks();
		for(Map.Entry<Integer, Book> mapElement : books.entrySet()) {
			int bookId = (int)mapElement.getKey();
			Book book = (Book)mapElement.getValue();
			ArrayList<Integer> bookCopyIds = book.getBookCopyIds();
			if(bookCopyIds.indexOf(bookCopyId) != -1)
				return bookId;
		}
		return -1;
	}
	
	private void placeBookCopyOnRack(int bookId, int bookCopyId) {
		ArrayList<Rack> racks = lib.getRacks();
		for( int j = 0; j < lib.getNumRacks(); j++ ) {
			Rack rack = racks.get(j);
			if(!rack.getBookIds().contains(bookId)) {
				HashSet<Integer> bookIds = rack.getBookIds();
				HashSet<Integer> bookCopyIds = rack.getBookCopyIds();
				bookIds.add(bookId);
				bookCopyIds.add(bookCopyId);
				rack.setBookCopyIds(bookCopyIds);
				rack.setBookIds(bookIds);
				System.out.println(bookCopyId + " placed on rack " + (j + 1));
				lib.setRacks(racks);
				return;
			}
		}
	}
	
	public void printAllUsers() {
		HashMap<Integer, User> users = lib.getUsers();
		for(Map.Entry<Integer, User> mapElement : users.entrySet()) {
			int userId = (int)mapElement.getKey();
			User user = (User)mapElement.getValue();
			System.out.println(user.getId() + " " + user.getName());
			HashMap<Integer, LocalDate> books = user.getBooksBorrowed();
			for(Map.Entry<Integer, LocalDate> element : books.entrySet()) {
				System.out.println(element.getKey() + " " + element.getValue().toString());
			}
		}
	}
	
	public void printAllBooks() {
		HashMap<Integer, Book> books = lib.getBooks();
		for(Map.Entry<Integer, Book> mapElement : books.entrySet()) {
			Book book = (Book)mapElement.getValue();
			System.out.println(book.getId() + " " + book.getTitle());
		}
	}
}
