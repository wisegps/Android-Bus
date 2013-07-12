package com.wise.androidbus;

import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.ImageView;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.Paint.Style;
public class MainActivity extends Activity {
	private SurfaceView mSurfaceView = null;
	private SurfaceHolder mSurfaceHolder = null;
	int screenWidth;
	private ImageView imageView = null;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}
	
	
	class MyThread extends Thread {
		public boolean isRun;
		public MyThread() {
			isRun = true;
		}
		@Override
		public void run() {
			int count = 0; //测试
			int Number = 10; //站点数目
			int TextSize = 10; //字体大小
			int yTop = 120;//边距
			int Distance = 20; //2条线之间的距离
			int yLeft = 20;//左右边距
			int Radius = 5; //站点半径
			float UnitWidth = (float)(screenWidth - yLeft*2)/(Number-1); //每个站点之间的距离
			System.out.println("screenWidth="+screenWidth);
			System.out.println("UnitWidth="+UnitWidth);
			Bitmap bmpDown = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			Bitmap bmpUp = BitmapFactory.decodeResource(getResources(), R.drawable.car2);

			System.out.println("isRun" + isRun);
			while (isRun) {
				Canvas c = mSurfaceHolder.lockCanvas();// 锁定画布，一般在锁定后就可以通过其返回的画布对象Canvas，在其上面画图等操作了。
				c.drawColor(Color.WHITE);// 设置画布背景颜色
				
				Paint paint = new Paint(); // 创建画笔
				paint.setTextSize(TextSize);
				paint.setColor(Color.RED);
				//公交站点
				Paint busSitePaint = new Paint();
				busSitePaint.setARGB(200, 220, 20, 60);
				
				Paint busSiteLightPaint = new Paint();
				busSiteLightPaint.setColor(Color.YELLOW);
				
				Paint borderPaint = new Paint();//圆边框
				borderPaint.setARGB(255, 128, 0, 0); // white  
		        borderPaint.setAntiAlias(true); // 抗锯齿  
		        borderPaint.setStyle(Style.STROKE); //描边，和Style.Fill相对  
		        borderPaint.setStrokeWidth(2); 
				//往返公交线路
				Paint busPaint1 = new Paint();
				busPaint1.setColor(Color.GREEN);
				Paint busPaint2 = new Paint();
				busPaint2.setColor(Color.BLUE);
				//虚线
				Paint dashedPaint = new Paint();
				dashedPaint.setStyle(Paint.Style.STROKE);
				dashedPaint.setAntiAlias(false);
				dashedPaint.setColor(Color.GRAY);			     
		        PathEffect effects = new DashPathEffect(new float[]{2,2,2,2},1);
		        dashedPaint.setPathEffect(effects);
				//划2条公交线
				c.drawLine(yLeft, yTop, (screenWidth-yLeft), yTop, busPaint1);
				c.drawLine(yLeft, (yTop + Distance), (screenWidth-yLeft), (yTop + Distance), busPaint2);
				//划公交站点
				for(int i = 0 ; i < Number ; i++){
					//判断公交站台名称对应的y坐标
					c.drawLine(yLeft + i * UnitWidth, yTop, yLeft + i * UnitWidth , yTop - 50 - (i%3)*15 , dashedPaint);//虚线
					c.drawText("站牌名称", yLeft + i * UnitWidth -20, yTop - 50 - (i%3)*15, paint);//站牌					
					c.drawLine(yLeft + i * UnitWidth, (yTop + Distance), yLeft + i * UnitWidth , yTop + 75 + (i%3)*15 , dashedPaint);
					c.drawText("站牌名称", yLeft + i * UnitWidth -20, yTop + 75 + (i%3)*15 , paint);//站牌						
					if(i == 4){
						c.drawCircle(yLeft + i * UnitWidth, yTop, Radius, busSiteLightPaint);//圆
					}else{
						c.drawCircle(yLeft + i * UnitWidth, yTop, Radius, busSitePaint);//圆
					}
					c.drawCircle(yLeft + i * UnitWidth, yTop, Radius, borderPaint);//边框
					c.drawText("粤" +i, yLeft + Radius/2 - TextSize/2 + (i * UnitWidth ), (yTop - 10), paint);//车牌
					c.drawBitmap(bmpUp, yLeft + (i * UnitWidth ) - bmpUp.getWidth()/2, (yTop - 30) - bmpUp.getHeight()/2, paint); //划车辆
					
					c.drawCircle(yLeft + i * UnitWidth , (yTop + Distance), Radius, busSitePaint);//圆
					c.drawCircle(yLeft + i * UnitWidth , (yTop + Distance), Radius, borderPaint);
					c.drawText("粤" +i, yLeft + Radius/2 - TextSize/2 + (i * UnitWidth ), (yTop + Distance +15), paint);//车牌
					c.drawBitmap(bmpDown, yLeft + (i * UnitWidth ) - bmpUp.getWidth()/2, (yTop + Distance +15), paint); //划车辆			
				}
				c.drawText("这是第" + (count++) + "秒", 10, 300, paint);				
				mSurfaceHolder.unlockCanvasAndPost(c);
				try {				
					Thread.sleep(10000);// 睡眠时间为1秒
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void init(){
		imageView = (ImageView) findViewById(R.id.image_view);
		mSurfaceView = (SurfaceView)findViewById(R.id.surfaceView1);
        mSurfaceHolder = mSurfaceView.getHolder();
        mSurfaceHolder.addCallback(new MyHolder());
        
        WindowManager manage=getWindowManager();
		Display display=manage.getDefaultDisplay();
		screenWidth=display.getWidth();
	}
	class MyHolder implements SurfaceHolder.Callback{
		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width,int height) {}
		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			new Thread(new MyThread()).start();
		}
		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {}
	}
}
