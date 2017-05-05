package net.lawyee.mobilelib.utils;

import java.util.Iterator;
import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Activity相关工具类，如：判断是否前端运行、发送Mail
 * @author wuzhu
 * @date 2013-12-12 09:22:00
 * @version $id$
 */
public class ActivityUtil
{

	private static final String TAG = ActivityUtil.class.getSimpleName();

	/**
	 * 当前应用是否在前端运行，如果是返回当前运行的ComponentName
	 * 
	 * @param context
	 * @param activitynames 当前应用程序的Activity名称 可以将 WelcomeActivity.class.getName(),HomeActivity.class.getName()传入
	 * @return
	 */
	public static ComponentName isCurAppRunning(Context context,List<String> activitynames)
	{
		ActivityManager am = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningTaskInfo> taskList = am.getRunningTasks(0xff);
		Log.i(TAG,
				"num = " + (0xff) + "\t context package = "
						+ context.getPackageName());
		Iterator<RunningTaskInfo> taskIt = taskList.iterator();
		
		while (taskIt.hasNext())
		{
			ActivityManager.RunningTaskInfo taskInfo = (ActivityManager.RunningTaskInfo) taskIt
					.next();

			ComponentName componentName = taskInfo.baseActivity;
			Log.i(TAG, "component name = " + componentName.getClassName());

			if (context.getPackageName().equals(componentName.getPackageName()))
			{
				Log.i(TAG,
						"topActivity name = "
								+ taskInfo.topActivity.getClassName());
				if(activitynames==null||activitynames.isEmpty())
					return taskInfo.topActivity;
				for(int i=0;i<activitynames.size();i++)
				{
					if(componentName.getClassName().equals(activitynames.get(i)))
						return taskInfo.topActivity;
				}
			}
		}

		return null;
	}

	/**
	 * 判断当前应用是否位于前端显示
	 * 
	 * @param context
	 * @param activitynames 当前应用程序的Activity名称 可以将 WelcomeActivity.class.getName(),HomeActivity.class.getName()传入
	 * @return
	 */
	public static boolean isCurAppRunningForeground(Context context,List<String> activitynames)
	{
		ActivityManager am = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningTaskInfo> taskList = am.getRunningTasks(1);

		if (taskList == null || taskList.isEmpty())
		{
			return false;
		}

		RunningTaskInfo taskInfo = taskList.get(0);

		ComponentName componentName = taskInfo.baseActivity;
		Log.i(TAG,
				"check foreground :: baseActivity = "
						+ componentName.getClassName());
		if(activitynames==null||activitynames.isEmpty())
			return true;
		for(int i=0;i<activitynames.size();i++)
		{
			if(componentName.getClassName().equals(activitynames.get(i)))
				return true;
		}

		return false;
	}

	/**
	 * 发送邮件
	 * @param context
	 * @param reciver
	 *            收件人
	 * @param subject
	 *            主题
	 * @param body
	 *            内容
	 * @param seltitle
	 *            弹出的选择框标题
	 */
	public static void sendMail(Context context, String reciver,
			String subject, String body, String seltitle)
	{
		Intent intent = new Intent(android.content.Intent.ACTION_SEND);
		intent.setType("plain/text");
		String[] email = new String[] { reciver }; // 需要注意，email必须以数组形式传入
		intent.setType("message/rfc822"); // 设置邮件格式
		intent.putExtra(Intent.EXTRA_EMAIL, email); // 接收人
		// intent.putExtra(Intent.EXTRA_CC, email); // 抄送人
		intent.putExtra(Intent.EXTRA_SUBJECT, subject); // 主题
		intent.putExtra(Intent.EXTRA_TEXT, body); // 正文
		context.startActivity(Intent.createChooser(intent, seltitle));
	}
}
