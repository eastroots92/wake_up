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

    public static final int CHANNEL_ID = 92;
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
//      TODO: Noti 클릭 시 메인 액티비티 띄우기!

    }

    private void initBuilder() {
//      TODO: NotificationCompat.Builder 만들기!
// 오레오부터 변경사항 있음 채널 넣어줘야 함!

    }

    private void setBuilder() {
//      TODO: builder 에 Noti 속성 마구마구 넣고 notify 실행!
    }


}
