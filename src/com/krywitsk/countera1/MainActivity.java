package com.krywitsk.countera1;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends FragmentActivity {
	
	CounterStatePagerAdapter adapter;

	ViewPager viewPager;
	
	ArrayList<Fragment> counterFrags; 

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//instantiate adapter
		adapter = new CounterStatePagerAdapter(getSupportFragmentManager());
		
		viewPager = (ViewPager) findViewById(R.id.mainPager);
		viewPager.setAdapter(adapter);
		
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
	        	Fragment testFrag = adapter.getItem(0);
	        	
	        case R.id.remove_counter:
	        	//remove counter
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

}
