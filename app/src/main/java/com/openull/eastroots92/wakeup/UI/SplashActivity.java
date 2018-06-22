package com.openull.eastroots92.wakeup.UI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.openull.eastroots92.wakeup.R;
import com.openull.eastroots92.wakeup.UI.AlarmActivity;
import com.openull.eastroots92.wakeup.UI.IntroActivity;
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
                Boolean isCurrent = checkingUserData();
                changeActivityManager(isCurrent);
            }
        };

        handler.postDelayed(
                startActivityRunnable,
                ACTIVITY_START_DELAY_MILLIS
        );
    }

    private Boolean checkingUserData() {
        // TODO: SharedPreference 값 가져와야 함!


        if ( true ){
            return true;
        }else{
            return false;
        }
    }

    private void changeActivityManager(boolean isCurrent) {
        if(isCurrent) {
            startIntroActivity();

        }else {
            startMainActivity();
        }
    }

    private void startIntroActivity() {
        startActivity(new Intent(this, IntroActivity.class));
        finish();
    }

    private void startMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
