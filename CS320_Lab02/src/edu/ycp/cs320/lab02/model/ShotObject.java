package edu.ycp.cs320.lab02.model;

//FOR REVEREND METRIX

public class ShotObject {
	private int shotNum;	//shotNum is which shot number (1 or 2)
	private Ball shotBall = new Ball(); //Reference to ball object
	private int[] count = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};	//How many pins & which pins are knocked over
	private int[] leave = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};	//How many & which pins are left standing
	Frame shotFrame = new Frame();
	private String typeOfLeave = "";
	private String Position = "";
	
	
	public ShotObject() {
		
	}
	
	public ShotObject(int shotNum, int[] count, int[]leave){
		//Should instantiate with previously used/selected ball, but can be changed
		//during shot.
		
		this.shotNum = shotNum;
		this.count = count;
		this.leave = leave;
		
	}
	
	public ShotObject(int shotNum, int[] count, int[]leave, Ball shotBall) {
		//Should instantiate with previously used/selected ball, but can be changed
		//during shot.
		
		this.shotNum = shotNum;
		this.count = count;
		this.leave = leave;
		this.shotBall = shotBall;
	}
	

	public ShotObject(int shotNum, int[] count, int[]leave, Ball shotBall, Frame shotFrame) {
		//Should instantiate with previously used/selected ball, but can be changed
		//during shot.
		
		this.shotNum = shotNum;
		this.count = count;
		this.leave = leave;
		this.shotBall = shotBall;
		this.shotFrame = shotFrame;
	}
	
	public ShotObject(int shotNum, int[] count, int[]leave, Frame shotFrame) {
		//Should instantiate with previously used/selected ball, but can be changed
		//during shot.
		
		this.shotNum = shotNum;
		this.count = count;
		this.leave = leave;
		this.shotFrame = shotFrame;

	}
	
	
	public boolean discardShot() {
		this.shotNum = 0;	//shotNum is set to 0, to show the shot has been thrown away
		//Don't make ball null change it since the whole shot is being discarded (doesn't need to be)
		//this.count = ;	//Need to have access to frame information to reference previous shot to determine
		//this.leave = ;	//Need to have access to frame information to reference previous shot to determine
		// DO NOT DISCARD FRAME
		this.setTypeOfLeave(""); //Return current leave to empty since it is not known anymore
		this.setPosition(""); //Return current position to empty since it is not known anymore
		return true;
	}
	
	public boolean modifyShot() {
		//this should have access to anything that is instantiated within the shot itself (not frame level, only shot)
		
		
		return true;
	}
	
	public void setShotNum(int shotNum) {
		this.shotNum = shotNum;
	}
	
	public int getShotNum() {
		return shotNum;
	}
	
	//Gets the entire array of pin data
	public int[] getCount() {
		return count;
	}
	
	//Gets one pins data out of the array
	public int getCountIndividual(int i) {
		return count[i];
	}

	//This method is meant to allow the system to change the entire array of pins to whatever values
	public void setCount(int[] count) {
		this.count = count;
	}
	
	//This method is meant to allow the system to change a particular pins state
	public void setCountIndividual(int whichPin, int knocked) {
		count[whichPin] = knocked; 
	}
	
	//Leave section may be redundant
	public int[] getLeave() {
		return leave;
	}
	public int getLeaveIndividual(int i) {
		return count[i];
	}
	public void setLeave(int[] leave) {
		this.leave = leave;
	}
	public void setLeaveIndividual(int whichPin, int knocked) {
		count[whichPin] = knocked; 
	}

	public String getTypeOfLeave() {
		return typeOfLeave;
	}

	public void setTypeOfLeave(String typeOfLeave) {
		this.typeOfLeave = typeOfLeave;
	}

	public String getPosition() {
		return Position;
	}

	public void setPosition(String position) {
		Position = position;
	}

	public Ball getShotBall() {
		return shotBall;
	}

	public void setShotBall(Ball shotBall) {
		this.shotBall = shotBall;
	}
	
	public Frame getshotFrame() {
		return shotFrame;
	}
	
	public void setShotFrame(Frame shotFrame) {
		this.shotFrame = shotFrame;
	}
	
}

