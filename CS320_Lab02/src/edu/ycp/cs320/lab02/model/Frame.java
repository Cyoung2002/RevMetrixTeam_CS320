package edu.ycp.cs320.lab02.model;

import java.util.List;
import java.util.ArrayList;

public class Frame {
	
	private int frameNum;
	private int laneNum;
	private String result;
	private int shotNum;
	private ArrayList<ShotObject> shots;
	private int pinScore;
	
	
	// Generic frame constructor
	public Frame() {
	}
	// Complete frame constructor
	public Frame(int frame, int lane, String result){
		this.frameNum = frame;
		this.laneNum = lane;
		this.result = result;
		this.shotNum = 1;
		this.pinScore = 0;
		shots = new ArrayList<ShotObject>();
	}
	
	
	public void setFrameNum(int frame) {
		this.frameNum = frame;
	}
	public int getFrameNum() {
		return frameNum;
	}
	public void setLaneNum(int lane) {
		this.laneNum = lane;
	}
	public int getLaneNum() {
		return laneNum;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getResult() {
		return result;
	}
	public void setShotNum(int shot) {
        this.shotNum = shot;
	}
    public int getShotNum() {
        return shotNum;
    }
    public void setShot(ShotObject shoot) {
    	shots.set(shotNum, shoot);
    }
    public ShotObject getShot(int shootNum) {
    	return shots.get(shootNum - 1);
    }
    public void setPinScore(int pScore) {
    	this.pinScore = pScore;
    }
    public int getPinScore() {
    	return pinScore;
    }
    
    
    public boolean addShot(ShotObject shot) {
    	if(shotNum <= 2) {
    		shots.add(shot);
        	// temporary frame score 
        	pinScore += shot.getPinsKnockedDown().size();
        	shotNum++;
        	return true;
    	}
    	return false;
    }
    // Method to cancel (clear) a frame. Clears the shot and sets the result of the frame to a blank String.
    public boolean cancelFrame(String result, int shotNum) {
    	result = null;
        shotNum = 1;
        return true;
    }
    // Method to modify the fields of a frame. Allows user to input new result and shot number values.
    public boolean modifyFrame(String newResult, int newShot) {
        this.result = newResult;
        this.shotNum = newShot;
        return true;
    }
}