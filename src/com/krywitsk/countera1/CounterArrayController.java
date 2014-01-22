package com.krywitsk.countera1;

import java.util.ArrayList;

import android.content.Context;
import android.content.SharedPreferences;

public class CounterArrayController {

	private static int counterIndex;

	public static final String SAVED_PREFS = "SavedPrefs";
	public static final String SAVE_INDEX = "SaveInd";
	public static final String NUM_COUNTERS = "numCounters";
	public static final String CONTROLLER_PREFS = "prefs";
	
	private ArrayList<Counter> counterArray;
	Context context;
	
	public CounterArrayController(Context context) {
		// TODO Auto-generated constructor stub
		
		this.context = context;
		
		ArrayList<Counter> loadedCounterArray = loadFromFile();
		if (loadedCounterArray == null) {
			counterIndex = -1;
			counterArray = new ArrayList<Counter>();

		} else {
			counterArray = loadedCounterArray;
		}
	}
	
	public String getCurrentCounterName() {
		if (!counterArray.isEmpty()) {
			return counterArray.get(counterIndex).getName();
		} else {
			return "Error";
		}
	}
	
	public String getCurrentCounterCount() {
		if (!counterArray.isEmpty()) {
			return counterArray.get(counterIndex).getCount().toString();
		} else {
			return "X";
		}
	}
	
	public void setCurrentCounterIndex(int index) {
		if (index >= 0 && index < counterArray.size()) {
			counterIndex = index;
		}
	}
	
	public void incrementCurrentCounter() {
		if (!counterArray.isEmpty()) {
			counterArray.get(counterIndex).incrementCount();
			saveToFile(counterArray);
		}
	}
	
	public void resetCurrentCounter() {
		if (!counterArray.isEmpty()) {
			counterArray.get(counterIndex).resetCount();
			saveToFile(counterArray);
		} 
	}
	
	public void addNewCounter(String newName) {
		counterArray.add(new Counter(newName));
		counterIndex = counterArray.size()-1;
		saveToFile(counterArray);
	}
	
	public void removeCurrentCounter() {
		if (counterArray.size() > 0) {
			counterArray.remove(counterArray.size()-1);
			saveToFile(counterArray);
		}
	}
	
	 private ArrayList<Counter> loadFromFile() {
	    	
	    	ArrayList<Counter> newCounter;
	    	ArrayList<String> tempStringArray = new ArrayList<String>();
	    	
	        SharedPreferences restore = context.getSharedPreferences(CONTROLLER_PREFS, 0);
	        Integer countSize = restore.getInt(NUM_COUNTERS, 0);
	        counterIndex = restore.getInt(SAVE_INDEX, 0);
	        for (Integer i = 0; i < countSize; ++i) {
	        	String keyString = i.toString();
	        	tempStringArray.add(restore.getString(keyString, ""));
	        }
			if (tempStringArray.isEmpty()) return null;
			
	    	newCounter = setCounterArray(tempStringArray);
	    		
	    	return newCounter;
	    }
	 
	 private void saveToFile(ArrayList<Counter> saveCounter) {
	    	
	   	ArrayList<String> save = convertCounterArrayToStrings(saveCounter);
	   	SharedPreferences saving = context.getSharedPreferences(CONTROLLER_PREFS, 0);
	   	SharedPreferences.Editor editor = saving.edit();
	   	editor.clear();
	   	editor.putInt(NUM_COUNTERS, save.size());
	   	editor.putInt(SAVE_INDEX, counterIndex);
	   	for (Integer i = 0; i < save.size(); ++i) {
	   		String keyString = i.toString();
	   		editor.putString(keyString, save.get(i));
	    }

	        // Commit the edits!
	    editor.commit();
	    }
	 
		public ArrayList<Counter> setCounterArray(ArrayList<String> stringArrayIn) {
			ArrayList<Counter> tempArray = new ArrayList<Counter>();
			
				for (String str : stringArrayIn) {
					String[] strSplit= str.split("%");
					Counter newCounter = new Counter(strSplit[0]);
					for (int i = 1; i < strSplit.length; ++i) {
						newCounter.addTimeStamp(strSplit[i]);
					}
					tempArray.add(newCounter);
				}
				
			return tempArray;
		}
		
		public ArrayList<String> convertCounterArrayToStrings(ArrayList<Counter> counterA) {
			
			ArrayList<String> strOut = new ArrayList<String>();
			for (Counter count : counterA) {
				strOut.add(count.convertToString());
			}
			return strOut;
		}

}
