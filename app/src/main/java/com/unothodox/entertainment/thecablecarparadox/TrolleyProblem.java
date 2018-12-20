package com.unothodox.entertainment.thecablecarparadox;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class TrolleyProblem extends AppCompatActivity {

    private int level = 0;
    private int levelCount = 5;
    ImageView iv_problem;
    TextView tv_title, tv_desc;
    ProgressBar pb_time, pb_level;
    Button b_action;

    TypedArray imageAt;
    String[] titleAt, descAt, actionAt;
    boolean[] action;

    int timePerLevel = 120;  //unit = second
    int timePerPercent = timePerLevel*10;   //unit = milli second
    Handler barInc = new Handler();
    Runnable bar = new Runnable() {
        @Override
        public void run() {
            if(pb_time.getProgress() == 100)    {
                nextProblem(tv_title);
            }else   {
                pb_time.incrementProgressBy(1);
                barInc.postDelayed(this, timePerPercent);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trolley_problem);

        iv_problem = findViewById(R.id.iv_problem);
        tv_title = findViewById(R.id.tv_title);
        tv_desc = findViewById(R.id.tv_desc);
        pb_time = findViewById(R.id.pb_time);
        pb_level = findViewById(R.id.pb_level);
        b_action = findViewById(R.id.b_action);

        Resources resources = getResources();
        imageAt = resources.obtainTypedArray(R.array.images);
        titleAt = resources.getStringArray(R.array.titles);
        descAt = resources.getStringArray(R.array.descriptions);
        actionAt = resources.getStringArray(R.array.actions);
        action = new boolean[levelCount];

        display(level);
        barInc.postDelayed(bar, timePerPercent);
    }

    public void display(int l)  {
        iv_problem.setImageResource(imageAt.getResourceId(l, 0));
        tv_title.setText(titleAt[l]);
        tv_desc.setText(descAt[l]);
        b_action.setText(actionAt[l]);
        pb_time.setProgress(0);
        pb_level.setProgress(l*100/levelCount);
        barInc.postDelayed(bar, timePerPercent);
    }

    @SuppressLint("RestrictedApi")
    public void nextProblem(View v) {
        action[level] = v.getTag().equals("Yes");
        level++;
        if(level < levelCount)
            display(level);
        else if(level == levelCount) {
            barInc.removeCallbacks(bar);
            Intent i = new Intent(this, Result.class);
            i.putExtra("action", action);
            startActivityForResult(i, 77);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 77)   {
            if(resultCode == RESULT_CANCELED)   {
                level = 0;
                display(level);
                barInc.postDelayed(bar, timePerPercent);
            }
        }
    }
}
