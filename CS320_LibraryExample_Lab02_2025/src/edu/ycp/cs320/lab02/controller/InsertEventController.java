package edu.ycp.cs320.lab02.controller;

import edu.ycp.cs320.booksdb.persist.DatabaseProvider;
import edu.ycp.cs320.booksdb.persist.DerbyDatabase;
import edu.ycp.cs320.booksdb.persist.IDatabase;

public class InsertEventController {

	private IDatabase db = null;

	public InsertEventController() {
		
		// creating DB instance here
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();		
	}

	public boolean insertEvent(String longname, String shortname, String establishmentShort, String weeknight, String start, String end_date, Integer gamesPerSession) {
		
		// insert new book (and possibly new author) into DB
		Integer event_id = db.insertEvent(longname, shortname, establishmentShort, weeknight, start, end_date, gamesPerSession);

		// check if the insertion succeeded
		if (event_id > 0)
		{
			System.out.println("New Event (ID: " + event_id + ") successfully added to Events table: <" + longname + ">");
			
			return true;
		}
		else
		{
			System.out.println("Failed to insert new Event (ID: " + event_id + ") into Events table: <" + longname + ">");
			
			return false;
		}
	}
}
