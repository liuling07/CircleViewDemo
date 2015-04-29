package com.example.circleviewdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.lling.circleviewdemo.R;

/**
 * @Title: CircleView.java
 * @Description: 自定义圆形TextView
 * @author lling
 * @date 2015-4-23
 */
public class CircleView extends TextView {

	private static final int DEFAULT_COLOR = 0xff52ce90;   //默认背景颜色
	private static final int DEFAULT_COLOR_HOVER = 0xff34b574;   //默认触摸控件时背景颜色
	private Paint mBgPaint = new Paint();  //背景绘制画笔
	private int mBgColor;  //背景颜色
	private int mBgColorHover;  //触摸时背景颜色

	PaintFlagsDrawFilter mPfd = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG|Paint.FILTER_BITMAP_FLAG); 

	public CircleView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		//从参数列表中获取参数  
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CircleView);  
        mBgColor = ta.getColor(R.styleable.CircleView_bgColor, DEFAULT_COLOR);
        mBgColorHover = ta.getColor(R.styleable.CircleView_bgColorHover, DEFAULT_COLOR_HOVER);
        ta.recycle(); 
		mBgPaint.setAntiAlias(true);     //防止边缘的锯齿
		mBgPaint.setColor(mBgColor);
		setGravity(Gravity.CENTER);
		setClickable(true);
	}

	/**
	 * 布局文件中使用时调用的构造方法
	 * @param context
	 * @param attrs
	 */
	public CircleView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	/**
	 * 代码中实例化时调用的构造函数
	 * @param context
	 */
	public CircleView(Context context) {
		this(context, null, 0);
	}


	@Override
	public void draw(Canvas canvas) {
		canvas.setDrawFilter(mPfd);
		canvas.drawCircle(getWidth()/2, getHeight()/2, getHeight()/2, mBgPaint);
		super.draw(canvas);
	}

	
	/**
	 * 通过onTouchEvent方法监控是否是否触摸控件，并设置相应的背景色
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN:// 手指压下
			mBgPaint.setColor(mBgColorHover);
			invalidate();
			break;
		case MotionEvent.ACTION_UP:// 手指离开
			mBgPaint.setColor(mBgColor);
			invalidate();
		}
		return super.onTouchEvent(event);
	}
	
	/**
	 * 固定宽高相等
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int measuredWidth = getMeasuredWidth();
		int measuredHeight = getMeasuredHeight();
		int max = Math.max(measuredWidth, measuredHeight);
		setMeasuredDimension(max, max);
	}
	
	/**
	 * 设置背景颜色
	 * @param mBgColor
	 */
	public void setmBgColor(int mBgColor) {
		this.mBgColor = mBgColor;
	}
	
	/**
	 * 设置触摸时背景颜色
	 * @param mBgColorHover
	 */
	public void setmBgColorHover(int mBgColorHover) {
		this.mBgColorHover = mBgColorHover;
	}

}
