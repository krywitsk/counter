package com.krywitsk.countera1;

import java.util.Date;
import java.util.ArrayList;

public class Counter {

	//recording dates and times for each counter
	private String name;
	ArrayList<Date> timeStamps;
	
	public Counter(String name) {
		this.name = name;
		timeStamps = new ArrayList<Date>();
	}
	
	public void incrementCount() {
		timeStamps.add(new Date());
	}
	
	public Integer getCount() {
		return timeStamps.size();
	}
	
	public ArrayList<Date> getTimeStamps() {
		return timeStamps;
	}
	
	public void addTimeStamp(String timeIn) {
		timeStamps.add(new Date(timeIn));
	}
		
	public void resetCount() {
		timeStamps.clear();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	

	public String convertToString() {
		String temp = new String(this.name);
		for(Date dates : timeStamps) {
			temp.concat("^");
			temp.concat(dates.toString());
		}
		return temp;
	}
	
}
