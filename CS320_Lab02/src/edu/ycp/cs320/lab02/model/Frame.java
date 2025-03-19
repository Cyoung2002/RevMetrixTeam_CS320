package edu.ycp.cs320.lab02.model;

import java.util.List;
import java.util.ArrayList;

public class Frame {
	private int frameNum;
	private int laneNum;
	private String result;
	private List<Integer> shotNum;
	
	public Frame() {
	}
	
	public Frame(int frame, int lane, String result, int shotNum){
		this.frameNum = frame;
		this.laneNum = lane;
		this.result = result;
		this.shotNum = new ArrayList<>();
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
	
	public void addShotNum(int shot) {
        this.shotNum.add(shot);
    }

    public List<Integer> getShotNum() {
        return shotNum;
    }
    
    public boolean cancelFrame() {
        this.result = "";
        this.shotNum.clear();
        return true;
    }
    
    public boolean modifyFrame(String newResult, List<Integer> newShots) {
        this.result = newResult;
        this.shotNum = new ArrayList<>(newShots);
        return true;
    }
}