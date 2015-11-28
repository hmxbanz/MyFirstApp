package com.example.administrator.hmxfirstapp;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class Notification extends Activity {
    private final static String TAG = "MainActivity";
    private Button btnStart = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        btnStart = (Button) this.findViewById(R.id.btnStart);



        btnStart.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"你成功跳转",Toast.LENGTH_LONG).show();
               //Intent intent = new Intent(Notification.this, StatusBarService.class);
               // startService(intent);//开始下载服务

                //简单的通知
                NotificationCompat.Builder builer=new NotificationCompat.Builder(Notification.this);
                builer.setSmallIcon(R.drawable.icon);
                builer.setContentTitle("我的通知");
                builer.setContentText("通知来了");
               Intent intent=new Intent(Notification.this,HelloEditText.class);
                TaskStackBuilder stackBuilder=TaskStackBuilder.create(Notification.this);
                stackBuilder.addParentStack(HelloEditText.class);
                stackBuilder.addNextIntent(intent);
                PendingIntent pendingIntent=stackBuilder.getPendingIntent(0,PendingIntent.FLAG_CANCEL_CURRENT  );
               builer.setContentIntent(pendingIntent);
                NotificationManager NM= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                NM.notify(0,builer.build());
            }
        });

    }

    /* (non-Javadoc)
 * @see android.app.Activity#onStart()
 */
    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        NotificationManager manager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        manager.cancel(R.layout.helloedittext);//此处ID取页面的，进入页面后取消提示
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_notification, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
