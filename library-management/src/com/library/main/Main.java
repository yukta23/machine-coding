package com.library.main;

import java.time.LocalDate;
import java.util.ArrayList;

import com.library.services.LibraryService;
import com.library.services.LibraryServiceImpl;

public class Main {

	public static void main(String[] args) {
		LibraryService library = new LibraryServiceImpl();
		library.createLibrary(145, 5);
		library.addBook(6, "Rich Dad Poor Dad", 
				new ArrayList<>(){{add("Robert Kiyosaki");}}, 
				new ArrayList<>() {
					{
						add("ABC");
						add("McGraw");
						add("New Publication");
					}
				}, 
				new ArrayList<>() {
					{
						add(1);
						add(2);
						add(3);
						add(4);
						add(5);
						add(6);
						add(7);
					}
				});
		library.borrowBookCopy(4, 126, LocalDate.now().plusWeeks(2));
		library.borrowBookCopy(5, 126, LocalDate.now().plusDays(5));
		library.printBorrowed(126);
		library.returnBookCopy(4);
		library.removeBookCopy(6);
		library.printBorrowed(126);
	}

}
