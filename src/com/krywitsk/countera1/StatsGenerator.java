package com.krywitsk.countera1;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

//generates proper stats list from a counter array 
public class StatsGenerator {

	public String[] generateList(Counter counter, int timeType) {
		
		//timeType = 0 1 2 3 for Hour, Day, Week, Month, respectively
		
		Vector<Date> times = new Vector<Date>();
		Vector<Integer> counts = new Vector<Integer>();
			
		if (counter != null) {
			//get first date
			if (!(counter.getTimeStamps().isEmpty())) {
								
				Date temp = counter.getTimeStamps().get(0);
				times.add(counter.getTimeStamps().get(0));
				
				//required to get String for month integer
			      DateFormatSymbols dateFormat = new DateFormatSymbols();
			      String[] monthFormat = dateFormat.getMonths();
			      String[] dayFormat = dateFormat.getWeekdays();

				//compare rest of timestamps with first
				int count = 0;
				switch (timeType) {
				
				case 0: // Hour
					count = 1;
					for (int i = 1; i < counter.getTimeStamps().size(); ++i) {
						if ((temp.getHours() == counter.getTimeStamps().get(i).getHours()) &&
							(temp.getDay() == counter.getTimeStamps().get(i).getDay()) &&
							(temp.getMonth() == counter.getTimeStamps().get(i).getMonth()) ) {
							count++;
						} else {
							times.add(counter.getTimeStamps().get(i));
							counts.add(new Integer(count));
							count = 1;
							temp = counter.getTimeStamps().get(i);
						}
					}
					counts.add(new Integer(count));
				
				case 1: // Day
					count = 1;
					for (int i = 1; i < counter.getTimeStamps().size(); ++i) {
						if ((temp.getDay() == counter.getTimeStamps().get(i).getDay()) &&
							(temp.getMonth() == counter.getTimeStamps().get(i).getMonth()) ) {
							count++;
						} else {
							times.add(counter.getTimeStamps().get(i));
							counts.add(new Integer(count));
							count = 1;
							temp = counter.getTimeStamps().get(i);
						}
					}
					counts.add(new Integer(count));

				case 2: // Week 
					count = 1;
					for (int i = 1; i < counter.getTimeStamps().size(); ++i) {
						if (temp.getMonth() == counter.getTimeStamps().get(i).getMonth()) {
							count++;
						} else {
							times.add(counter.getTimeStamps().get(i));
							counts.add(new Integer(count));
							count = 1;
							temp = counter.getTimeStamps().get(i);
						}
					}
					counts.add(new Integer(count));

				case 3: //Month
					count = 1;
					for (int i = 1; i < counter.getTimeStamps().size(); ++i) {
						if (temp.getMonth() == counter.getTimeStamps().get(i).getMonth()) {
							count++;
						} else {
							times.add(counter.getTimeStamps().get(i));
							counts.add(new Integer(count));
							count = 1;
							temp = counter.getTimeStamps().get(i);
						}
					}
					counts.add(new Integer(count));
				}
								
			}
		
		
		//once done generating all counters
		
		String[] strArray = new String[times.size()+1];
		strArray[0] = counter.getName();
		
		//configure formatting
		SimpleDateFormat sdf = null;
		
		//refuses to correctly set date formatting
		sdf = new SimpleDateFormat("EEE-MMM-dd HH");
		
		if (timeType == 0) {
			sdf.applyPattern("EEE MMM dd - HH:00.00");
		} else if (timeType == 1) {
			sdf.applyPattern("EEE MMM dd");
		} else if (timeType == 2) {
			sdf.applyPattern("W MMM");
		} else if (timeType == 3) {
			sdf.applyPattern("MMMMMMMMM");
		}
		
		for (int i = 0; i < times.size(); ++i) {
			
			if (timeType == 2) {
				strArray[i+1] = "Week " + sdf.format(times.get(i)) + "  -  " + counts.get(i).toString() + " counts";
			} else {
				strArray[i+1] = sdf.format(times.get(i)) + "  -  " + counts.get(i).toString() + " counts";
			}
		}	
		return strArray;
	} else {
		String[] nullStr = {""};
		return nullStr;
	}
	}

}
