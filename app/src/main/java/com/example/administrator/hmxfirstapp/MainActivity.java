package com.example.administrator.hmxfirstapp;



import com.example.administrator.hmxfirstapp.Function.CustomToast;
import com.example.administrator.hmxfirstapp.Menu.HomeActivity;
import com.example.administrator.hmxfirstapp.WebView.MyWebChromeClient;
import com.example.administrator.hmxfirstapp.WebView.MyWebViewClient;
import com.umeng.update.UmengUpdateAgent;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

//@Override
//    public void onProgressChanged(WebView view, int progress) {
//        //mContext.getApplicationContext().setTitle("网页加载中...");
//        //  mContext.getApplicationContext().setProgress(progress * 100);
//        // if (progress == 100)
//        //  mContext.getApplicationContext().setTitle(R.string.app_name);
//
//        super.onProgressChanged(view, progress);
//    }


/////////////////////////////////////////////////////////////////////MainActivity


public class MainActivity extends Activity {
    private WebView myWebView = null;
    private final static int menuService=1002;
    // private ProgressBar progressBar = null;
    private Button btn = null;

    private EditText WebSiteAddr = null;
//请隐藏menu一直显示，不管手机有没有物理menu按键
    private void setOverflowShowingAlways() {
        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            menuKeyField.setAccessible(true);
            menuKeyField.setBoolean(config, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取设备内容
     */
    public static String getDeviceInfo(Context context) {
        try{
            org.json.JSONObject json = new org.json.JSONObject();
            android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);

            String device_id = tm.getDeviceId();

            android.net.wifi.WifiManager wifi = (android.net.wifi.WifiManager) context.getSystemService(Context.WIFI_SERVICE);

            String mac = wifi.getConnectionInfo().getMacAddress();
            json.put("mac", mac);

            if( TextUtils.isEmpty(device_id) ){
                device_id = mac;
            }

            if( TextUtils.isEmpty(device_id) ){
                device_id = android.provider.Settings.Secure.getString(context.getContentResolver(),android.provider.Settings.Secure.ANDROID_ID);
            }

            json.put("device_id", device_id);

            return json.toString();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // requestWindowFeature(Window.FEATURE_NO_TITLE);
        //UpdateConfig.setDebug(true);
        UmengUpdateAgent.update(this);
        //让隐藏menu一直显示，不管手机有没有物理menu按键
        setOverflowShowingAlways();

        myWebView = (WebView) findViewById(R.id.webView);
        btn = (Button) findViewById(R.id.btn);
        WebSiteAddr = (EditText) findViewById(R.id.WebSiteAddr);

        //监听回车键
        WebSiteAddr.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Toast.makeText(MainActivity.this, String.valueOf(actionId), Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        btn.setOnClickListener(new Button.OnClickListener() {
            // 实现监听器接口的匿名内部类,其中监听器本身是View类的内部接口
            // 实现接口必须实现的onClick方法
            public void onClick(View v) {

                myWebView.loadUrl(WebSiteAddr.getText().toString());
                Toast.makeText(getApplicationContext(), WebSiteAddr.getText().toString(), Toast.LENGTH_LONG).show();
            }
        });
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.getSettings().setSupportZoom(true);
        myWebView.setWebChromeClient(new MyWebChromeClient(MainActivity.this));
        myWebView.setWebViewClient(new MyWebViewClient(MainActivity.this));
        myWebView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return false;
            }
        });


        myWebView.loadUrl("http://wx.mlww.info/");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack()) {
            // 返回键退回
            myWebView.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up
        // to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }
///菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater menuInflate = getMenuInflater();
        menuInflate.inflate(R.menu.menu_main, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                Toast.makeText(MainActivity.this, "展开", Toast.LENGTH_LONG);
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                Toast.makeText(MainActivity.this, "收缩", Toast.LENGTH_LONG);
                return true;
            }
        });
        MenuItem menuItem=menu.add(1,menuService, 1, "服务");
        menuItem.setIcon(R.drawable.ic_home_black_24dp);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.MenuItemExample:
                Intent IntentToExampleActivity= new Intent(MainActivity.this, ExampleActivity.class);
                item.setIntent(IntentToExampleActivity);
                break;
            case R.id.CustomToastMenuItem:
                Intent CustomToastIntent = new Intent(MainActivity.this, CustomToast.class);
                item.setIntent(CustomToastIntent);
                 break;
            case R.id.menu_add:
                Intent intent = new Intent(MainActivity.this, HelloEditText.class);
                startActivity(intent);
                break;
            case R.id.menu_wallaper:
                Intent intent2 = new Intent(MainActivity.this, IntentPassValue.class);
                startActivity(intent2);
                break;
            case R.id.menu_search:
                break;
            case R.id.menu_setting:
                showSettings();
                break;
            case R.id.dialog:
                break;
            case R.id.btn_Notification:
                Toast.makeText(MainActivity.this,"成功",Toast.LENGTH_LONG);
                Intent intent3 = new Intent(MainActivity.this, Notification.class);
                startActivity(intent3);
                break;
            case menuService:
                Intent intent4 = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent4);
                break;


        }


        return super.onOptionsItemSelected(item);
    }


 //////////////////////////////////////////   弹出对话框




    //重写这一方法让overflow中的菜单图标显示出来
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

    //用菜单显示系统设置
    private void showSettings() {

        final Intent settings = new Intent(android.provider.Settings.ACTION_SETTINGS);
        settings.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);

        startActivity(settings);
    }

}
