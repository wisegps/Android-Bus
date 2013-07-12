package com.wise.androidbus;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.app.Activity;
import android.content.Context;
public class MainActivity extends Activity {
	private ImageView imageView = null;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}
	
	private void initView(){
		imageView = (ImageView) findViewById(R.id.image_view);
	}
}
