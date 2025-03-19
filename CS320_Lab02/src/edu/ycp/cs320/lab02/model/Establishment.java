package edu.ycp.cs320.lab02.model;

import java.util.ArrayList;


public class Establishment {
	private String name;
	private String location;
	private String phoneNumber;
	private String hours;
	private ArrayList<Establishment> establishments;
	
	public Establishment(){
	}
	
	public Establishment(String name, String location, String phoneNumber, String hours) {
		this.name = name;
		this.location = location;
		this.phoneNumber = phoneNumber;
		this.hours = hours;
		
		establishments = new ArrayList<Establishment>();
		
	}
	

	public ArrayList<Establishment> getEstablishments() {
		return establishments;
	}
	
	
	public Establishment makeEstablishment(String name, String location, String phoneNumber, String hours) {
		Establishment establish = new Establishment();
		
		establish.setName(name);
		establish.setLocation(location);
		establish.setphoneNumber(phoneNumber);
		establish.setHours(hours);
		
		return establish;
	}
	
	public boolean addNewEstablishment(Establishment establishment) {
		if(this.establishments.contains(establishment)) {
			return false;
		}
		else {
			this.establishments.add(establishment);
			return true;
		}
	}
	

	public boolean deleteEstablishment(Establishment establishment) {
		if(this.establishments.contains(establishment)) {
			this.establishments.remove(establishment);
			return true;
		}
		return false;
	}
	
	
	
	//getters and setters
	
	public String getName(){
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getphoneNumber() {
		return phoneNumber;
	}
	
	public void setphoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getHours() {
		return hours;
	}
	
	public void setHours(String hours) {
		this.hours = hours;
	}
	
}

