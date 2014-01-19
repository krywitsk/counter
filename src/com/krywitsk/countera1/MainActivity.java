package com.krywitsk.countera1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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
	FileInputStream inputStream;
	
	TextView counterValue;
	TextView counterName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		counterIndex = 0;
		counterArray = new CounterArray();

		try {
			inputStream = openFileInput(FILENAME);
			counterArray.restorePersistent(inputStream);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		
		
		try {
			outputStream = openFileOutput(FILENAME, Context.MODE_PRIVATE);
		} catch (Exception e) {
			//error creating file
			e.printStackTrace();
		}
	    
	    	    
		counterValue = (TextView) findViewById(R.id.counter_value);
		counterName = (TextView) findViewById(R.id.counter_name);
		
		
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

    //method for saving activity state temporarily
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        counterArray.savePersistent(outputStream);
        System.out.println("Saved!");
        
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }
    
    @Override
    public void onStop() {
    	//outputStream.close();
    }
    
    public void resetButton(View view) {
    	counterArray.getCounter(counterIndex).resetCount();
    	counterValue.setText(counterArray.getCounter(counterIndex).getCount().toString());
    	counterArray.savePersistent(outputStream);
    }
    
    public void incrementButton(View view) {
    	counterArray.getCounter(counterIndex).incrementCount();
    	counterValue.setText(counterArray.getCounter(counterIndex).getCount().toString());
    	counterArray.savePersistent(outputStream);
    }
}
