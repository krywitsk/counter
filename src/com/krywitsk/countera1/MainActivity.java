package com.krywitsk.countera1;


import java.util.ArrayList;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView counterValue;
	TextView counterName;
	
	EditText alertIn;
	
	CounterArrayController countControl;
	
	String tempNewName = "Temp";
	
	ArrayList<String> counterNameList;
	
	Dialog counterDialog;
	

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		counterValue = (TextView) findViewById(R.id.counter_value);
		counterName = (TextView) findViewById(R.id.counter_name);
		
		counterDialog = createDialog();
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		
		countControl = new CounterArrayController(this);
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


    public Dialog createDialog() {
        // Use the Builder class for convenient dialog construction
    	
    	// Create adapter for list
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, 
                android.R.layout.simple_list_item_1, counterNameList);
        
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.select_counter);
        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            	countControl.setCurrentCounterIndex(which);
        }
        });
        
        

        // Create the AlertDialog object and return it
        return builder.create();
    }
	
	
    @Override
    //for action bar buttons
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) { 
            case R.id.add_new_counter:
            	countControl.addNewCounter(tempNewName);

            case R.id.remove_counter:
            	countControl.removeCurrentCounter();
            	
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    
    public void resetButton(View view) {
    	countControl.resetCurrentCounter();
    }
    
    public void incrementButton(View view) {
    	countControl.incrementCurrentCounter();
    }
    
    public void tempSelect(View view) {
    	counterDialog.show();
    }
    

}
