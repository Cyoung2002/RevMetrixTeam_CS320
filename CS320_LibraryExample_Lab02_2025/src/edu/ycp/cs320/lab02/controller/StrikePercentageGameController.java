package edu.ycp.cs320.lab02.controller;

import edu.ycp.cs320.booksdb.persist.DatabaseProvider;
import edu.ycp.cs320.booksdb.persist.DerbyDatabase;
import edu.ycp.cs320.booksdb.persist.IDatabase;
import edu.ycp.cs320.booksdb.model.Shot;
import java.util.ArrayList;

public class StrikePercentageGameController {

	private IDatabase db = null;

	public StrikePercentageGameController() {
		
		// creating DB instance here
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();		
	}

	public Double StrikePercentageGame(String gameID) {
		
		// insert new book (and possibly new author) into DB
		ArrayList<Shot> shotList = db.findAllShotsInGame(gameID);
		Double numStrikes = 0.0;
		Double numFrames = 10.0;
		Double percentResult = 0.0;
		
		for (Shot shot : shotList) {
			System.out.println("Shot count: '" + shot.getCount() + "'");
			if (shot.getCount().equals("X")) {
			    numStrikes++;
			}
		}
		System.out.println(numStrikes);
		percentResult = ((numStrikes)/(numFrames));
		return percentResult;
	}
}