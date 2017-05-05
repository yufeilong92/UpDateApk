package com.example.yfphilps.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
       UpDateService.startActionBaz(this,"","","");
        startActivity(new Intent(MainActivity.this, Main2Activity.class));
        WindowManager windowManager = getWindowManager();

    }

    private void initView() {
        tv = (TextView) findViewById(R.id.tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("222222222");
                builder.setMessage("44444444");
                builder.show();
            }
        });
    }
}
