package com.krywitsk.countera1;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//fragment representing one counter object
public class CounterFragment extends Fragment {

	public static int counterNum;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		
		View rootView = inflater.inflate(R.layout.counter_view, container, false);

		
		return rootView;
	}

	public int getCounterNum() {
		return counterNum;
	}
	

}
