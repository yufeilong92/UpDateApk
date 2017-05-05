package net.lawyee.mobilewidget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * 解决ScrollView 和Gesture 冲突的问题
 * 
 * @author ZJD
 * 
 */
public class GestureScrollView extends ScrollView {
	GestureDetector gestureDetector;

	public GestureScrollView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public GestureScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public GestureScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public void setGestureDetector(GestureDetector gestureDetector) {
		this.gestureDetector = gestureDetector;
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		super.onTouchEvent(ev);
		return gestureDetector.onTouchEvent(ev);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		gestureDetector.onTouchEvent(ev);
		super.dispatchTouchEvent(ev);
		return true;
	}
}
