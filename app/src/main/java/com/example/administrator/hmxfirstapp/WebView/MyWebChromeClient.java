package com.example.administrator.hmxfirstapp.WebView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.administrator.hmxfirstapp.R;

/**
 * Created by Administrator on 2015/8/19.
 */
public class MyWebChromeClient extends WebChromeClient {

    private Activity activity;
    private ProgressBar pb;
    private TextView tvtitle;

    public MyWebChromeClient(Activity activity) {
        this.activity = activity;
    }

    Animation animation;

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        pb = (ProgressBar) activity.findViewById(R.id.progressBar);
        pb.setMax(100);
        if (newProgress < 100) {
            if (pb.getVisibility() == View.GONE)
                pb.setVisibility(View.VISIBLE);
            pb.setProgress(newProgress);
        } else {
            pb.setProgress(100);
            animation = AnimationUtils.loadAnimation(activity, R.anim.animation);
            // 运行动画 animation
            pb.startAnimation(animation);
            // 将 spinner 的可见性设置为不可见状态
            pb.setVisibility(View.INVISIBLE);
        }

        super.onProgressChanged(view, newProgress);
    }

    @Override
    public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
//handle Alert event, here we are showing AlertDialog
        new AlertDialog.Builder(activity)
                .setTitle("黄先生提醒您：")
                .setMessage(message)
                .setPositiveButton("Close", null)
                .setPositiveButton(android.R.string.ok,
                        new AlertDialog.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do your stuff
                                result.confirm();
                            }
                        }).setCancelable(false).create().show();
        return true;
    }

    /**
     * 覆盖默认的window.confirm展示界面，避免title里显示为“：来自file:////”
     */
    @Override
    public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle("对话框")
                .setMessage(message)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm();
                    }
                })
                .setNeutralButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        result.cancel();
                    }
                });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
                result.cancel();
            }
        });

        // 屏蔽keycode等于84之类的按键，避免按键后导致对话框消息而页面无法再弹出对话框的问题
        builder.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                Log.v("onJsConfirm", "keyCode==" + keyCode + "event=" + event);
                return true;
            }
        });
        // 禁止响应按back键的事件
        // builder.setCancelable(false);
        // AlertDialog dialog = builder.create();
        //dialog.show();
        return true;
        // return super.onJsConfirm(view, url, message, result);
    }

    /**
     * 覆盖默认的window.prompt展示界面，避免title里显示为“：来自file:////”
     * window.prompt('请输入您的域名地址', '618119.com');
     */
//    public boolean onJsPrompt(WebView view, String url, String message,
//                              String defaultValue, final JsPromptResult result) {
//        final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
//
//        builder.setTitle("对话框").setMessage(message);
//
//        final EditText et = new EditText(view.getContext());
//        et.setSingleLine();
//        et.setText(defaultValue);
//        builder.setView(et)
//                .setPositiveButton("确定", new OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        result.confirm(et.getText().toString());
//                    }
//
//                })
//                .setNeutralButton("取消", new OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        result.cancel();
//                    }
//                });
//
//        // 屏蔽keycode等于84之类的按键，避免按键后导致对话框消息而页面无法再弹出对话框的问题
//        builder.setOnKeyListener(new OnKeyListener() {
//            public boolean onKey(DialogInterface dialog, int keyCode,KeyEvent event) {
//                Log.v("onJsPrompt", "keyCode==" + keyCode + "event="+ event);
//                return true;
//            }
//        });
   // return true;
//}


    @Override
    public void onReceivedTitle(WebView view, String title) {
      //  tvtitle = (TextView) activity.findViewById(R.id.tvtitle);
        //tvtitle.setText(title);
        activity.setTitle(title);
        super.onReceivedTitle(view, title);
    }


}
