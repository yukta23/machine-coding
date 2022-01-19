package com.library.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import com.library.models.Book;
import com.library.services.LibraryService;
import com.library.services.LibraryServiceImpl;

public class Main {

	public static LibraryServiceImpl service = new LibraryServiceImpl();

	public static void main(String[] args) {
		ArrayList<String> commands = new ArrayList<String>() {
			{
				add("create_library");
				add("add_book");
				add("remove_book_copy");
				add("borrow_book_copy");
				add("return_book_copy");
				add("print_borrowed");
				add("search");
			}
		};
		ArrayList<String> commandStatement = new ArrayList<>();
		String prevArg = "";
		for(String arg : args ) {
			// command arg
			if (search(commands, arg)) {
				if (commandStatement.size() != 0 )
				{
					processCommand(prevArg, commandStatement);
				}
				prevArg = arg;
				commandStatement.clear();
			} else 
			{
				commandStatement.add(arg);
			}
		}
		if (commandStatement.size() != 0 )
		{
			processCommand(prevArg, commandStatement);
		}

	}
	public static boolean search(ArrayList<String> commands,String arg) {
		for( int i = 0; i < commands.size(); i++ ) {
			if (commands.get(i).equalsIgnoreCase(arg))
				return true;
		}
		return false;
	}
	public static void processCommand(String command, ArrayList<String> statement) {
		switch(command) {
			case "create_library": {
				service.createLibrary(Integer.parseInt(statement.get(0)), Integer.parseInt(statement.get(1)));
				break;
			}
			case "add_book": {
				String[] authors = statement.get(2).split(",");
				String[] pubs = statement.get(3).split(",");
				String[] ids = statement.get(4).split(",");
				ArrayList<Integer> copyIds = new ArrayList<>();
				for(String id : ids ) {
					copyIds.add(Integer.parseInt(id));
				}
				service.addBook(Integer.parseInt(statement.get(0)), statement.get(1), 
						new ArrayList<String>(Arrays.asList(authors)),
						new ArrayList<String>(Arrays.asList(pubs)), copyIds);
				break;
			}
			case "remove_book_copy": {
				service.removeBookCopyId(Integer.parseInt(statement.get(0)));
				break;
			}
			case "borrow_book_copy": {
				service.borrowBookCopy(Integer.parseInt(statement.get(0)), 
						Integer.parseInt(statement.get(1)), LocalDate.parse(statement.get(2)) );
				service.printAllUsers();
				break;
			}
			case "search": {
				String searchFactor = statement.get(0);
				String searchVal = statement.get(1);
				ArrayList<Book> books = new ArrayList<>();
				Book bk;
				if (searchFactor.equalsIgnoreCase("book_id")) {
					bk = service.searchByBookId(Integer.parseInt(searchVal));
					System.out.println("Book: " + bk.getId() + " " + bk.getTitle());
					return;
				} else if (searchFactor.equalsIgnoreCase("author")) {
					books = service.searchByAuthor(searchVal);
				} else {
					books = service.searchByPublisher(searchVal);
				}
				for(Book book : books) {
					System.out.println(book.getId() + " " + book.getTitle());
				}
				break;
			}
		}
	}
}
