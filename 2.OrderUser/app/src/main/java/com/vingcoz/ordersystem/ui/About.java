package com.vingcoz.ordersystem.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.vingcoz.ordersystem.R;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        setTitle("About");
    }
}
