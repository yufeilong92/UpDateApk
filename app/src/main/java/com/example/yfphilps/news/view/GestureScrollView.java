package com.example.yfphilps.news.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * All rights Reserved, Designed By www.lawyee.com
 *
 * @version V 1.0 xxxxxxxx
 * @Title: News
 * @Package com.example.yfphilps.news.view
 * @Description: $todo$
 * @author: YFL
 * @date: 2017/5/3 15:49
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2017 www.lawyee.com Inc. All rights reserved.
 * 注意：本内容仅限于北京法意科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */


public class GestureScrollView extends ScrollView {
    GestureDetector gestureDetector;

    public GestureScrollView(Context context) {
        super(context);
    }

    public GestureScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GestureScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setGestureDetector(GestureDetector gestureDetector) {
        this.gestureDetector = gestureDetector;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
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
