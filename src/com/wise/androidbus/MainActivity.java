package com.wise.androidbus;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.app.Activity;
import android.content.Context;
public class MainActivity extends Activity {
	private ImageView imageView = null;
	/**
	 * 获取ImageView 控件的坐标
	 * 用于绘制边框
	 */
	private int imageViewTop = 0;
	private int imageViewButtom = 0;
	private int imageViewLeft = 0;
	private int imageViewRight = 0;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		LayoutInflater layoutInflater = new LayoutInflater() {
//			
//			@Override
//			public LayoutInflater cloneInContext(Context newContext) {
//				// TODO Auto-generated method stub
//				return null;
//			}
//		};
		
		initView();
	}
	
	private void initView(){
		imageView = (ImageView) findViewById(R.id.image_view);
	}
}
