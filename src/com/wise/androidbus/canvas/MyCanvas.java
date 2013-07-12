package com.wise.androidbus.canvas;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.Log;
import android.view.View;
public class MyCanvas extends View {
	//��ͼ�ؼ�
	private Paint titlePaint = null;
	private Canvas canvas = null; 
	private Bitmap bitmap = null;
	private Paint textPaint = null;
	
	//��ͼ����
	public static int rectFillLeft = 0;
	public static int rectFillRight = 0;
	public static int rectFillTop = 0;
	public static int rectFillBottom = 0;
	public static String title = ""; 
	public MyCanvas(Context context) {
		super(context);
		setBackgroundColor(Color.parseColor("#0871DC"));
		
		//��������
		titlePaint = new Paint();
		titlePaint.setAntiAlias(true);//���û��ʲ���ʾ���
		titlePaint.setStrokeWidth(2);  //���û��ʵĿ��
		titlePaint.setStyle(Style.FILL);
		titlePaint.setColor(Color.parseColor("#7A160B"));
		
		//��������
		textPaint = new Paint();
		textPaint.setAntiAlias(true);
		textPaint.setStyle(Style.STROKE);
		textPaint.setColor(Color.parseColor("#ffffff"));
		
		//��������
		canvas = new Canvas();
		bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
		canvas.setBitmap(bitmap);
	}
	//����ͼ��
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawText(title, 3, 16, textPaint);
		//����Բ�Ǿ���
//		canvas.drawRoundRect(outerRect, outerRadiusRat, outerRadiusRat, paint);
		canvas.drawRect(rectFillLeft, rectFillTop, rectFillRight, rectFillBottom, titlePaint);
	
	}
	
}
