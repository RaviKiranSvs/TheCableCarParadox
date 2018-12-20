package com.unothodox.entertainment.thecablecarparadox;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    Resources resources;
    TextView tv_level, tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resources = getResources();
        tv_level = findViewById(R.id.tv_level);
        tv_result = findViewById(R.id.tv_result);

        String[] titleAt = resources.getStringArray(R.array.titles);
        StringBuilder titleText = new StringBuilder();
        for(String s : titleAt) {
            titleText.append(s).append(": ").append("\n");
        }
        tv_level.setText(titleText);

        boolean[] action = getIntent().getBooleanArrayExtra("action");
        StringBuilder resultText = new StringBuilder();
        for (boolean b : action) {
            if(b)   resultText.append("acted\n");
            else    resultText.append("stayed still\n");
        }
        tv_result.setText(resultText);
    }

    public void restart(View v) {
        this.finish();
    }

    public void goToBlog(View v)    {
        Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse(resources.getString(R.string.url_link)));
        startActivity(browser);
    }
}
