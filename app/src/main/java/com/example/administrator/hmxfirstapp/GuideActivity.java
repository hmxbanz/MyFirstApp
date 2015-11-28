package com.example.administrator.hmxfirstapp;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;

import com.example.administrator.hmxfirstapp.Adapter.GuideAdapter;

/**
 * Created by Administrator on 2015/10/3.
 */
public class GuideActivity extends FragmentActivity {
    private ViewPager mViewPager;
    private void initView()
    {
        this.mViewPager = ((ViewPager)findViewById(R.id.MyViewPager));
        GuideAdapter GuideAdapter = new GuideAdapter(getSupportFragmentManager(), this);
        this.mViewPager.setAdapter(GuideAdapter);
    }

    protected void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        setContentView(R.layout.welcome);
        initView();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {}
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onStop() {
        finish();
        super.onStop();
    }

}
