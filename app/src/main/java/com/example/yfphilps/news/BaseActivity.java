package com.example.yfphilps.news;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yfphilps.news.view.GestureScrollView;

import net.lawyee.mobilelib.Constants;
import net.lawyee.mobilelib.json.JsonParser;
import net.lawyee.mobilelib.utils.MobileInfoUtil;
import net.lawyee.mobilewidget.slidingmenu.app.SlidingActivityHelper;

import java.util.LinkedList;
import java.util.List;

/**
 * All rights Reserved, Designed By www.lawyee.com
 *
 * @version V 1.0 xxxxxxxx
 * @Title: News
 * @Package com.example.yfphilps.news
 * @Description: $todo$
 * @author: YFL
 * @date: 2017/5/3 17:12
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2017 www.lawyee.com Inc. All rights reserved.
 * 注意：本内容仅限于北京法意科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */


public class BaseActivity extends Activity {

    /**
     * 更改主题的头标题
     */
    public String mThemeName = "";

    // wx967daebe835fbeac是你在微信开发平台注册应用的AppID, 这里需要替换成你注册的AppID
    String appID = "wx5a913c6f12c3b50d";


    private MobileInfoUtil mMobileInfoUtil;

    public GestureDetector gesture;

    public GestureScrollView scrollview;

    /**
     * 手势滑动的距离的判断基准
     */
    private int verticalMinDistance = 150;

    /**
     * 手势滑动的速度的判断基准
     */
    private int minVelocity = 30;

    public SlidingActivityHelper mHelper;

    protected static final String TAG = BaseActivity.class.getSimpleName();

    /**
     * 项目名
     */
    public static final String CSTR_APPLICATION_NAME = "net.lawyee.liuzhouapp";

    /**
     * 退出消息
     */
    public static final int CINT_WHAT_EXITAPPLICATION = 10000;
    /**
     * 用户登录
     */
    public static final int CINT_REQUESTCODE_USERLOGIN = 10003;
    /**
     * 用户注册
     */
    public static final int CINT_REQUESTCODE_USERREG = 10004;
    /**
     * 系统设置
     */
    public static final int CINT_REQUESTCODE_SETTING = 10005;
    /**
     * 用户修改信息
     */
    public static final int CINT_REQUESTCODE_USERMODIFYINFO = 10006;
    /**
     * 设置服务器信息
     */
    public static final int CINT_REQUESTCODE_MODIFYSERVER = 10007;

    /**
     * 用户栏目变动RequestCode
     */
    public static final int CINT_REQUESTCODE_SUBCHANNELSEL = 10011;

    /**
     * 邮箱RequestCode
     */
    public static final int CINT_REQUESTCODE_MAIL = 10012;

    /**
     * 其他RequestCode
     */
    public static final int CINT_REQUESTCODE_OTHER = 10013;

    /**
     * 捕获返回键
     */
    private Boolean mCaptureBackKey = false;
    private View.OnKeyListener mKeyDownListener = null;

    /**
     * 关闭程序时的提示信息
     */
    private String mCloseApplicationAskMsg = "";

    /**
     * ActivityResultListeners
     */
    private List<PreferenceManager.OnActivityResultListener> activityResultListeners = new LinkedList<PreferenceManager.OnActivityResultListener>();

    /**
     * onCreate 调用initView初始化界面 调用 initActions初始化点击操作
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mHelper = new SlidingActivityHelper(this);
        mHelper.onCreate(savedInstanceState);


        // 初始化lib中的变量
        Constants.DataFileDir = getString(R.string.app_datafiledir);
        JsonParser.PACKAGE_NAME = "net.lawyee.liuzhouapp.vo.";
        if (!"".equals(getString(R.string.theme_name))) {
            mThemeName = getString(R.string.theme_name) + "_";
            //JsonParser.PACKAGE_NAME = "net.lawyee."+getString(R.string.theme_name)+"app.vo.";
        }
        Log.d(TAG, "DATASTOREDIR:" + Constants.getDataStoreDir());

        overridePendingTransition(R.anim.comm_activity_left_in,
                R.anim.comm_activity_left_out);

        mMobileInfoUtil = MobileInfoUtil.getUtil();
        gesture = new GestureDetector(this, new MyGestureListener());//触屏事件监听

//        initView();
//
//        initDataContent();
//
//        regShareListener();


    }

    /**
     * @param resid
     * @param sel
     */

    public void setImageViewSel(int resid, boolean sel) {
        View view = findViewById(resid);
        if (view == null)
            return;
        ((ImageView) view).setSelected(sel);
    }

    public void setTextViewText(int resid, String text) {
        View view = findViewById(resid);
        if (view == null)
            return;
        ((TextView) view).setText(text);
    }

    /**
     * *************************************************************************
     * *
     */

    public void onBackPressed() {
        Log.e("onBackPressed", "onBackPressed");
        super.onBackPressed();
        overridePendingTransition(R.anim.comm_activity_right_in,
                R.anim.comm_activity_right_out);

    }

    class MyGestureListener implements GestureDetector.OnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {

            // 右滑
            if (e2.getX() - e1.getX() > verticalMinDistance
                    && Math.abs(velocityX) > minVelocity) {

                onBackPressed();
            }

            return false;
        }

        class MyOnTouchListener implements View.OnTouchListener {

            @SuppressLint("ClickableViewAccessibility")
            public boolean onTouch(View v, MotionEvent event) {
                return gesture.onTouchEvent(event);
            }

        }

        public void setMoveBack(View v) {
            v.setOnTouchListener(new MyOnTouchListener());
        }
    }
}
