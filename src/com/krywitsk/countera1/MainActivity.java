package com.krywitsk.countera1;


import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity  {

	TextView counterValue;
	TextView counterName;
	
	EditText alertIn;
	
	private CounterArrayController countControl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		this.counterValue = (TextView) findViewById(R.id.counter_value);
		this.counterName = (TextView) findViewById(R.id.counter_name);
		
		this.countControl = new CounterArrayController(this);	
    	updateTextViews();
		
	}
	/*
	@Override
	protected void onStart() {
		super.onStart();
		
		//countControl = new CounterArrayController(this);
	}
	*/
	
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


    public void createListDialog() {
        // Not sure if creating a new dialog every time is more efficient
    	//Array adapter might be less expensive
    	
    	String[] strNames = countControl.getCounterNameArray();
    	
    	//TO FIX ARRAYLIST ADD BUG
    	//String[] strNames = new String[str.length-1];
    	
    	
        //adapter.notifyDataSetChanged();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.select_counter);
        builder.setItems(strNames, new DialogInterface.OnClickListener() {
			
            public void onClick(DialogInterface dialog, int which) {
            	System.out.println("Item selected = " + which);
            	countControl.setCurrentCounterInd(which);
            	updateTextViews();
        }
        });
        // Create the AlertDialog object and return it
        builder.create();
        builder.show();
    }
    
    
    public void createInputDialog() {
    	
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setTitle(R.string.enter_new_name);
    	builder.setMessage("Enter name"); //decide if string resource needed
    	
    	
    	final EditText inputText = new EditText(this);
    	builder.setView(inputText);
    	
    	builder.setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener() {
    		public void onClick(DialogInterface dialog, int which) {
    			String str = inputText.getText().toString();
            	if (!(str.isEmpty())) {
            		countControl.addNewCounter(str);
            		updateTextViews();
            	}
    			
    		}
    	}); 
    	
    	builder.setNegativeButton(R.string.button_cancel, new DialogInterface.OnClickListener() {
    		public void onClick(DialogInterface dialog, int which) {
    			
    		}
    	}); 
    	
    	builder.create();
    	builder.show();
    }
    
   
    private void updateTextViews() {
		counterName.setText(countControl.getCurrentCounterName());
		counterValue.setText(countControl.getCurrentCounterCount());
    }
	
    @Override
    //for action bar buttons
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) { 
            case R.id.add_new_counter:
            	createInputDialog();
            
            case R.id.remove_counter:
            	countControl.removeCurrentCounter();
            	updateTextViews();
            	
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    
    public void resetButton(View view) {
    	countControl.resetCurrentCounter();
    	updateTextViews();
    }
    
    public void incrementButton(View view) {
    	countControl.incrementCurrentCounter();
    	updateTextViews();
    }
    
    public void tempSelect(View view) {
       	createListDialog();
       	
    }
    
    public void changeToStats(View view) {
    	Intent intent = new Intent(this, StatsActivity.class);
    	startActivity(intent);
    }

}
