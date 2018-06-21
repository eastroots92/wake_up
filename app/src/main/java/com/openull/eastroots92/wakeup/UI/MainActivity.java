package com.openull.eastroots92.wakeup.UI;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TimePicker;
import android.widget.Toast;

import com.openull.eastroots92.wakeup.R;
import com.openull.eastroots92.wakeup.Receiver.NotificationReceiver;
import com.openull.eastroots92.wakeup.databinding.ActivityMainBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Calendar calendar;

    private SharedPreferences timePreference;
    private SimpleDateFormat simpleDate;

    private AlarmManager alarmManager;

    private Intent notificationIntent;
    private PendingIntent notificationPendingIntent;

    private Boolean isDatePicked;

    private static final int REQUEST_CODE_2 = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        init();
    }

    private void init() {

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        initDate();
        initView();
        initIntent();
        initTimeSwitch();
        ButtonClickListener();
    }


    private void initView() {
        int hour = timePreference.getInt("hour", -1);
        int minute = timePreference.getInt("minute", -1);

        if( hour > -1 && minute > -1 ){
            calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minute);
            binding.textViewTime.setText(simpleDate.format(calendar.getTime()));
        }
    }

    private void initIntent() {
        initNotificationReceiver();
    }

    private void initNotificationReceiver() {
        notificationIntent = new Intent(MainActivity.this, NotificationReceiver.class);
        notificationPendingIntent = PendingIntent.getBroadcast(MainActivity.this, REQUEST_CODE_2, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    }


    private void initTimeSwitch() {
        boolean isMorningCall = timePreference.getBoolean("isMorningCall", false);

        if(isMorningCall){
            binding.switchTime.setChecked(true);
        }
    }

    private void ButtonClickListener() {
        alarmButton();
        timeSwitch();
    }

    private void timeSwitch() {
        binding.switchTime.setOnCheckedChangeListener( (buttonView, isChecked) -> {

            SharedPreferences.Editor editor = timePreference.edit();
            editor.putBoolean("isMorningCall",isChecked);
            editor.commit();

            if(isChecked){
                if(!isDatePicked){
                    int hour = timePreference.getInt("hour", -1);
                    int minute = timePreference.getInt("minute", -1);

                    if( hour > -1 && minute > -1 ) {
                        calendar = Calendar.getInstance();
                        calendar.set(Calendar.HOUR_OF_DAY, hour);
                        calendar.set(Calendar.MINUTE, minute);

                        setAlarm();
                    }
                }

            }else{
                cancelAlarm();
            }
        });
    }

    private void alarmButton() {
        binding.textViewAlarmOnButton.setOnClickListener(__->{
            int[] currentTime = getTimeNow();
            showTimePicker(currentTime);
        });
    }

    private void showTimePicker(int[] currentTime) {
        final TimePickerDialog timeDialog = new TimePickerDialog(this, (view, hourOfDay, minute) ->{

            isDatePicked = true;

            // TimePickerDialog로 입력 받은 시간 값을 설정함
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calendar.set(Calendar.MINUTE,minute);

            // sharedpreferences에 값 추가
            SharedPreferences.Editor editor = timePreference.edit();
            editor.putInt("hour", hourOfDay);
            editor.putInt("minute",minute);
            editor.putBoolean("isMorningCall", true);
            editor.commit();


            // View에 보여질 내용들 설정 (여기서 SimpleDateFormat을 이용해서 값 변경)
            binding.textViewTime.setText(simpleDate.format(calendar.getTime()));
            binding.switchTime.setChecked(true);

            setAlarm();

            isDatePicked = false;

        }, currentTime[0],currentTime[1], false);

        timeDialog.show();
    }

    private void initDate() {
        timePreference = getSharedPreferences("dateTime", MODE_PRIVATE);
        simpleDate = new SimpleDateFormat("HH:mm");
    }


    public int[] getTimeNow() {
        calendar = Calendar.getInstance();
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        int currentMinute = calendar.get(Calendar.MINUTE);
        int[] currentTime = {currentHour,currentMinute};
        return currentTime;
    }

    private void setAlarm() {
        alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis() - 120000, notificationPendingIntent);
    }

    private void cancelAlarm(){
        alarmManager.cancel(notificationPendingIntent);
        notificationPendingIntent.cancel();
    }

}
