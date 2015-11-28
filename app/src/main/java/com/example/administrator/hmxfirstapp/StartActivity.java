package com.example.administrator.hmxfirstapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2015/10/3.
 */
public class StartActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guidefragment1);
        LinearLayout mLinear = (LinearLayout) findViewById(R.id.GuideFragment01_LinearLayout);
        mLinear.setBackgroundResource(R.drawable.welcome_pic);

        new Thread() {
            public void run() {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Message msg = hand.obtainMessage();
                hand.sendMessage(msg);
            }

        }.start();
    }

    Handler hand = new Handler() {
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            if (isFristRun()) {
                Intent intent = new Intent(StartActivity.this,GuideActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(StartActivity.this,MainActivity.class);
                startActivity(intent);
            }
            finish();
        }
    };

    //是否第一次运行
    private boolean isFristRun() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("FirstTimeRecord", MODE_PRIVATE);
        boolean isFirstRun = sharedPreferences.getBoolean("IsFirstTimeRun", true);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (isFirstRun) {
            editor.putBoolean("IsFirstTimeRun", false);
            editor.commit();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {

        }
        return true;
    }
}
