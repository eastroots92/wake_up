package com.openull.eastroots92.wakeup;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.openull.eastroots92.wakeup.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {

    private ActivitySplashBinding binding;

    private Handler handler = new Handler();
    private static final int ACTIVITY_START_DELAY_MILLIS = 1000 * 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);

        init();
    }

    private void init() {
        startMainActivityWithDelay();
    }

    private void startMainActivityWithDelay() {
        Runnable startActivityRunnable = new Runnable() {
            @Override
            public void run() {
                startMainActivity();
            }
        };

        handler.postDelayed(
                startActivityRunnable,
                ACTIVITY_START_DELAY_MILLIS
        );
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

        finish();
    }
}
