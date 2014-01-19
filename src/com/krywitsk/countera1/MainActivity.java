package com.krywitsk.countera1;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	private int counterIndex;
	private CounterArray counterArray;
	
	private final String FILENAME = "savedata.txt";
	FileOutputStream outputStream;
	
	TextView counterValue;
	TextView counterName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		counterIndex = 0;
		counterArray = new CounterArray();
		
		counterValue = (TextView) findViewById(R.id.counter_value);
		counterName = (TextView) findViewById(R.id.counter_name);
		
		try {
			  outputStream = openFileOutput(FILENAME, Context.MODE_PRIVATE);
			} catch (Exception e) {
			  e.printStackTrace();
			}
	
	
		//###########test code #################### creates default counter
		if (counterArray.isEmpty()) {
			counterIndex = counterArray.addCounter("Default");
		}
		//#################################################################
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
                    
            case R.id.remove_counter:
                    //remove counter
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    public void resetButton(View view) {
    	counterArray.getCounter(counterIndex).resetCount();
    	counterValue.setText(counterArray.getCounter(counterIndex).getCount().toString());
    }
    
    public void incrementButton(View view) {
    	counterArray.getCounter(counterIndex).incrementCount();
    	counterValue.setText(counterArray.getCounter(counterIndex).getCount().toString());
    }
}
