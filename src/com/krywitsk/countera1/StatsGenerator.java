package com.krywitsk.countera1;

import java.util.Date;
import java.util.ArrayList;

//generates proper stats list from a counter array 
public class StatsGenerator {

	private ArrayList<CounterStatHolder> simplifiedCounters;
	
	public String[] generateList(ArrayList<Counter> countersIn, int timeType) {
		
		//timeType = 0 1 2 3 for Hour, Day, Week, Month, respectively
		
		simplifiedCounters = new ArrayList<CounterStatHolder>();
		
		for (Counter counter : countersIn) {
			//get first date
			if (!(counter.getTimeStamps().isEmpty())) {
				Date temp = counter.getTimeStamps().get(0);
				
				simplifiedCounters.add(new CounterStatHolder(counter.getName()));
				simplifiedCounters.get(simplifiedCounters.size()-1).addNewTimeStamp(counter.getTimeStamps().get(0));
			
				//compare rest of timestamps with first
				switch (int timeType) {
				
				case 0: // Hour
					for (int i = 1; i < counter.getTimeStamps().size(); ++i) {
						if (temp.getHours() == counter.getTimeStamps().get(i).getHours()) {
							simplifiedCounters.get(simplifiedCounters.size()-1).incrementLastCounter();
						} else {
							//timestamps do not match according to hr, day, wk, mo
							//add new timestamp to temp array, set temp to it
							simplifiedCounters.get(simplifiedCounters.size()-1).addNewTimeStamp(counter.getTimeStamps().get(i));
							simplifiedCounters.get(simplifiedCounters.size()-1).incrementLastCounter();
							temp = counter.getTimeStamps().get(i);
						}
					}
					
				case 1: // Day
					for (int i = 1; i < counter.getTimeStamps().size(); ++i) {
						if (temp.getDay() == counter.getTimeStamps().get(i).getDay()) {
							simplifiedCounters.get(simplifiedCounters.size()-1).incrementLastCounter();
						} else {
							//timestamps do not match according to hr, day, wk, mo
							//add new timestamp to temp array, set temp to it
							simplifiedCounters.get(simplifiedCounters.size()-1).addNewTimeStamp(counter.getTimeStamps().get(i));
							simplifiedCounters.get(simplifiedCounters.size()-1).incrementLastCounter();
							temp = counter.getTimeStamps().get(i);
						}
					}
					
				case 2: // Week
					
					//DATE HAS NO WEEK METHOD, MAY NEED TO SWITCH TO CALENDAR TO AVOID DEPRECATED METHODS
					
					/*
					for (int i = 1; i < counter.getTimeStamps().size(); ++i) {
						if (temp.getMonth() == counter.getTimeStamps().get(i).getMonth()) {
							simplifiedCounters.get(simplifiedCounters.size()-1).incrementLastCounter();
						} else {
							//timestamps do not match according to hr, day, wk, mo
							//add new timestamp to temp array, set temp to it
							simplifiedCounters.get(simplifiedCounters.size()-1).addNewTimeStamp(counter.getTimeStamps().get(i));
							simplifiedCounters.get(simplifiedCounters.size()-1).incrementLastCounter();
							temp = counter.getTimeStamps().get(i);
						}
					}
					*/
				case 3: //Month
					for (int i = 1; i < counter.getTimeStamps().size(); ++i) {
						if (temp.getMonth() == counter.getTimeStamps().get(i).getMonth()) {
							simplifiedCounters.get(simplifiedCounters.size()-1).incrementLastCounter();
						} else {
							//timestamps do not match according to hr, day, wk, mo
							//add new timestamp to temp array, set temp to it
							simplifiedCounters.get(simplifiedCounters.size()-1).addNewTimeStamp(counter.getTimeStamps().get(i));
							simplifiedCounters.get(simplifiedCounters.size()-1).incrementLastCounter();
							temp = counter.getTimeStamps().get(i);
						}
					}
					
				}
				
			}
		}
		
		for (CounterStatHolder ct : simplifiedCounters) {
			System.out.println(ct.getName());	
		}
			
		
		return null;
	}

}
