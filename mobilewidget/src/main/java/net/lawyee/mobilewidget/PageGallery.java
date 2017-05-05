/**
* Project:newsreader2
* File:PageGallery.java
* Copyright 2013 WUZHUPC Co., Ltd. All rights reserved.
*/
package net.lawyee.mobilewidget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Gallery;

/**
 * 多页项展示组件，自定义Gallery，实现每次只滑动一个item
 * @author wuzhu
 * @date 2013-5-15 下午2:39:43
 * @version $id$
 */
@SuppressWarnings("deprecation")
public class PageGallery extends Gallery
{

	/**
	 * @param context
	 * @param attrs
	 */
	public PageGallery(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}


    @Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY)
	{
		// TODO Auto-generated method stub
		return super.onScroll(e1, e2, distanceX, distanceY);
	}

    

    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
    {
        int kEvent;
        if (isScrollingLeft(e1, e2))
        { // Check if scrolling left
            kEvent = KeyEvent.KEYCODE_DPAD_LEFT;
        }
        else
        { // Otherwise scrolling right

            kEvent = KeyEvent.KEYCODE_DPAD_RIGHT;
        }
        onKeyDown(kEvent, null);
        return true;

    }
    
    public void dradLeft()
    {
    	onKeyDown(KeyEvent.KEYCODE_DPAD_LEFT, null);
    }

    private boolean isScrollingLeft(MotionEvent e1, MotionEvent e2)
    {
        return e2.getX() > e1.getX();
    }
}
