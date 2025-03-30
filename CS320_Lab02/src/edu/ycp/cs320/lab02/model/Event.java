package edu.ycp.cs320.lab02.model;

import java.util.ArrayList;

public class Event {
	private String name;
	private String type;
	private String location;
	private String session;
	private double eventStats;
	private int standings;
	private ArrayList<Event> events;
	
	public Event(){
	}
	
	public Event(String name, String type, String location, String session, double eventStats, int standings) {
		this.name = name;
		this.type = type;
		this.location = location;
		this.session = session;
		this.eventStats = eventStats;
		this.standings = standings;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String Location) {
		this.location = location;
	}
	
	public String getSession() {
		return session;
	}
	
	public void setSession(String session) {
		this.session = session;
	}
	
	public double getEventStats() {
		return eventStats;
	}
	
	public void setEventStats(double eventStats) {
		this.eventStats = eventStats;
	}
	
	public int getStandings() {
		return standings;
	}
	
	public void setStandings(int standings) {
		this.standings = standings;
	}
	
	public ArrayList<Event> getEvents() {
		return events;
	}
	
	public Event makeEvent(String name, String type, String location, String session, double eventStats, int standings) {
		Event event = new Event();
		
		event.setName(name);
		event.setType(type);;
		event.setLocation(location);
		event.setSession(session);
		event.setEventStats(eventStats);
		event.setStandings(standings);
		
		return event;
	}
	
	public boolean addNewEvent(Event event) {
		if(this.events.contains(event)) {
			return false;
		}
		else {
			this.events.add(event);
			return true;
		}
	}
	
	public boolean deleteEvent(Event dupe) {
		for(Event event : events) {
			if(dupe.getName().equals(event.getName())) {
				events.remove(event);
				return true;
			}
		}
		return false;
	}
}