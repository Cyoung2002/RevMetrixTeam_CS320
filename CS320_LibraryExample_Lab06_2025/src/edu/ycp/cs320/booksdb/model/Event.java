package edu.ycp.cs320.booksdb.model;

public class Event {
	//private int establishmentId;
	private String longname;
	private String shortname;
	private String establishmentShort;
	private String weeknight;
	private String start;
	private String end;
	private int gamesPerSession;
	private String eventID;
	
	
	public Event() {
		
	}

	/*
	public int getEstablishmentId() {
		return establishmentId;
	}
	public void setEstablishmentId(int establishmentId) {
		this.establishmentId = establishmentId;
	}
	*/

	public String getLongname() {
		return longname;
	}
	public void setLongname(String longname) {
		this.longname = longname;
	}


	public String getShortname() {
		return shortname;
	}
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
	
	
	public String getEstablishmentShort() {
		return establishmentShort;
	}
	public void setEstablishmentShort(String estabShort) {
		this.establishmentShort = estabShort;
	}


	public String getWeeknight() {
		return weeknight;
	}
	public void setWeeknight(String weeknight) {
		this.weeknight = weeknight;
	}


	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}


	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}


	public int getGamesPerSession() {
		return gamesPerSession;
	}
	public void setGamesPerSession(int gamesPerSession) {
		this.gamesPerSession = gamesPerSession;
	}
	
	public void setEventID(String eventID) {
		this.eventID = eventID;
	}

}
