package edu.ycp.cs320.lab02.model;

public class Frame {
	private int frameNum;
	private int laneNum;
	private String result;
	private int shotNum;
	
	public Frame(int frame, int lane, String result, int shot){
		this.frameNum = frame;
		this.laneNum = lane;
		this.result = result;
		this.shotNum = shot;
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
}