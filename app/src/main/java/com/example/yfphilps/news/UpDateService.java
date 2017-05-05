package com.example.yfphilps.news;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class UpDateService extends Service {

    private static WindowManager windowManagers;

    public UpDateService() {

    }

    private static final String ACTION_BAZ = "com.utalk.ui.helper.action.BAZ";
    private static final String EXTRA_PARAM1 = "com.utalk.ui.helper.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.utalk.ui.helper.extra.PARAM2";
    private static final String EXTRA_PARAM3 = "com.utalk.ui.helper.extra.PARAM3";
    /**
     * @param context 上下文
     * @param path    服务器路径
     * @param version 版本号
     * @param mark    调用 0 ，自动检查，1 为按钮点击
     */
    public static void startActionBaz(Context context, String path, String version, String mark) {
        Intent intent = new Intent(context, UpDateService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, path);
        intent.putExtra(EXTRA_PARAM2, version);
        intent.putExtra(EXTRA_PARAM3, mark);
        context.startService(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                final String mark = intent.getStringExtra(EXTRA_PARAM3);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent dialogIntent = new Intent(getBaseContext(), DialogActivity.class);
                        dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        getApplication().startActivity(dialogIntent);
//                context.startActivity(new Intent(context, DialogActivity.class));
                    }
                }, 2000);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handleActionBaz(param1, param2, mark);
                    }

                }).start();
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void handleActionBaz(String param1, String param2, String mark) {
//        showDialog();

    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("=====", "onCreate: ");

        String topActivity = getTopActivity( getApplicationContext());
        try {
            Class<?> forName = Class.forName(topActivity);

//            AlertDialog.Builder builder = new AlertDialog.Builder();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Log.d("===", "onCreate: "+topActivity);
    }

    private String getTopActivity(Context context)

    {

        ActivityManager manager = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);

        List<ActivityManager.RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);


        if (runningTaskInfos != null)

            return (runningTaskInfos.get(0).topActivity).toString();

        else

            return null;

    }

    private void show() {

        WindowManager mWm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialogview, null);
        WindowManager.LayoutParams mParams = new WindowManager.LayoutParams();
        mParams.height = -2;//WRAP_CONTENT
//        para.width = -1;//WRAP_CONTENT
        mParams.width = (int) (getWallpaperDesiredMinimumWidth() * 0.8);
        mParams.format = 1;
        mParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        mParams.type = WindowManager.LayoutParams.TYPE_TOAST;
        mWm.addView(view, mParams);
    }

    private void showDialog() {
        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialogview, null);
        AlertDialog dialog = new AlertDialog.Builder(new ContextThemeWrapper(getApplicationContext(), android.R.style.Theme_Holo_Light_Dialog)).create();
        Button btn_1 = (Button) view.findViewById(R.id.btn_1);
        Button btn_2 = (Button) view.findViewById(R.id.btn_2);
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "btn_1", Toast.LENGTH_SHORT).show();
                stopSelf();
            }
        });
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "btn_2", Toast.LENGTH_SHORT).show();
                stopSelf();
            }
        });
        dialog.show();
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.width = (int) (windowManagers.getDefaultDisplay().getWidth() * 0.8);
        params.height = (int) (windowManagers.getDefaultDisplay().getHeight() * 0.25);
        dialog.getWindow().setAttributes(params);
        dialog.getWindow().setContentView(view);
    }
}
