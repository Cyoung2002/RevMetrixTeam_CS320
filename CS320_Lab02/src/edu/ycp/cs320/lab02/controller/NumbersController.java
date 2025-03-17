package edu.ycp.cs320.lab02.controller;

import edu.ycp.cs320.lab02.model.Numbers;

public class NumbersController {

	private Numbers model;
	
	public void setModel(Numbers model) {
		this.model = model;
	}
	
	
	public double add() {
		model.setResult(model.getFirst() + model.getSecond() + model.getThird());
		return model.getResult();
	}
	
	
	public double multiply() {
		model.setResult(model.getFirst() * model.getSecond());
		return model.getResult();
		
	}
	
	public void setMultNum(Double first, Double second) {
			model.setFirst(first);
			model.setSecond(second);
	
	}
	
	public void setAddNum(Double first, Double second, Double third) {
		model.setFirst(first);
		model.setSecond(second);
		model.setThird(third);

	}
	
	
	public void setAddNumStr(String first, String second, String third) {
		model.setFirstStr(first);
		model.setSecondStr(second);
		model.setThirdStr(third);

	}
	
	public void setMultNumStr(String first, String second) {
		model.setFirstStr(first);
		model.setSecondStr(second);

}

}
