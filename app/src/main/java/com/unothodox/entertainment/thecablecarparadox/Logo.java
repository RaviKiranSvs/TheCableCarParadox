package com.unothodox.entertainment.thecablecarparadox;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Logo extends AppCompatActivity {

    ImageView iv_uox;
    TextView tv_presents, tv_title;

    Resources res;
    char[] uox;
    char[] title;

    Handler handler = new Handler();
    int t_uox = 2000;   //units = milli seconds
    int alpha_uox = 0;
    Runnable uox_alpha = new Runnable() {
        @Override
        public void run() {
            if(alpha_uox <= 255) {
                iv_uox.setImageAlpha(alpha_uox++);
                handler.postDelayed(this, t_uox / 255);
            }
            else
                handler.postDelayed(type_uox, t_type_uox);
        }
    };

    int t_type_uox = 100;    //units = milli seconds
    int size_uox = 0;
    Runnable type_uox = new Runnable() {
        @Override
        public void run() {
            if(size_uox <= uox.length) {
                tv_presents.setText(uox, 0, size_uox++);
                handler.postDelayed(this, t_type_uox);
            }
            else
                handler.postDelayed(type_title, t_type_title);
        }
    };

    int t_type_title = 100;
    int size_title = 0;
    Runnable type_title = new Runnable() {
        @Override
        public void run() {
            if(size_title <= title.length) {
                tv_title.setText(title, 0, size_title++);
                handler.postDelayed(this, t_type_title);
            }
            else    {
                Intent i = new Intent(Logo.this, TrolleyProblem.class);
                //finish();
                startActivity(i, ActivityOptions.makeSceneTransitionAnimation(Logo.this)
                        .toBundle());
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);

        res = getResources();
        uox = res.getString(R.string.uox).toCharArray();
        title = res.getString(R.string.app_name).toCharArray();

        iv_uox = findViewById(R.id.iv_uox);
        tv_presents = findViewById(R.id.tv_presents);
        tv_title = findViewById(R.id.tv_title);

        iv_uox.setImageAlpha(alpha_uox);
        handler.postDelayed(uox_alpha, t_uox / 255);
    }
}
