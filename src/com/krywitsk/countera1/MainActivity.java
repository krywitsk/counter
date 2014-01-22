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

	TextView counterValue;
	TextView counterName;
	
	EditText alertIn;
	
	CounterArrayController countControl;
	
	String tempNewName = "Temp";
	

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		counterValue = (TextView) findViewById(R.id.counter_value);
		counterName = (TextView) findViewById(R.id.counter_name);
		
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
    

}
