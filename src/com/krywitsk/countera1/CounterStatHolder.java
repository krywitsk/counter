package com.krywitsk.countera1;

import java.util.ArrayList;
import java.util.Date;

//container for holding counter name and final count
public class CounterStatHolder {
	
	private String name;
	private ArrayList<Date> timeStamps;
	private ArrayList<Integer> counts;
	
	public CounterStatHolder(String nameIn) {
		name = new String(nameIn);
		timeStamps = new ArrayList<Date>();
		counts = new ArrayList<Integer>();
	}
	
	//each timestamp string is linked to a count in counts
	public void addNewTimeStamp(Date newTime) {
		timeStamps.add(newTime);
		int x = 0;
		counts.add(x);
	}
	
	//can only incrememnt the last counter added
	public void incrementLastCounter() {
		if (!(counts.isEmpty())) {
			counts.set(counts.size()-1, counts.get(counts.size()-1) + 1);
		}
	}

	public String getName() {
		return name;
	}

	public ArrayList<Date> getTimeStamps() {
		return timeStamps;
	}

	public ArrayList<Integer> getCounts() {
		return counts;
	}
	
	
	
}
