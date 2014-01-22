package com.krywitsk.countera1;


import java.util.ArrayList;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private static int counterIndex;

	public static final String SAVED_PREFS = "SavedPrefs";
	public static final String SAVE_INDEX = "SaveInd";
	public static final String NUM_COUNTERS = "numCounters";
	
	private ArrayList<Counter> counterArray;

	TextView counterValue;
	TextView counterName;
	
	EditText alertIn;
	
	final String[] names = {"One","Two","Three"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//need to restore index as well
		counterIndex = 0;
		
		counterValue = (TextView) findViewById(R.id.counter_value);
		counterName = (TextView) findViewById(R.id.counter_name);
		
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		
		ArrayList<Counter> newCounterArray = loadFromFile();
		if (newCounterArray == null) {
			counterIndex = 0;
			counterArray = new ArrayList<Counter>();
			counterArray.add(new Counter("DEFAULT"));
			counterName.setText(counterArray.get(counterIndex).getName());
			counterValue.setText(counterArray.get(counterIndex).getCount().toString());

		} else {
			counterArray = newCounterArray;
			//NOT ALWAYS
			counterIndex = counterArray.size()-1;
			//########
			counterName.setText(counterArray.get(counterIndex).getName());
			counterValue.setText(counterArray.get(counterIndex).getCount().toString());
		}
		
	}
	
	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

    @Override
    //for action bar buttons
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) { 
            case R.id.add_new_counter:
            	if (counterIndex >= 0) {
            		//test code
            		counterIndex++;
            		System.out.println(counterIndex);
            		counterArray.add(new Counter("CHANGEME"));
            		counterName.setText(counterArray.get(counterIndex).getName());
    				counterValue.setText(counterArray.get(counterIndex).getCount().toString());
            	}

            case R.id.remove_counter:
            	if (counterArray.size() >= 0) {
            		counterArray.remove(counterIndex);
            		counterIndex--;
            	}
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private ArrayList<Counter> loadFromFile() {
    	
    	ArrayList<Counter> newCounter;
    	ArrayList<String> tempStringArray = new ArrayList<String>();
    	
        SharedPreferences restore = getPreferences(0);
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
    	SharedPreferences saving = getPreferences(0);
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

	public void nextButton(View view) {
		if (counterIndex > 0) {
			counterIndex++;
		}
		counterArray.get(counterIndex).resetCount();
		counterValue.setText(counterArray.get(counterIndex).getCount().toString());
	}
	
	public void prevButton(View view) {
		if (counterIndex >= counterArray.size() -1) {
			counterIndex--;
		}
		counterArray.get(counterIndex).resetCount();
		counterValue.setText(counterArray.get(counterIndex).getCount().toString());
	}
    
    public void resetButton(View view) {
    	if (counterIndex >= 0) {
    		counterArray.get(counterIndex).resetCount();
    		counterValue.setText(counterArray.get(counterIndex).getCount().toString());
    		saveToFile(counterArray);
    	}
    }
    
    public void incrementButton(View view) {
    	if (counterIndex >= 0) {
    		counterArray.get(counterIndex).incrementCount();
    		counterValue.setText(counterArray.get(counterIndex).getCount().toString());
    		saveToFile(counterArray);
    	}
    }
    
    public void clearButton(View view) {
    	SharedPreferences saving = getPreferences(0);
        SharedPreferences.Editor editor = saving.edit();
        editor.clear();
        // Commit the edits!
        editor.commit();
    }
}
