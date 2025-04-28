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

	public boolean insertSession(String league, String bowled, String week, String series) {
		
		// insert new book (and possibly new author) into DB
		Integer session_id = db.insertSession(league, bowled, week, series);

		// check if the insertion succeeded
		if (session_id > 0)
		{
			System.out.println("New Session (ID: " + session_id + ") successfully added to Session table: <" + week + ">");
			
			return true;
		}
		else
		{
			System.out.println("Failed to insert new session (ID: " + session_id + ") into Sessions table: <" + week + ">");
			
			return false;
		}
	}
}
