 package com.krywitsk.countera1;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.support.v4.app.NavUtils;

public class StatsActivity extends Activity implements OnItemSelectedListener {

	private CounterArrayController countControl;
	private StatsGenerator statsGen;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stats);
		// Show the Up button in the action bar.
		setupActionBar();
		
		Spinner spinner = (Spinner) findViewById(R.id.time_spinner);
		ArrayAdapter<CharSequence> spinAdapter = ArrayAdapter.createFromResource(this, R.array.time_selection_strings, android.R.layout.simple_spinner_item);
		spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(spinAdapter);
		spinner.setOnItemSelectedListener(this);
		
		countControl = new CounterArrayController(this);
		statsGen = new StatsGenerator();
	}
	
	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.stats, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			Intent intent = new Intent(this, MainActivity.class);
			//
			NavUtils.navigateUpTo(this, intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	//handles selection from spinner
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {
		String choice = parent.getItemAtPosition(pos).toString();
		
		//switch case cannot be used with string evidently
		if (choice.equals("Month")) {
			System.out.println("Month selected!");
			String[] strList = statsGen.generateList(countControl.getCounterArrayList());
		} else if (choice.equals("Week")) {
			//
		} else if (choice.equals("Day")) {
			//
		} else if (choice.equals("Hour")) {
			//
		}


		
	}

	//handles nothing being clicked in the spinner
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// do nothing
		
	}

}
