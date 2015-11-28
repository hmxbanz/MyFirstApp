package com.example.administrator.hmxfirstapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.io.PrintStream;

/**
 * Created by Administrator on 2015/10/3.
 */
public class UserInfoActivity extends Activity
{
    public WebView myWebView;

    protected void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        setContentView(R.layout.userinfolayout);
        this.myWebView = ((WebView)findViewById(R.id.UserInfoWebView));
        String str = getIntent().getStringExtra("Url");
        this.myWebView.getSettings().setJavaScriptEnabled(true);
        this.myWebView.getSettings().setSupportZoom(true);
        System.out.print("dddddddddddddddddd");
        this.myWebView.loadUrl(str);
    }
}