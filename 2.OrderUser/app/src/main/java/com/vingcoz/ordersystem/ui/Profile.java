package com.vingcoz.ordersystem.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.vingcoz.ordersystem.R;

public class Profile extends AppCompatActivity {

    Button btnChangeAddress;
    Button btnChangePassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        setTitle("Profile");

        btnChangeAddress = findViewById(R.id.btnChangeAddress);
        btnChangePassword = findViewById(R.id.btnChangePassword);

        btnChangeAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Profile.this.Address();

            }
        });

        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Profile.this.Password();

            }
        });

    }

    public void Address() {
        Intent myAddress = new Intent(this, ChangeAddress.class);
        startActivity(myAddress);
    }

    public void Password() {
        Intent myPassword = new Intent(this, ChangePassword.class);
        startActivity(myPassword);
    }
}
