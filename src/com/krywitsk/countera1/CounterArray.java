package com.krywitsk.countera1;

import java.util.ArrayList;


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
}
