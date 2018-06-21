package com.openull.eastroots92.wakeup.Receiver;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.openull.eastroots92.wakeup.R;
import com.openull.eastroots92.wakeup.UI.MainActivity;

public class NotificationReceiver extends BroadcastReceiver {

    private NotificationManager notificationManager;
    private NotificationCompat.Builder builder;

    private Context context;
    private Intent intent;

    private Intent touchActionIntent;
    private PendingIntent touchActionPendingIntent;

    private static final int CHANNEL_ID = 92;
    private static final int REQUEST_CODE_1 = 10;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        this.intent =intent;

        
        init();
    }

    private void init() {
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        initIntent();
        initBuilder();
        setBuilder();
    }

    private void initIntent() {
        touchActionIntent = new Intent(context, MainActivity.class);
        touchActionPendingIntent = PendingIntent.getActivity(context,REQUEST_CODE_1,intent, PendingIntent.FLAG_UPDATE_CURRENT);

    }

    private void initBuilder() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            String channelId = "noti";
            String channelName = "MorningCall";
            String channelDescription = "이거 끄면 모닝콜 아무소용 없어요";

            NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(channelDescription);

            // 선택사항
            channel.enableLights(true);
            channel.enableVibration(true);

            // 어짜피 필요한데 오레오부터는 channel이 무조건 필요함
            notificationManager.createNotificationChannel(channel);

            builder = new NotificationCompat.Builder(context, channelId);
        }else{
            builder = new NotificationCompat.Builder(context);
        }
    }

    private void setBuilder() {
        builder.setSmallIcon(R.drawable.icon_alarm)
                .setWhen(System.currentTimeMillis())
                .setContentTitle("잠시후에 알람이 울립니다.")
                .setContentText("두구두구두구두구두구두구")
                .setAutoCancel(true)
                .setOngoing(false)
                .setContentIntent(touchActionPendingIntent);

        notificationManager.notify(CHANNEL_ID, builder.build());
    }


}
