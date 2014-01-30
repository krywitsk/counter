package com.krywitsk.countera1;

import java.util.ArrayList;

import android.content.Context;
import android.content.SharedPreferences;

public class CounterArrayController {

	private static int countIndex;

	public static final String SAVED_PREFS = "SavedPrefs";
	public static final String SAVE_INDEX = "SaveInd";
	public static final String NUM_COUNTERS = "numCounters";
	public static final String CONTROLLER_PREFS = "prefs";
	
	private static ArrayList<Counter> counterArray;
	Context context;
	
	public CounterArrayController(Context context) {
		// TODO Auto-generated constructor stub
		
		this.context = context;
		
		ArrayList<Counter> loadedCounterArray = loadFromFile();
		if (loadedCounterArray == null) {
			
			counterArray = new ArrayList<Counter>();
			countIndex = 0;
			System.out.println("Creating New Data");

		} else {
			System.out.println("Restoring Data");
			counterArray = loadedCounterArray;
		}
	}
	
	public String getCurrentCounterName() {
		if (!(counterArray.isEmpty())) {
			return counterArray.get(countIndex).getName();
		} else {
			return "_ _ _";
		}
	}
	
	public ArrayList<Counter> getCounterArrayList() {
		return counterArray;
	}
	
	//Generate every time??
	public String[] getCounterNameArray() {
		
		String[] names = new String[counterArray.size()];

		for (int i = 0; i < counterArray.size(); ++i) {
			names[i] = counterArray.get(i).getName() + " - " + counterArray.get(i).getCount().toString();
		}
		
		return names;
	}
	
	public String getCurrentCounterCount() {
		if (!counterArray.isEmpty()) {
			return counterArray.get(countIndex).getCount().toString();
		} else {
			return "";
		}
	}
	
	public void setCurrentCounterInd(int index) {
		if (index >= 0 && index < (counterArray.size())) {
			countIndex = index;
		}
		saveToFile(counterArray);
	}
	
	public void incrementCurrentCounter() {
		if (!counterArray.isEmpty()) {
			counterArray.get(countIndex).incrementCount();
			saveToFile(counterArray);
		}
	}
	
	public void resetCurrentCounter() {
		if (!counterArray.isEmpty()) {
			counterArray.get(countIndex).resetCount();
			saveToFile(counterArray);
		} 
	}
	
	public void addNewCounter(String newName) {
			
		//WHY MUST WE ADD TWICE - COUNTERS DON'T ADD OTHERWISE
		counterArray.add(new Counter(newName));
		counterArray.add(new Counter(newName));

		//DOESN"T MAKE ANY SENSE

		for (Counter ct : counterArray) {
			System.out.println(ct.getName());
		}
		
		countIndex = (counterArray.size() - 1);

		saveToFile(counterArray);
	}
	
	public void removeCurrentCounter() {
		if (counterArray.size() > 0) {

			counterArray.remove(countIndex);
			if (countIndex > 0) {
				countIndex--;
			}
			saveToFile(counterArray);
		}
	}
	
	 private ArrayList<Counter> loadFromFile() {
	    	
		 ArrayList<Counter> tempArray = new ArrayList<Counter>();
	    	ArrayList<String> tempStringArray = new ArrayList<String>();
	    	
	        SharedPreferences restore = context.getSharedPreferences(CONTROLLER_PREFS, 0);
	        Integer countSize = restore.getInt(NUM_COUNTERS, 0);
	        countIndex = restore.getInt(SAVE_INDEX, 0);
	        for (Integer i = 0; i < countSize; ++i) {
	        	String keyString = i.toString();
	        	tempStringArray.add(restore.getString(keyString, ""));
	        }
			if (tempStringArray.isEmpty()) return null;
			
			for (String str : tempStringArray) {
				String[] strSplit= str.split("%");
				Counter newCounter = new Counter(strSplit[0]);
				for (int i = 1; i < strSplit.length; ++i) {
					newCounter.addTimeStamp(strSplit[i]);
				}
				tempArray.add(newCounter);
			}
			
	    	return tempArray;
	    }
	 
	 private void saveToFile(ArrayList<Counter> saveCounter) {
	    	
	   	ArrayList<String> save = convertCounterArrayToStrings(saveCounter);
	   	SharedPreferences saving = context.getSharedPreferences(CONTROLLER_PREFS, 0);
	   	SharedPreferences.Editor editor = saving.edit();
	   	editor.clear();
	   	editor.putInt(NUM_COUNTERS, save.size());
	   	editor.putInt(SAVE_INDEX, countIndex);
	   	for (Integer i = 0; i < save.size(); ++i) {
	   		String keyString = i.toString();
	   		editor.putString(keyString, save.get(i));
	    }

	        // Commit the edits!
	    editor.commit();
	    }
	 
		
		private ArrayList<String> convertCounterArrayToStrings(ArrayList<Counter> counterA) {
			
			ArrayList<String> strOut = new ArrayList<String>();
			for (Counter count : counterA) {
				strOut.add(count.convertToString());
			}
			return strOut;
		}
		
		public void clearSaveData() {
			SharedPreferences saving = context.getSharedPreferences(CONTROLLER_PREFS, 0);
		   	SharedPreferences.Editor editor = saving.edit();
		   	editor.clear();
		   	editor.commit();
		}

}
