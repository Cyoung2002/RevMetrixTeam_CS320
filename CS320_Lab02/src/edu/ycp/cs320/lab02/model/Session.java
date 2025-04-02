package edu.ycp.cs320.lab02.model;

import java.util.ArrayList;


public class Session {
	private String establishment;
	private String date;
	private String time;
	private String oppoTeam;
	private String oppoPlayer;
	private ArrayList<Game> games;
	private ArrayList<Session> sessions;
	
	public Session(){
	}
	
	public Session(String establishement, String date, String time, String oppoTeam, String oppoPlayer, ArrayList<Game> games) {
		this.establishment = establishment;
		this.date = date;
		this.time = time;
		this.oppoTeam = oppoTeam;
		this.oppoPlayer = oppoPlayer;
		this.games = games;
		
		sessions = new ArrayList<Session>();
		
	}
	
	public ArrayList<Session> getSessions() {
		return sessions;
	}
	
	// Pick up HERE *********************
	public Session makeEstablishment(String name, String location, String phoneNumber, String hours) {
		Session establish = new Session();
		
		establish.setName(name);
		establish.setLocation(location);
		establish.setphoneNumber(phoneNumber);
		establish.setHours(hours);
		
		return establish;
	}
	
	public boolean addNewEstablishment(Session establishment) {
		if(this.establishments.contains(establishment)) {
			return false;
		}
		else {
			this.establishments.add(establishment);
			return true;
		}
	}
	
	public boolean duplicateEstablishment(Session dupe) {
		for(Session establishment : establishments) {
			if(dupe.getName().equals(establishment.getName())) {
				establishments.add(establishment);
				return true;
			}
		}
		return false;
	}
	public boolean deleteEstablishment(Session dupe) {
		for(Session establishment : establishments) {
			if(dupe.getName().equals(establishment.getName())) {
				establishments.remove(establishment);
				return true;
			}
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

