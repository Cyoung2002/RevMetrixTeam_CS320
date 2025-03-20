package edu.ycp.cs320.lab02.model;

//FOR REVEREND METRIX

public class ShotObject {
	private int shotNum;	//shotNum is which shot number (1 or 2)
	private Ball shotBall = new Ball(); //Reference to ball object
	private int[] count = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};	//List of pins and their state (0 = not been knocked down)
	private int[] leave = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};	//List of pins and their state (1 = not been knocked down)
	Frame shotFrame = new Frame();
	private String typeOfLeave = "";
	private String Position = "";
	//might need to make a new count so that we can store it in
	private int countAmt = 0;
	private int leaveAmt = 10;
	
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
	
	//This method is inefficient but it should work. Essentially, regardless of which shot, the method goes through the array
	//of "count" and sets the computed amount between the "first" and "second" count to be how many pins have been knocked down.
	//This works, since when using this method we can hardcode the first shot's count array to have the "firstCount" to be 0,
	//while the second is the amount the user entered as the first count amount on the webpage.
	//Then for the second shot it uses both the provided amounts by the user (hence why this is inefficient, since it will go back
	//through parts of the array. This essentially acts as another setter just for a pin amount instead of specific pin.
	public boolean modifyCount(int firstCount, int secondCount) {
		this.countAmt = firstCount + secondCount;
		for(int i = 0; i < countAmt; i++) {
			count[i] = 0;
		}
		return true;
	}
	
	public boolean modifyLeave(int firstLeave, int secondLeave) {
		this.leaveAmt = firstLeave + secondLeave;
		for(int i = 0; i < leaveAmt; i++) {
			leave[i] = 1;
		}
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

	public int getCountAmt() {
		return countAmt;
	}

	public void setCountAmt(int countAmt) {
		this.countAmt = countAmt;
	}

	public int getLeaveAmt() {
		return leaveAmt;
	}

	public void setLeaveAmt(int leaveAmt) {
		this.leaveAmt = leaveAmt;
	}
	
	
}

