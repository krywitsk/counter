package com.krywitsk.countera1;

import java.util.Date;
import java.util.ArrayList;

public class Counter {

	//recording dates and times for each counter
	private String name;

	private int count;
	ArrayList<Date> timeStamps;
	
	public Counter(String name) {
		this.name = name;
		count = 0;
		timeStamps = new ArrayList<Date>();
	}
	
	public void incrementCount() {
		count++;
		timeStamps.add(new Date());
	}
	
	public int getCount() {
		return count;
	}
	
	public void resetCount() {
		count = 0;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	

}
