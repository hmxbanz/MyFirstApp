package com.example.administrator.hmxfirstapp;

/**
 * Created by Administrator on 2015/8/3.
 */
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;

public class StatusBarService extends IntentService {

    private final static String TAG = "MainActivity";

    public StatusBarService() {
        super("StatusBarService");
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG, "开始下载");
        showNotification(false);
        try {
            Thread.sleep(10000);
            showNotification(true);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Log.i(TAG, "下载完成");
    }

    private void showNotification(boolean finish) {
        Notification notification = new Notification();
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent conIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        if (!finish) {
            notification.icon = R.drawable.icon;
            notification.tickerText = "开始下载";
            notification.setLatestEventInfo(this, "下载", "正在下载中……", conIntent);
        } else {
            notification.icon = R.drawable.icon;
            notification.tickerText = "下载完成";
            notification.setLatestEventInfo(this, "下载", "程序下载完毕", conIntent);
        }

        notification.defaults = Notification.DEFAULT_SOUND;// 添加声音提示
        // 下边的两个方式可以添加音乐
        // notification.sound =
        // Uri.parse("file:///sdcard/notification/ringer.mp3");
        // notification.sound =
        // Uri.withAppendedPath(Audio.Media.INTERNAL_CONTENT_URI, "6");
        // audioStreamType的值必须AudioManager中的值，代表着响铃的模式
        notification.audioStreamType = android.media.AudioManager.ADJUST_LOWER;
        manager.notify(R.layout.helloedittext, notification);
    }

}