package com.mtelnet.myview;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mtelnet.myview.utils.BadgeUtil;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        sendNotification("ConstraintLayout(约束布局), 是2016年Google I/O推出的Android布局, 目前还在完善阶段. 从推出的力度而言, 预计会成为主流布局样式. 在最新版本的Android Studio中, ConstraintLayout已替代RelativeLayout, 成为HelloWorld项目的默认布局. ConstraintLayout作为非绑定(Unbundled)的支持库, 命名空间是app:, 即来源于本地的包命名空间. ");
    }


    private void sendNotification(String message) {
        Intent intent = new Intent(this, MainActivity.class);
        Bundle bundle=new Bundle();
        bundle.putString("push_msg",message);
        intent.putExtras(bundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(getString(R.string.app_name))
                .setContentText(message)
                .setAutoCancel(true)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());

        //icon msg
        BadgeUtil.setBadgeCount(notificationBuilder.build(),NotificationActivity.this,99);
    }
}
