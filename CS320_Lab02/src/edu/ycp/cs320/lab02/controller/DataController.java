package edu.ycp.cs320.lab02.controller;

import java.util.ArrayList;

import edu.ycp.cs320.lab02.model.Numbers;

public class DataController {

	private ArrayList sessionStats = new ArrayList();
	private ArrayList eventStats = new ArrayList();
	private ArrayList sessionData = new ArrayList();
	private ArrayList eventData = new ArrayList();
	
	public boolean calcSessionStats(String statReq) {
		//parse statReq
		sessionStats.add(statReq);
		return true;
	}
	
	public boolean calcEventStats(String statReq) {
		//parse statReq
		eventStats.add(statReq);
		return true;
	}
	
	public boolean calcSessionData(String dataReq) {
		//parse dataReq
		sessionData.add(dataReq);
		return true;
	}
	
	public boolean calcEventData(String dataReq) {
		//parse dataReq
		eventData.add(dataReq);
		return true;
	}

}
