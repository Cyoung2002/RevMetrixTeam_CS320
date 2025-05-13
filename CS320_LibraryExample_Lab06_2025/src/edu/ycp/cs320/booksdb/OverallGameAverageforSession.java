package edu.ycp.cs320.booksdb;

import java.util.ArrayList;
import java.util.Scanner;

import edu.ycp.cs320.booksdb.model.Session;
import edu.ycp.cs320.booksdb.persist.DatabaseProvider;
import edu.ycp.cs320.booksdb.persist.IDatabase;

public class OverallGameAverageforSession {
	public static void main(String[] args) throws Exception {

		Scanner keyboard = new Scanner(System.in);

		// Create the default IDatabase instance
		InitDatabase.init(keyboard);

		System.out.print("Enter a session's date: ");
		String date = keyboard.nextLine();

		IDatabase db = DatabaseProvider.getInstance();
		ArrayList<Session> gameList = db.findGamesWithSessionDate(date);

		if (gameList.isEmpty()) {
			System.out.println("No games found for this session <" + date + ">");
		} else {
			for (Session session : gameList) {
				int x = session.getGameOneScore();
				int y = session.getGameTwoScore();
				int z = session.getGameThreeScore();
				
				int result = ((x + y + z)/3);
				
				System.out.println(
					result
				);
			}
		}
		
	}
}
