package com.openull.eastroots92.wakeup.UI;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.RelativeLayout;

import com.openull.eastroots92.wakeup.R;
import com.openull.eastroots92.wakeup.databinding.ActivityIntroBinding;

public class IntroActivity extends AppCompatActivity {

    private ActivityIntroBinding binding;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_intro);
        
        init();
    }

    private void init() {
        showIntroManager();
        inputNameManager();
    }

    private void showIntroManager() {
        initIntro();
        startIntro(binding.relativeIntro1, 1000);
        startIntro(binding.relativeIntro2, 2000);
    }

    private void initIntro() {
        binding.relativeIntro1.setVisibility(View.GONE);
        binding.relativeIntro2.setVisibility(View.GONE);
    }

    private void startIntro(RelativeLayout view, int delayTime) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setVisibility(View.VISIBLE);
            }
        },delayTime);
    }

    private void inputNameManager() {
        editTextChecker();
    }

    private void editTextChecker() {
        binding.introNameFormEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                inputNameChecker(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

            private void inputNameChecker(CharSequence s) {
                if(s.length() != 0){
                    int color = Color.rgb(28,28,28);
                    binding.viewNameFormUnderBar.setBackgroundColor(color);
                    binding.textViewSubmit.setBackgroundColor(color);

                    buttonClickListener();
                }else{
                    int color = Color.rgb(184,184,184);
                    binding.viewNameFormUnderBar.setBackgroundColor(color);
                    binding.textViewSubmit.setBackgroundColor(color);
                }

            }

        });
    }

    private void buttonClickListener() {
        binding.textViewSubmit.setOnClickListener(__->{
            String userName = binding.introNameFormEditText.getText().toString();
            setUserData(userName);
            startMainActivity();
        });
    }

    public void setUserData(String userName) {
        SharedPreferences sharedPreferences = getSharedPreferences("userData", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name",userName);
        editor.commit();
    }

    private void startMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
