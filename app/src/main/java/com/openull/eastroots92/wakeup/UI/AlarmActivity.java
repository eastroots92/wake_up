package com.openull.eastroots92.wakeup.UI;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.openull.eastroots92.wakeup.R;
import com.openull.eastroots92.wakeup.databinding.ActivityAlarmBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AlarmActivity extends AppCompatActivity {

    private ActivityAlarmBinding binding;
    private MediaPlayer alarmBell;

    private SharedPreferences timePreference;
    private SharedPreferences userPreference;
    private SimpleDateFormat simpleDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_alarm);
        
        init();
    }

    private void init() {
        initPreference();
        initView();
        initAlarm();
        ButtonClickListener();
    }

    private void ButtonClickListener() {
        alarmButton();
    }

    private void alarmButton() {
        binding.textViewAlarmOffButton.setOnClickListener(__->{
            alarmBell.stop();
        });
    }

    private void initAlarm() {
        Uri alarm = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        alarmBell = MediaPlayer.create(getApplicationContext(), alarm);

        alarmBell.start();
        alarmBell.setLooping(true);
        alarmBell.setVolume(1,1);
    }

    private void initView() {
        simpleDate = new SimpleDateFormat("HH:mm");

        setNameView();
        setTimeView();
    }

    private void setTimeView() {
        Calendar calendar = Calendar.getInstance();
        int hour = timePreference.getInt("hour", -1);
        int minute = timePreference.getInt("minute", -1);

        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);

        if( hour > -1 && minute > -1 ) {
            binding.textViewTime.setText(simpleDate.format(calendar.getTime()));
        }
    }

    private void setNameView() {
        String name = userPreference.getString("name", "");

        if( name != "" ){
            binding.textViewUserName.setText(name);
        }
    }

    private void initPreference() {
        timePreference = getSharedPreferences("dateTime", Context.MODE_PRIVATE);
        userPreference = getSharedPreferences("userData", Context.MODE_PRIVATE);
    }
}