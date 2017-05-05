package net.lawyee.mobilewidget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

public class ExHorizontalScrollView extends HorizontalScrollView{

	private OnScrollViewTouchPositionListener myOnTouchListener=null;
	public ExHorizontalScrollView(Context context) {
		super(context);
	}
	
	public ExHorizontalScrollView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	@Override
	public void scrollTo(int x, int y) {
		
		//Log.v("ExHorizontalScrollView", "X:"+x+"Y:"+y);
		if (myOnTouchListener != null) {
			myOnTouchListener.Click(x, y);
		}
		
		super.scrollTo(x, y);
	}
	
	public void setOnTouchClick(OnScrollViewTouchPositionListener listener){
		myOnTouchListener = listener;
		
	}
	


}
