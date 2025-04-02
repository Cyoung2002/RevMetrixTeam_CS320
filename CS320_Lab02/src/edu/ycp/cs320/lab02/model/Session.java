package edu.ycp.cs320.lab02.model;

import java.util.ArrayList;


public class Session {
	private String establishment;
	private String date;
	private String time;
	private String oppoTeam;
	private String oppoPlayer;
	private int numGames;
	private ArrayList<Session> sessions;
	
	public Session(){
	}
	
	public Session(String establishement, String date, String time, String oppoTeam, String oppoPlayer, int games) {
		this.establishment = establishment;
		this.date = date;
		this.time = time;
		this.oppoTeam = oppoTeam;
		this.oppoPlayer = oppoPlayer;
		this.numGames = games;
		
		sessions = new ArrayList<Session>();
		
	}
	
	public ArrayList<Session> getSessions() {
		return sessions;
	}
	
	// Pick up HERE *********************
	public Session makeSession(String establishment, String date, String time, String oppoTeam, String oppoPlayer, int games) {
		Session session = new Session();
		
		session.setEstablishment(establishment);
		session.setDate(date);
		session.setTime(time);
		session.setOppoTeam(oppoTeam);
		session.setOppoPlayer(oppoPlayer);
		session.setNumGames(games);
		
		return session;
	}
	
	public boolean addNewSession(Session session) {
		if(this.sessions.contains(session)) {
			return false;
		}
		else {
			this.sessions.add(session);
			return true;
		}
	}
	
	public boolean deleteSession(Session dupe) {
		for(Session session : sessions) {
			if(dupe.getEstablishment().equals(session.getEstablishment())) {
				sessions.remove(session);
				return true;
			}
		}
		return false;
	}
	
	
	//getters and setters
	
	public String getEstablishment(){
		return establishment;
	}
	
	public void setEstablishment(String establishment) {
		this.establishment = establishment;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public String getOppoTeam() {
		return oppoTeam;
	}
	
	public void setOppoTeam(String oppoTeam) {
		this.oppoTeam = oppoTeam;
	}
	
	public String getOppoPlayer() {
		return oppoPlayer;
	}
	
	public void setOppoPlayer(String oppoPlayer) {
		this.oppoPlayer = oppoPlayer;
	}
	
	public int getNumGames() {
		return numGames;
	}
	
	public void setNumGames(int games) {
		this.numGames = games;
	}
}

