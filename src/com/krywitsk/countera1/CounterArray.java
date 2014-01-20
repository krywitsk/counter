package com.krywitsk.countera1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class CounterArray {
	
	private ArrayList<Counter> counters;
	
	public CounterArray() {
		counters = new ArrayList<Counter>();
	}
		
	//add new counter and return index
	public int addCounter(String name) {
		counters.add(new Counter(name));
		return counters.size()-1;
	}
	
	//need better error handling ############################################
	public Counter getCounter(int index) {
		if (index <= 0 && index < counters.size()) {
			return counters.get(index); 
		}
		return counters.get(0);
	}

	
	//greater than = or just greater than?
	public void removeCounter(int index) {
		if (index <= 0 && index < counters.size()) {
			counters.remove(index);
		}
	}
	
	public boolean isEmpty() {
		return counters.isEmpty();
	}
	
	public int getSize() {
		return counters.size();
	}
	
	public String convertCounterArrayToString() {
		String temp = new String();
		for(Counter counter : counters) {
			temp.concat(counter.convertToString());
			temp.concat("%");
		}
		return temp;
	}
	
	public ArrayList<Counter> convertStringToCounterArray(String strIn) {
		
		ArrayList<Counter> tempArray = new ArrayList<Counter>();
		
		//Separate string into separate counter strings
		String[] counterStrings = strIn.split("%");
		
		for (String string : counterStrings) {
			String[] counterStr = string.split("^");
			
			Counter tempCounter = new Counter(counterStr[0]);
			
			for (int i = 1; i < counterStr.length; ++i) {
				tempCounter.addTimeStamp(counterStr[i]);
			}	
			tempArray.add(tempCounter);
		}
		return tempArray;
	}

	public void savePersistent(FileOutputStream outputStream) {
		try {
			outputStream.write(this.convertCounterArrayToString().getBytes());
			outputStream.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void restorePersistent(FileInputStream inputStream) {
		
		System.out.println("Attempting to restore");
		StringBuffer strBuf = new StringBuffer("");
		
			//instantiate our file reader objects
			try {
				
				byte[] buffer = new byte[1024];
				while (inputStream.read(buffer) != -1) {
					strBuf.append(new String(buffer));
				}
				inputStream.close();

				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//convert string back to counter array
			this.counters = convertStringToCounterArray(strBuf.toString());
	}
}
