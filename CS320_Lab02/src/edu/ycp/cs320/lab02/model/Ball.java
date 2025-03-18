package edu.ycp.cs320.lab02.model;


public class Ball {
	private Double weight;
	private String color;
	private String name;
	
	public Ball(){
	}
	
	Double getWeight() {
		return weight;
	}
	
	void setWeight(Double weight) {
		this.weight = weight;
	}
	
	String getColor(){
		return color;
	}
	
	void setColor(String color) {
		this.color = color;
	}
	
	String getName() {
		return name;
	}
	
	void setName(String name) {
		this.name = name;
	}
	
}

