package edu.ycp.cs320.lab02.model;


public class Ball {
	private Double diameter;
	private Double weight;
	private String color;
	private String name;
	private String brand;
	private String core;
	
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
	
	Double getDiameter() {
		return diameter;
	}
	
	void setDiameter(Double diameter) {
		this.diameter = diameter;
	}
	
	String getBrand() {
		return brand;
	}
	
	void setBrand(String brand) {
		this.brand = brand;
	}
	
	String getCore() {
		return core;
	}
	
	void setCore(String core) {
		this.core = core;
	}
	
}

