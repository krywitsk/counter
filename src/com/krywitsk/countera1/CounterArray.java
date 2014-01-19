package com.krywitsk.countera1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;

public class CounterArray {
	
	private ArrayList<Counter> counters;
	private final String SEPERATOR = "###";
	
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
	

	public void savePersistent(FileOutputStream fileStream) {
		try {
			OutputStreamWriter outStream = new OutputStreamWriter(fileStream);
			
			//write name and date data
			for (Counter item : counters) {
				outStream.write(item.getName());
				for (Date tstamp : item.getTimeStamps()) {
					outStream.write(tstamp.toString());

				}
				outStream.write(SEPERATOR);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}

	}
	
	public void restorePersistent(FileInputStream inStream) {
		
		try {
			//instantiate our file reader objects
			
			Integer testInt = inStream.read();
			System.out.println(testInt.toString());
			
			/*
			InputStreamReader iReader = new InputStreamReader(inStream);
			BufferedReader bReader = new BufferedReader(iReader);
			
			String readLine = bReader.readLine();
			System.out.println(readLine);
			
			while (readLine != null) {
				counters.add(new Counter(readLine));
				readLine = bReader.readLine();
				System.out.println("Read something!");
				while (readLine != SEPERATOR) {
					counters.get(counters.size()-1).addTimeStampString(readLine);
				}
				readLine = bReader.readLine();
				readLine = bReader.readLine();
				//System.out.println(readLine);
			}
			
			*/
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		

	}
}
