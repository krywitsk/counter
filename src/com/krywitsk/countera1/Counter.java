package com.krywitsk.countera1;

import java.util.Date;
import java.util.Vector;

public class Counter {

	//recording dates and times for each counter
	private String name;
	private Vector<Date> timeStamps;
	
	public Counter(String nameIn) {

		this.name = new String(nameIn);
		timeStamps = new Vector<Date>();
	}
	
	public void incrementCount() {
		timeStamps.add(new Date());
	}
	
	public Integer getCount() {
		return timeStamps.size();
	}
	
	public Vector<Date> getTimeStamps() {
		return timeStamps;
	}
	
	public void addTimeStamp(Date dateIn) {
		timeStamps.add(dateIn);
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
		this.name = new String(name);
	}	

	public String convertToString() {
		StringBuilder temp = new StringBuilder();
		temp.append(name);
		for(Date dates : timeStamps) {
			temp.append("%");
			temp.append(dates.toString());
		}
		return temp.toString();
	}
	
}
