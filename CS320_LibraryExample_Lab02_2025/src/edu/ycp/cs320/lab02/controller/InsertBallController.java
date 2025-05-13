package edu.ycp.cs320.lab02.controller;

import edu.ycp.cs320.booksdb.persist.DatabaseProvider;
import edu.ycp.cs320.booksdb.persist.DerbyDatabase;
import edu.ycp.cs320.booksdb.persist.IDatabase;

public class InsertBallController {

	private IDatabase db = null;

	public InsertBallController() {
		
		// creating DB instance here
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();		
	}

	public boolean insertGame(String league, String season, String week, String date, String game, String lane) {
		
		// insert new book (and possibly new author) into DB
		Integer game_id = db.insertGame(league, season, week, date, game, lane);

		// check if the insertion succeeded
		if (game_id > 0) {
			System.out.println("New Game (ID: " + game_id + ") successfully added to Games table");
			return true;
			
		} else {
			System.out.println("Failed to insert new game (ID: " + game_id + ") into Games table");
			return false;
		}
	}
}
