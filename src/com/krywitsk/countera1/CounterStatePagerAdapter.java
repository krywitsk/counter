package com.krywitsk.countera1;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class CounterStatePagerAdapter extends FragmentStatePagerAdapter{


	public CounterStatePagerAdapter(FragmentManager frag) {
		super(frag);
	}

	@Override
	public android.support.v4.app.Fragment getItem(int counterNum) {
		Fragment fragment = new CounterFragment();
		Bundle args = new Bundle();
		fragment.setArguments(args);
		
		return fragment;
		
	}

	@Override
	//return number of counters NOT COMPLETED
	public int getCount() {
		return 0;
	}

}
