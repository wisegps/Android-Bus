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
	//绘图控件
	private Paint titlePaint = null;
	private Canvas canvas = null; 
	private Bitmap bitmap = null;
	private Paint textPaint = null;
	
	//绘图参数
	public static int rectFillLeft = 0;
	public static int rectFillRight = 0;
	public static int rectFillTop = 0;
	public static int rectFillBottom = 0;
	public static String title = ""; 
	public MyCanvas(Context context) {
		super(context);
		setBackgroundColor(Color.parseColor("#0871DC"));
		
		//创建画笔
		titlePaint = new Paint();
		titlePaint.setAntiAlias(true);//设置画笔不显示锯齿
		titlePaint.setStrokeWidth(2);  //设置画笔的宽度
		titlePaint.setStyle(Style.FILL);
		titlePaint.setColor(Color.parseColor("#7A160B"));
		
		//绘制文字
		textPaint = new Paint();
		textPaint.setAntiAlias(true);
		textPaint.setStyle(Style.STROKE);
		textPaint.setColor(Color.parseColor("#ffffff"));
		
		//创建画布
		canvas = new Canvas();
		bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
		canvas.setBitmap(bitmap);
	}
	//绘制图形
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawText(title, 3, 16, textPaint);
		//绘制圆角矩形
//		canvas.drawRoundRect(outerRect, outerRadiusRat, outerRadiusRat, paint);
		canvas.drawRect(rectFillLeft, rectFillTop, rectFillRight, rectFillBottom, titlePaint);
	
	}
	
}
