package com.openull.eastroots92.wakeup;

import android.app.TimePickerDialog;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TimePicker;

import com.openull.eastroots92.wakeup.databinding.ActivityMainBinding;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        init();
    }

    private void init() {
        initDate();
        ButtonClickListener();
    }

    private void ButtonClickListener() {
        alarmButton();
    }

    private void alarmButton() {
        binding.textViewAlarmButton.setOnClickListener(__->{
            int[] currentTime = getTimeNow();
            showTimePicker(currentTime);
        });
    }

    private void showTimePicker(int[] currentTime) {
        final TimePickerDialog timeDialog = new TimePickerDialog(this, (view, hourOfDay, minute) ->{

        }, currentTime[0],currentTime[1], false);

        timeDialog.show();
    }


    private void initDate() {
        calendar = Calendar.getInstance();
    }


    public int[] getTimeNow() {
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        int currentMinute = calendar.get(Calendar.MINUTE);
        int[] currentTime = {currentHour,currentMinute};
        return currentTime;
    }
}
