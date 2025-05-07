package edu.ycp.cs320.booksdb;

import java.util.ArrayList;
import java.util.Scanner;

import edu.ycp.cs320.booksdb.model.Session;
import edu.ycp.cs320.booksdb.persist.DatabaseProvider;
import edu.ycp.cs320.booksdb.persist.IDatabase;

public class OverallGameAverageforEvent {
	public static void main(String[] args) throws Exception {

		Scanner keyboard = new Scanner(System.in);

		// Create the default IDatabase instance
		InitDatabase.init(keyboard);

		System.out.print("Enter an event's long name: ");
		String longname = keyboard.nextLine();

		IDatabase db = DatabaseProvider.getInstance();
		ArrayList<Session> gameList = db.findGameswithEventDate(longname);

		if (gameList.isEmpty()) {
			System.out.println("No games found for this event <" + longname + ">");
		} else {
			for (Session session : gameList) {
				int x = Integer.parseInt(session.getGameOneScore());
				int y = Integer.parseInt(session.getGameTwoScore());
				int z = Integer.parseInt(session.getGameThreeScore());
				
				int result = ((x + y + z)/3);
				
				System.out.println(
					result
				);
			}
		}
		
	}
}
