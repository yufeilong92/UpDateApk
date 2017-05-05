/**
* Project:newsreader2
* File:PageIndicatorView.java
* Copyright 2013 WUZHUPC Co., Ltd. All rights reserved.
*/
package net.lawyee.mobilewidget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 页数显示控件
 * @author wuzhu
 * @date 2013-5-15 下午2:29:05
 * @version $id$
 */
public class PageIndicatorView extends View
{

	/**
	 * 页数
	 */
	private int mPages;
	
	/**
	 * 当前页
	 */
	private int mNowPage;
	
	
	/**
	 * 普通的样式
	 */
	private Drawable mNormalStyle;
	
	/**
	 * 普通的颜色
	 */
	private int mNormalColor;
	
	/**
	 * 选中后的样式
	 */
	private Drawable mSelectedStyle;
	
	/**
	 * 选中后的颜色
	 */
	private int mSelectedColor;
	
	/**
	 * 普通的样式时画的半径
	 */
	private int mNormalRadius;
	
	/**
	 * 选中后的样式时画的半径
	 */
	private int mSelectedRadius;
	
	/**
	 * 间隔
	 */
	private float mInterval; 
	
	/**
	 * 
	 */
	private Paint mPaint=null;
	
	public PageIndicatorView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		TypedArray a=context.obtainStyledAttributes(attrs, R.styleable.PageIndicatorView);
		mPages=a.getInt(R.styleable.PageIndicatorView_pages, 0);
		mNowPage=a.getInt(R.styleable.PageIndicatorView_nowpage,0);
		
		mNormalStyle=a.getDrawable(R.styleable.PageIndicatorView_normalstyle);
		if(mNormalStyle==null)
		{
			mNormalStyle=context.getResources().getDrawable(android.R.color.white);
			mNormalColor=context.getResources().getColor(android.R.color.white);
		}
		else if(mNormalStyle instanceof ColorDrawable)
			mNormalColor=a.getColor(R.styleable.PageIndicatorView_normalstyle, context.getResources().getColor(android.R.color.white));
		
		mSelectedStyle=a.getDrawable(R.styleable.PageIndicatorView_selectedstyle);
		if(mSelectedStyle==null)
		{
			mSelectedStyle=context.getResources().getDrawable(android.R.color.white);
			mSelectedColor=context.getResources().getColor(android.R.color.white);
		}
		else if(mSelectedStyle instanceof ColorDrawable)
			mSelectedColor=a.getColor(R.styleable.PageIndicatorView_selectedstyle, context.getResources().getColor(android.R.color.white));
		
			
		mNormalRadius=a.getInt(R.styleable.PageIndicatorView_normalradius, 2);
		mSelectedRadius=a.getInt(R.styleable.PageIndicatorView_selectedradius, 5);		
		mInterval=a.getDimension(R.styleable.PageIndicatorView_interval, 20f);
		a.recycle();
	}

	@Override
	public void draw(Canvas canvas)
	{
		super.draw(canvas);
		
		if(mPages<=0) return;
		
		int availableWidth = getWidth();
		int availableHeight =getHeight();
		int x = availableWidth / 2;
		int y = availableHeight / 2;
		
		
		 //总宽度
		 float sumlen=(mPages-1)*mInterval;
		 float startx=x-sumlen/2;

		  
		 //
		 for(int i=0;i<mPages;i++)
		 {
			 int tmpx=(int)(startx+i*mInterval);
			 if(i==mNowPage-1)
			 {
				 //画当前页
				 if(mSelectedStyle instanceof ColorDrawable)
				 {
					 canvas.drawCircle(tmpx, y, mSelectedRadius, getPaint(mSelectedColor));
				 }
				 else
				 {
					 mSelectedStyle.setBounds(tmpx-mSelectedRadius, y-mSelectedRadius, tmpx+mSelectedRadius, y+mSelectedRadius);
					 mSelectedStyle.draw(canvas);
				 }
				 continue;
			 }
			 //画普通
			 if(mNormalStyle instanceof ColorDrawable)
			 {
				 canvas.drawCircle(tmpx, y, mNormalRadius, getPaint(mNormalColor));
			 }
			 else
			 {
				 mNormalStyle.setBounds(tmpx-mNormalRadius, y-mNormalRadius, tmpx+mNormalRadius, y+mNormalRadius);
				 mNormalStyle.draw(canvas);
			 }
		 }
		 canvas.restore();
	}
	
	private Paint getPaint(int color)
	{
		if(mPaint==null)
		{
		mPaint=new Paint();
		//抗锯齿
		mPaint.setAntiAlias(true);
		//
		mPaint.setStyle(Style.FILL);
		}
		mPaint.setColor(color);
		return mPaint;
	}
	
	/**
	 * 设置页数
	 * @param pages
	 */
	public void setPages(int pages)
	{
		if(pages<0||mPages==pages) return;
		mPages=pages;
		//默认第一页
		setNowPage(1);
		invalidate();
	}
	
	/**
	 * 页数
	 */
	public int getPages()
	{
		return mPages;
	}
	
	/**
	 * 设置当前页（设置完先设置好页数,从1开始）
	 * @param nowpage
	 */
	public void setNowPage(int nowpage)
	{
		if(nowpage==mNowPage||mPages==0) return;
		if(nowpage<=0) mNowPage=1;
		if(nowpage>mPages) mNowPage=mPages;
		mNowPage=nowpage;
		invalidate();
	}
	
	/**
	 * 下一页
	 */
	public void nextPage()
	{
		setNowPage(mNowPage+1);
	}
	
	/**
	 * 上一页
	 */
	public void prevPage()
	{
		setNowPage(mNowPage-1);
	}
	
	/**
	 * 当前页
	 * @return
	 */
	public int getNowPage()
	{
		return mNowPage;
	}
}
