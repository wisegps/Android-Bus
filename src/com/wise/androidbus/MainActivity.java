package com.wise.androidbus;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}
	/**
	 * Honesty 2013-7-12
	 * ≥ı ºªØ
	 */
	private void init(){
		
	}
	
	/**
	 * wang sir 2013-7-12
	 * ÃÌº”
	 */
	private void add(){
		
	}
	private void test(){
		//abcdef
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
