package com.meng.mengtestapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.meng.mengtestapplication.R;
import com.meng.mengtestapplication.view.CountDownView;

public class TestCountDownViewActivity extends AppCompatActivity {
    private CountDownView countDownView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_count_down_view);
        countDownView = findViewById(R.id.countdownView);
        //开始倒计时
        countDownView.startCountDown();
        //倒计时之后的操作
        countDownView.setAddCountDownListener(new CountDownView.OnCountDownFinishListener() {
            @Override
            public void countDownFinished() {
                Toast.makeText(TestCountDownViewActivity.this, "倒计时结束", Toast.LENGTH_LONG).show();
            }
        });
    }
}
