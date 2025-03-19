package edu.ycp.cs320.lab02.model;

import java.util.ArrayList;
//import edu.ycp.cs320.lab02.model.Ball;

public class Arsenal {
	
	private String arsenalName;
	private ArrayList balls;
	
	
	public Arsenal(){
	}
	public Arsenal(String name){
		this.arsenalName = name;
		balls = new ArrayList();
	}

	
	public void setArsenalName(String name){
		this.arsenalName = name;
	}
	public String getArsenalName() {
		return arsenalName;
	}
	public ArrayList getBalls() {
		return balls;
	}
	
	
	public boolean addNewBall(Object ball) {
		if(this.balls.contains(ball)) {
			return false;
		}
		else {
			this.balls.add(ball);
			return true;
		}
	}
	public boolean duplicateBall(Object ball) {
		if(this.balls.contains(ball)) {
			this.balls.add(ball);
			return true;
		}
		return false;
	}
	public boolean deleteBall(Object ball) {
		if(this.balls.contains(ball)) {
			this.balls.remove(ball);
			return true;
		}
		return false;
	}
	
}

