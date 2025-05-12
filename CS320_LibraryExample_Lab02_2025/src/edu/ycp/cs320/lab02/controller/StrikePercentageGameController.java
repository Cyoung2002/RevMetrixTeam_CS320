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

	public Double AllStrikePercentage() {
		
		// insert new book (and possibly new author) into DB
		ArrayList<Shot> shotList = db.findAllShots();
		Double numStrikes = 0.0;
		Double numFirstShots = 10.0;
		Double percentResult = 0.0;
		
		for (Shot shot : shotList) {
			if (shot.getCount().equals("X")) {
			    numStrikes++;
			}
		}
		
		System.out.println(numStrikes);
		percentResult = (((numStrikes)/(numFirstShots))*100.0);
		return percentResult;
	}
	
	public Double StrikePercentageFrame(String frameNum) {
		
		// insert new book (and possibly new author) into DB
		ArrayList<Shot> shotList = db.findAllShotsInFrame(frameNum);
		Double numStrikes = 0.0;
		Double numFirstShots = 10.0;
		Double percentResult = 0.0;
		
		for (Shot shot : shotList) {
			if (shot.getCount().equals("X")) {
			    numStrikes++;
			}
		}
		
		System.out.println(numStrikes);
		percentResult = (((numStrikes)/(numFirstShots))*100.0);
		return percentResult;
	}
	
	public Double StrikePercentageEventFrame(String event, String season, String frameNum) {
		ArrayList<Shot> shotList = db.findAllShotsGivenEventFrame(event, season, frameNum);
		Double numStrikes = 0.0;
		Double numFrames = 12.0;
		Double percentResult = 0.0;
		
		for (Shot shot : shotList) {
			System.out.println(shot.getCount());
			if (shot.getCount().equals("X")) {
			    numStrikes++;
			}
		}
		
		System.out.println(numStrikes);
		percentResult = (((numStrikes)/(numFrames))*100.0);
		return percentResult;
	}
}