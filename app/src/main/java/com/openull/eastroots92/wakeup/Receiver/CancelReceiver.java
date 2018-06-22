package com.openull.eastroots92.wakeup.Receiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import static com.openull.eastroots92.wakeup.UI.MainActivity.REQUEST_CODE_3;

public class CancelReceiver extends BroadcastReceiver {

    private AlarmManager alarmManager;

    @Override
    public void onReceive(Context context, Intent intent) {

        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent alarmActivityIntent = new Intent("com.openull.eastroots92.ACTION_ALARM");
        PendingIntent alarmActivityPendingIntent = PendingIntent.getActivity(context,REQUEST_CODE_3,alarmActivityIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.cancel(alarmActivityPendingIntent);
        alarmActivityPendingIntent.cancel();
    }
}
