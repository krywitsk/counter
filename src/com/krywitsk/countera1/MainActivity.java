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

	private TextView counterValue;
	private TextView counterName;
	
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


    private void createListDialog() {
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
    
    //create dialog for entering new counter name
    public void createInputDialog(boolean edit) {
    	
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	if (edit) {
    		builder.setTitle(R.string.edit_name);
    		builder.setMessage(R.string.enter_name); //decide if string resource needed
    	} else {
    		builder.setTitle(R.string.enter_new_name);
    		builder.setMessage(R.string.enter_name); //decide if string resource needed
    	}
    	
    	final EditText inputText = new EditText(this);
    	builder.setView(inputText);
    	
    	if (edit) {
    		builder.setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener() {
    			public void onClick(DialogInterface dialog, int which) {
    				String str = inputText.getText().toString();
    				if (!(str.isEmpty())) {
    					countControl.editCurrentName(str);
    					updateTextViews();
    				}
    			}
    		});
    	} else {
    		builder.setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener() {
    			public void onClick(DialogInterface dialog, int which) {
    				String str = inputText.getText().toString();
    				if (!(str.isEmpty())) {
    					countControl.addNewCounter(str);
    					updateTextViews();
    				}
    			}
    		});
    	}
    	
    	builder.setNegativeButton(R.string.button_cancel, new DialogInterface.OnClickListener() {
    		public void onClick(DialogInterface dialog, int which) {
    			
    		}
    	}); 
    	builder.create();
    	builder.show();
    }
    
    //refresh counter name and count textviews
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
            	createInputDialog(false);
            
            case R.id.remove_counter:
            	countControl.removeCurrentCounter();
            	updateTextViews();
            	
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    
    //handle button presses
    public void resetButton(View view) {
    	countControl.resetCurrentCounter();
    	updateTextViews();
    }
    
    public void incrementButton(View view) {
    	countControl.incrementCurrentCounter();
    	updateTextViews();
    }
    
    public void tempSelect(View view) {
    	if (!countControl.isArrayListEmpty()) {
       	createListDialog();
    	}
    }
    
    //start stats activity
    public void changeToStats(View view) {
    	if (!countControl.isArrayListEmpty()) {
    		Intent intent = new Intent(this, StatsActivity.class);
    		startActivity(intent);
    	}
    }
    
    public void editName(View view) {
    	if (!countControl.isArrayListEmpty()) {
    		createInputDialog(true);
    	}
    }

}
