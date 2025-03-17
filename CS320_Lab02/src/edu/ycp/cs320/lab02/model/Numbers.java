package edu.ycp.cs320.lab02.model;


public class Numbers {
	private Double first;
	private Double second;
	private Double third;
	private Double result; 
	
	private String firstStr;
	private String secondStr;
	private String thirdStr;
	
	public Numbers(){
	}
	
	public Numbers(Double first, Double second){
		this.first = first;
		this.second = second;
	}
	
	public Numbers(Double first, Double second, Double third){
		this.first = first;
		this.second = second;
		this.third = third;
	}
	
	public void setFirst(Double first){
		this.first = first;
	}
	
	public Double getFirst() {
		return first;
	}
	
	public void setSecond(Double second){
		this.second = second;
	}
	
	public Double getSecond() {
		return second;
	}
	
	public void setThird(Double third){
		this.third = third;
	}
	
	public Double getThird() {
		return third;
	}
	
	public Double getResult() {
		return result;
	}
	
	public void setResult(Double result) {
		this.result = result;
	}
	
	
	public void setFirstStr(String first){
		this.firstStr = first;
	}
	
	public String getFirstStr() {
		return firstStr;
	}
	
	public void setSecondStr(String second){
		this.secondStr = second;
	}
	
	public String getSecondStr() {
		return secondStr;
	}
	
	public void setThirdStr(String third){
		this.thirdStr = third;
	}
	
	public String getThirdStr() {
		return thirdStr;
	}
	
}

