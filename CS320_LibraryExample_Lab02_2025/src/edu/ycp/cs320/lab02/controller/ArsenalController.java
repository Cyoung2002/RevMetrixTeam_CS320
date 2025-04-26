package edu.ycp.cs320.lab02.controller;

import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.booksdb.model.Ball;
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

	public ArrayList< Ball> getArsenal() {
		
		// get the list of (Author, Book) pairs from DB
		List<Ball> authorList = db.findAllBalls();
		ArrayList<Ball> arsenal = null;
		
		if (authorList.isEmpty()) {
			System.out.println("No authors found in library");
			return null;
		}
		else {
			arsenal = new ArrayList<Ball>();
			for (Ball ball : arsenal) {
				arsenal.add(ball);
				System.out.println(ball.getLongname() + ", " + ball.getShortname());
			}			
		}
		
		// return arsenal for this title
		return arsenal;
	}
}

