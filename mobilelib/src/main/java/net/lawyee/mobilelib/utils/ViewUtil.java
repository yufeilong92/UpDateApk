package net.lawyee.mobilelib.utils;

import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

public class ViewUtil
{
	/**
	 * 获取屏幕宽
	 * @param act
	 */
	@SuppressWarnings("deprecation")
	public static int getScreenWidth(Context context)
	{
		WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		//WindowManager windowManager = act.getWindowManager();  
		Display display = windowManager.getDefaultDisplay();  
		
		return display.getWidth();
	}
	/**
	 * 获取屏幕宽
	 * @param act
	 */
	@SuppressWarnings("deprecation")
	public static int getScreenHeight(Context context)
	{
		WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		//WindowManager windowManager = act.getWindowManager();  
		Display display = windowManager.getDefaultDisplay(); 
		return display.getHeight();
	}
}
