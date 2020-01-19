package com.vingcoz.orderadmin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends Activity {

    private static int SPLASH_TIME_OUT = 3000;
    SharedPreferences MainPreference;
    String LoginUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        MainPreference = SplashScreen.this.getSharedPreferences("com.vingcoz.orderadmin", Context.MODE_PRIVATE);
        LoginUser = MainPreference.getString("login_user", "empty");


        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {


                if(LoginUser.equalsIgnoreCase("empty")){
                    Intent i = new Intent(SplashScreen.this, AdminLoginMobile.class);
                    startActivity(i);
                }else{
                    Intent i = new Intent(SplashScreen.this, DashBoard.class);
                    startActivity(i);
                }
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}

