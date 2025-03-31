package edu.ycp.cs320.lab02.model;

import java.util.ArrayList;

public class Game {
	
	private int lane;
	private int gameNumber;
	private int currentFrame;
	private int score;
	private int startingLane;
	private String teamResult;
	private String individualResult;
	private boolean laneSwitch;
	private ArrayList<Frame> frames;
	
	
	public Game(){
	}
	public Game(int gameNum, int startLane) {
		this.gameNumber = gameNum;
		this.startingLane = startLane;
		this.currentFrame = 0;
		this.score = 0;
		frames = new ArrayList<Frame>();
		
		if(startingLane%2 == 0) {
			this.laneSwitch = false;
		}
		else {
			this.laneSwitch = true;
		}
	}

	
	public void setLane(int laney){
		this.lane = laney;
	}
	public int getLane() {
		return lane;
	}
	public void setGameNumber(int gameNum){
		this.gameNumber = gameNum;
	}
	public int getGameNumber() {
		return gameNumber;
	}
	public void setScore(int scor){
		this.score = scor;
	}
	public int getScore() {
		return score;
	}
	public ArrayList<Frame> getFrames() {
		return frames;
	}
	
	
	public Frame newFrame() {
		Frame newFrame = new Frame();
		frames.add(newFrame);
		if(currentFrame != 0) {
			switchLanes();
		}
		currentFrame++;
		return newFrame;
	}
	public void switchLanes() {
		if(laneSwitch == true) {
			lane++;
			laneSwitch = false;
		} else {
			lane--;
			laneSwitch = true;
		}
	}
	public boolean modifyGame(String modify) {
		if (modify.contains("xyz")) {
			return true;
		}
		return false;  
	}
	public boolean cancelGame() {			// score will be the negative version of the points
		score = score * -1; 				// collected in the game up to the point of canceling
		return true; 						// check for a cancelled game by negative score value
	}
	public void updateScore(int points) {	// temporary score method before full ScoreController implementation
		score += points;
	}
	// Shot and game servlets have buttons to go straight to each others page
	// Insert new shot method first
}

