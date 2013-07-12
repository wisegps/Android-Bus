package com.wise.androidbus;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.app.Activity;
import android.content.Context;
public class MainActivity extends Activity {
	private ImageView imageView = null;
	/**
	 * ��ȡImageView �ؼ�������
	 * ���ڻ��Ʊ߿�
	 */
	private int imageViewTop = 0;
	private int imageViewButtom = 0;
	private int imageViewLeft = 0;
	private int imageViewRight = 0;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}
	
	private void initView(){
		imageView = (ImageView) findViewById(R.id.image_view);
		
		int location[] = new int[2];
		imageView.getLocationInWindow(location);
	}
	protected void onResume() {
		super.onResume();
		Log.e("top--->", imageView.getTop()+"");
		Log.e("bottom--->", imageView.getBottom()+"");
		Log.e("left--->", imageView.getLeft()+"");
		Log.e("right--->", imageView.getRight()+"");
	}
	
	
}
