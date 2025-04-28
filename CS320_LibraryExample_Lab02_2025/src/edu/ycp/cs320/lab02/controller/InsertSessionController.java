package edu.ycp.cs320.lab02.controller;

import edu.ycp.cs320.booksdb.persist.DatabaseProvider;
import edu.ycp.cs320.booksdb.persist.DerbyDatabase;
import edu.ycp.cs320.booksdb.persist.IDatabase;

public class InsertSessionController {

	private IDatabase db = null;

	public InsertSessionController() {
		
		// creating DB instance here
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();		
	}

	public boolean insertSession(String league, String bowled, String ball, String startLane, String week, String series) {
		
		// insert new book (and possibly new author) into DB
		Integer weekID = db.insertSession(league, bowled, ball, startLane, week, series);

		// check if the insertion succeeded
		if (weekID > 0)
		{
			System.out.println("New Session (ID: " + weekID + ") successfully added to Session table: <" + week + ">");
			
			return true;
		}
		else
		{
			System.out.println("Failed to insert new session (ID: " + weekID + ") into Sessions table: <" + week + ">");
			
			return false;
		}
	}
}
