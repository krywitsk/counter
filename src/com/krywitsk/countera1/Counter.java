package com.krywitsk.countera1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	
	public void addTimeStampString(String timeIn) {
		//SimpleDateFormat fDate = new SimpleDateFormat("EEE-MMM-dd-HH-mm-ss-");
		//Date tempDate;
		//tempDate = fDate.parse(timeIn);
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

}
