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
			int count = 0; //����
			int Number = 10; //վ����Ŀ
			int TextSize = 10; //�����С
			int yTop = 120;//�߾�
			int Distance = 20; //2����֮��ľ���
			int yLeft = 20;//���ұ߾�
			int Radius = 5; //վ��뾶
			float UnitWidth = (float)(screenWidth - yLeft*2)/(Number-1); //ÿ��վ��֮��ľ���
			System.out.println("screenWidth="+screenWidth);
			System.out.println("UnitWidth="+UnitWidth);
			Bitmap bmpDown = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			Bitmap bmpUp = BitmapFactory.decodeResource(getResources(), R.drawable.car2);

			System.out.println("isRun" + isRun);
			while (isRun) {
				Canvas c = mSurfaceHolder.lockCanvas();// ����������һ����������Ϳ���ͨ���䷵�صĻ�������Canvas���������滭ͼ�Ȳ����ˡ�
				c.drawColor(Color.WHITE);// ���û���������ɫ
				
				Paint paint = new Paint(); // ��������
				paint.setTextSize(TextSize);
				paint.setColor(Color.RED);
				//����վ��
				Paint busSitePaint = new Paint();
				busSitePaint.setARGB(200, 220, 20, 60);
				
				Paint busSiteLightPaint = new Paint();
				busSiteLightPaint.setColor(Color.YELLOW);
				
				Paint borderPaint = new Paint();//Բ�߿�
				borderPaint.setARGB(255, 128, 0, 0); // white  
		        borderPaint.setAntiAlias(true); // �����  
		        borderPaint.setStyle(Style.STROKE); //��ߣ���Style.Fill���  
		        borderPaint.setStrokeWidth(2); 
				//����������·
				Paint busPaint1 = new Paint();
				busPaint1.setColor(Color.GREEN);
				Paint busPaint2 = new Paint();
				busPaint2.setColor(Color.BLUE);
				//����
				Paint dashedPaint = new Paint();
				dashedPaint.setStyle(Paint.Style.STROKE);
				dashedPaint.setAntiAlias(false);
				dashedPaint.setColor(Color.GRAY);			     
		        PathEffect effects = new DashPathEffect(new float[]{2,2,2,2},1);
		        dashedPaint.setPathEffect(effects);
				//��2��������
				c.drawLine(yLeft, yTop, (screenWidth-yLeft), yTop, busPaint1);
				c.drawLine(yLeft, (yTop + Distance), (screenWidth-yLeft), (yTop + Distance), busPaint2);
				//������վ��
				for(int i = 0 ; i < Number ; i++){
					//�жϹ���վ̨���ƶ�Ӧ��y����
					c.drawLine(yLeft + i * UnitWidth, yTop, yLeft + i * UnitWidth , yTop - 50 - (i%3)*15 , dashedPaint);//����
					c.drawText("վ������", yLeft + i * UnitWidth -20, yTop - 50 - (i%3)*15, paint);//վ��					
					c.drawLine(yLeft + i * UnitWidth, (yTop + Distance), yLeft + i * UnitWidth , yTop + 75 + (i%3)*15 , dashedPaint);
					c.drawText("վ������", yLeft + i * UnitWidth -20, yTop + 75 + (i%3)*15 , paint);//վ��						
					if(i == 4){
						c.drawCircle(yLeft + i * UnitWidth, yTop, Radius, busSiteLightPaint);//Բ
					}else{
						c.drawCircle(yLeft + i * UnitWidth, yTop, Radius, busSitePaint);//Բ
					}
					c.drawCircle(yLeft + i * UnitWidth, yTop, Radius, borderPaint);//�߿�
					c.drawText("��" +i, yLeft + Radius/2 - TextSize/2 + (i * UnitWidth ), (yTop - 10), paint);//����
					c.drawBitmap(bmpUp, yLeft + (i * UnitWidth ) - bmpUp.getWidth()/2, (yTop - 30) - bmpUp.getHeight()/2, paint); //������
					
					c.drawCircle(yLeft + i * UnitWidth , (yTop + Distance), Radius, busSitePaint);//Բ
					c.drawCircle(yLeft + i * UnitWidth , (yTop + Distance), Radius, borderPaint);
					c.drawText("��" +i, yLeft + Radius/2 - TextSize/2 + (i * UnitWidth ), (yTop + Distance +15), paint);//����
					c.drawBitmap(bmpDown, yLeft + (i * UnitWidth ) - bmpUp.getWidth()/2, (yTop + Distance +15), paint); //������			
				}
				c.drawText("���ǵ�" + (count++) + "��", 10, 300, paint);				
				mSurfaceHolder.unlockCanvasAndPost(c);
				try {				
					Thread.sleep(10000);// ˯��ʱ��Ϊ1��
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
