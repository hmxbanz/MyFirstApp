package com.example.administrator.hmxfirstapp.WebView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.administrator.hmxfirstapp.MainActivity;
import com.example.administrator.hmxfirstapp.R;
import com.example.administrator.hmxfirstapp.UserInfoActivity;

/**
 * Created by Hmx on 2015/8/19.
 */
public class MyWebViewClient extends WebViewClient {
    private Activity activity;
    private ProgressBar progressBar;
    public MyWebViewClient(Activity activity) {
        this.activity = activity;
    }
    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        // TODO Auto-generated method stub
        progressBar=(ProgressBar) activity.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        super.onPageStarted(view, url, favicon);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        //����
        super.onPageFinished(view, url);
        progressBar.setVisibility(View.GONE);

    }



    @Override
    public void onReceivedError(WebView view, int errorCode,String description, String failingUrl) {
        // TODO Auto-generated method stub
        //super.onReceivedError(view, errorCode, description, failingUrl);
        //progressBar.setVisibility(android.view.View.GONE);
        view.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
        view.loadUrl("file:///android_asset/error.html");


       //String data = "<h4>找不到网络</h4>";
       //view.loadUrl("javascript:document.body.innerHTML=\"" + data + "\"");
        //view.setBackgroundResource(R.drawable.icon);
        //view.setBackgroundColor(Color.TRANSPARENT);
        //String customHtml = "<html><body><h4><font color='red'>找不到网络ʾ</font></h4></body></html>";
        //view.loadData(customHtml, "text/html", "UTF-8");
    }

    public boolean shouldOverrideUrlLoading(WebView view, String url) {

        if (Uri.parse(url).getHost().equals("wx.mlww.info"))
        {
            Intent intent = new Intent(this.activity, UserInfoActivity.class);
            intent.putExtra("Url", url);
            this.activity.startActivity(intent);
        }
        else
        {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(url));
            this.activity.startActivity(intent);
        }

        return true;
    }

}
