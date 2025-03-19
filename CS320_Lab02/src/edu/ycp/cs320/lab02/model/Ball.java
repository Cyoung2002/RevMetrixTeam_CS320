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
	
	public Ball(Double diameter, Double weight, String color, String name, String brand, String core) {
		this.diameter = diameter;
		this.weight = weight;
		this.color = color;
		this.name = name;
		this.brand = brand;
		this.core = core;
		
	}
	
	public Double getWeight() {
		return weight;
	}
	
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	
	public String getColor(){
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Double getDiameter() {
		return diameter;
	}
	
	public void setDiameter(Double diameter) {
		this.diameter = diameter;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public String getCore() {
		return core;
	}
	
	public void setCore(String core) {
		this.core = core;
	}
	
}

