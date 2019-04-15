package com.meng.mengtestapplication.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.meng.mengtestapplication.R;
import com.meng.mengtestapplication.view.RoundProgressBar;

public class TestRoundProgressActivity extends AppCompatActivity {
    private RoundProgressBar mRoundProgressBar1, mRoundProgressBar2, mRoundProgressBar3, mRoundProgressBar4, mRoundProgressBar5;
    private int progress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_round_progress);
        mRoundProgressBar1 = (RoundProgressBar) findViewById(R.id.roundProgress1);
        mRoundProgressBar2 = (RoundProgressBar) findViewById(R.id.roundProgress2);
        mRoundProgressBar3 = (RoundProgressBar) findViewById(R.id.roundProgress3);
        mRoundProgressBar4 = (RoundProgressBar) findViewById(R.id.roundProgress4);
        mRoundProgressBar5 = (RoundProgressBar) findViewById(R.id.roundProgress5);

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (progress <= 100) {
                            progress += 3;

                            mRoundProgressBar1.setProgress(progress);
                            mRoundProgressBar2.setProgress(progress);
                            mRoundProgressBar3.setProgress(progress);
                            mRoundProgressBar4.setProgress(progress);
                            mRoundProgressBar5.setProgress(progress);

                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        });
    }
}
