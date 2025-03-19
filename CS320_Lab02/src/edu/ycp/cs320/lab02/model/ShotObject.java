package edu.ycp.cs320.lab02.model;

//FOR REVEREND METRIX

public class ShotObject {
	private int shotNum;	//shotNum is which shot number (1 or 2)
	//private Ball shotBall = new Ball(); can't make until ball object added
	private int[] count = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};	//How many pins & which pins are knocked over
	private int[] leave = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};	//How many & which pins are left standing
	// Frame shotFrame = new Frame(); can't make until frame object added
	private String typeOfLeave = "";
	private String Position = "";
	
	public ShotObject(int shotNum, int[] count, int[]leave){
		//Constructor to create instance of shot
		//Should have reference to frame, be able to access and use logic to determine
		//where in frame the shot is (to instantiate if it's the first or second shot/new frame etc
		
		//Should instantiate with previously used/selected ball, but can be changed
		//during shot.
		
		this.shotNum = shotNum;
		
	}
	
	public boolean discardShot() {
		this.shotNum = 0;	//shotNum is set to 0, to show the shot has been thrown away
		//Don't make ball null change it since the whole shot is being discarded (doesn't need to be)
		//this.count = ;	//Need to have access to frame information to reference previous shot to determine
		//this.leave = ;	//Need to have access to frame information to reference previous shot to determine
		// DO NOT DISCARD FRAME
		this.typeOfLeave = ""; //Return current leave to empty since it is not known anymore
		this.Position = ""; //Return current position to empty since it is not known anymore
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

	public int[] getCount() {
		return count;
	}
	
	public int getCountIndividual(int i) {
		return count[i];
	}

	public void setCount(int[] count) {
		this.count = count;
	}
	
}

