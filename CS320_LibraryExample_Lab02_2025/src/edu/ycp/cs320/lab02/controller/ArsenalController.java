package edu.ycp.cs320.lab02.controller;

import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.booksdb.model.Author;
import edu.ycp.cs320.booksdb.model.Ball;
import edu.ycp.cs320.booksdb.model.Book;
import edu.ycp.cs320.booksdb.model.Pair;
import edu.ycp.cs320.booksdb.persist.DatabaseProvider;
import edu.ycp.cs320.booksdb.persist.DerbyDatabase;
import edu.ycp.cs320.booksdb.persist.IDatabase;

public class ArsenalController {

	private IDatabase db = null;

	public ArsenalController() {
		
		// creating DB instance here
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();		
	}

	public ArrayList<Ball> getAllBalls() {
		
		// get the list balls from arsenal db
		ArrayList<Ball> arsenal = db.findAllBalls();
		
		if (arsenal.isEmpty()) {
			System.out.println("No balls in arsenal");
			return null;
		}
		else {
			//books = new ArrayList<Book>();
			for (Ball ball : arsenal) {
				//Author author = authorBook.getLeft();
				//Book book = authorBook.getRight();
				//books.add(book);
				System.out.println(ball.getLongname() + ", " + ball.getShortname());
			}			
		}
		
		// return arsenal of balls
		return arsenal;
	}
}

