package com.example.yfphilps.news;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * All rights Reserved, Designed By www.lawyee.com
 *
 * @version V 1.0 xxxxxxxx
 * @Title: News
 * @Package com.example.yfphilps.news
 * @Description: $todo$
 * @author: YFL
 * @date: 2017/5/4 14:25
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2017 www.lawyee.com Inc. All rights reserved.
 * 注意：本内容仅限于北京法意科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */


public class DialogActivity extends Activity implements View.OnClickListener {

    private TextView tv_NewApktitle;
    private TextView tv_NowServsion;
    private TextView tv_NewServsion;
    private TextView textView;
    private TextView tv_NewApkSize;
    private TextView tv_NewApkContent;
    private Button btn_ignore;
    private Button btn_future;
    private Button btn_force;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog);
        initView();
        Log.d("DialogActivity", "onCreate: ");
//         startActivity(new Intent(DialogActivity.this,Main2Activity.class));
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, android.R.style.Theme_Holo_Light_Dialog));
        builder.setTitle("提示更新");
        builder.setMessage("22222222");
        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                Log.d("===", "onDismiss: ");
                DialogActivity.this.finish();
            }
        });
        if (!dialog.isShowing()) {
                Log.d("===", "===========: ");
            DialogActivity.this.finish();
        }

//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("aaa");
//        builder.setMessage("2222");
//        builder.show();

    }

    private void initView() {
        tv_NewApktitle = (TextView) findViewById(R.id.tv_NewApktitle);
        tv_NowServsion = (TextView) findViewById(R.id.tv_NowServsion);
        tv_NewServsion = (TextView) findViewById(R.id.tv_NewServsion);
        textView = (TextView) findViewById(R.id.textView);
        tv_NewApkSize = (TextView) findViewById(R.id.tv_NewApkSize);
        tv_NewApkContent = (TextView) findViewById(R.id.tv_NewApkContent);
        btn_ignore = (Button) findViewById(R.id.btn_ignore);
        btn_future = (Button) findViewById(R.id.btn_future);
        btn_force = (Button) findViewById(R.id.btn_force);

        btn_ignore.setOnClickListener(this);
        btn_future.setOnClickListener(this);
        btn_force.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ignore:
                finish();
                break;
            case R.id.btn_future:
                finish();
                break;
            case R.id.btn_force:
                finish();
                break;
        }
    }
}
