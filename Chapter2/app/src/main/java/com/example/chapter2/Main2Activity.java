package com.example.chapter2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        String s= getIntent().getStringExtra("capital");

        TextView tv = (TextView) findViewById(R.id.capital2);

        tv.setText(s);
    }
}
