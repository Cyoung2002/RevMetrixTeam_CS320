package edu.ycp.cs320.booksdb;

import java.util.List;
import java.util.Scanner;

import edu.ycp.cs320.booksdb.model.Author;
import edu.ycp.cs320.booksdb.model.Book;
import edu.ycp.cs320.booksdb.model.Pair;
import edu.ycp.cs320.booksdb.persist.DatabaseProvider;
import edu.ycp.cs320.booksdb.persist.IDatabase;

public class OverallGameAverageforEvent {
	public static void main(String[] args) throws Exception {
		
		Scanner keyboard = new Scanner(System.in);

		// Create the default IDatabase instance
		InitDatabase.init(keyboard);
		
		System.out.print("Enter an event's long name and date: ");
		String longname = keyboard.nextLine();
		String date = keyboard.nextLine();
		
		IDatabase db = DatabaseProvider.getInstance();
		List<Pair<Author, Book>> authorBookList = db.findAuthorAndBookByAuthorLastName(longname);
		if (authorBookList.isEmpty()) {
			System.out.println("No games found for this event <" + longname + ">");
		}
		else {
			for (Pair<Author, Book> authorBook : authorBookList) {
				Author author = authorBook.getLeft();
				Book book = authorBook.getRight();
				System.out.println(author.getLastname() + "," + author.getFirstname() + "," + book.getTitle() + "," + book.getIsbn());
			}			
		}

	}
}