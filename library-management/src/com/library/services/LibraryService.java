package com.library.services;

import java.time.LocalDate;
import java.util.ArrayList;

import com.library.models.Book;

public interface LibraryService {
	void createLibrary(int libId, int numRacks);
	void addBook(int bookId, String title, ArrayList<String> authors, 
			ArrayList<String> publishers, ArrayList<Integer> bookCopyIds);
	void removeBookCopyId(int bookCopyId);
	void borrowBookCopy(int bookCopyId, int userId, LocalDate dueDate);
	void returnBookCopy(int bookCopyId);
	void printBorrowed(int userId);
	Book searchByBookId(int bookId);
	ArrayList<Book> searchByAuthor(String author);
	ArrayList<Book> searchByPublisher(String publisher);
}
