package edu.ycp.cs320.booksdb;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.ycp.cs320.booksdb.model.Author;
import edu.ycp.cs320.booksdb.model.Establishment;
import edu.ycp.cs320.booksdb.persist.DatabaseProvider;
import edu.ycp.cs320.booksdb.persist.IDatabase;

public class AllEstablishmentsQuery {
	public static void main(String[] args) throws Exception {
		Scanner keyboard = new Scanner(System.in);

		// Create the default IDatabase instance
		InitDatabase.init(keyboard);
		
		// get the DB instance and execute transaction
		IDatabase db = DatabaseProvider.getInstance();
		ArrayList<Establishment> establishmentList = db.findAllEstablishments();
		
		// check if anything was returned and output the list
		if (establishmentList.isEmpty()) {
			System.out.println("There are no establishments in the database");
		}
		else {
			for (Author establishment : establishmentList) {
				System.out.println(establishment.getlongname() + ", " + establishment.getShortname());
			}
		}
	}
}
