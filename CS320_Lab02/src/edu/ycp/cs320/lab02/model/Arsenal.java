package edu.ycp.cs320.lab02.model;

import java.util.ArrayList;
//import edu.ycp.cs320.lab02.model.Ball;

public class Arsenal {
	
	private String arsenalName;
	private ArrayList<Ball> balls;
	
	
	public Arsenal(){
	}
	public Arsenal(String name){
		this.arsenalName = name;
		balls = new ArrayList<Ball>();
	}

	
	public void setArsenalName(String name){
		this.arsenalName = name;
	}
	public String getArsenalName() {
		return arsenalName;
	}
	public ArrayList<Ball> getBalls() {
		return balls;
	}
	
	
	public Ball makeBall(String name, String color, Double weight) {
		Ball bally = new Ball();
		
		bally.setName(name);
		bally.setColor(color);
		//bally.setBrand(brand);
		//bally.setCore(core);
		//bally.setDiameter(diameter);
		bally.setWeight(weight);
		
		return bally;
	}
	public boolean addNewBall(Ball ball) {
		if(this.balls.contains(ball)) {
			return false;
		}
		else {
			this.balls.add(ball);
			return true;
		}
	}
	public boolean duplicateBall(Ball ball) {
		if(this.balls.contains(ball)) {
			this.balls.add(ball);
			return true;
		}
		return false;
	}
	public boolean deleteBall(Ball ball) {
		if(this.balls.contains(ball)) {
			this.balls.remove(ball);
			return true;
		}
		return false;
	}
	
}

