package com.vingcoz.ordersystem.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.vingcoz.ordersystem.R;
import com.vingcoz.ordersystem.utils.DBHelper;
import com.vingcoz.ordersystem.utils.ToastUtils;



public class DashBoard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView UserName;
    TextView PhoneNumber;

    DBHelper DbHelper;
    SharedPreferences MainPreference;
    String LoginUser;
    String strPhoneNumber;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DbHelper = new DBHelper(this);
        MainPreference = DashBoard.this.getSharedPreferences("com.vingcoz.orderingsys", Context.MODE_PRIVATE);
        LoginUser = MainPreference.getString("login_user", "empty");
        strPhoneNumber = MainPreference.getString("login_phno", "empty");


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashBoard.this, AddOrder.class);
                startActivity(i);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        TextView UserName;
        TextView PhoneNumber;

        View header = navigationView.getHeaderView(0);
        UserName = header.findViewById(R.id.txtUsernm);
        PhoneNumber = header.findViewById(R.id.txtPhNo);
        UserName.setText(LoginUser);
        PhoneNumber.setText(strPhoneNumber);

//
//        String strCompanyName = DbHelper.GetCharParams("Company");
//        PhoneNumber.setText(strCompanyName + " - " + LoginUser);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dash_board, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_addOrder) {

            Intent i = new Intent(DashBoard.this, AddOrder.class);
            startActivity(i);

        } else if (id == R.id.nav_OrdReport) {

            Intent i = new Intent(DashBoard.this, OrderReport.class);
            startActivity(i);

        } else if (id == R.id.nav_profile) {

            Intent i = new Intent(DashBoard.this, Profile.class);
            startActivity(i);

        } else if (id == R.id.nav_ChangeAddress) {

            Intent i = new Intent(DashBoard.this, ChangeAddress.class);
            startActivity(i);

        } else if (id == R.id.nav_changePass) {

            Intent i = new Intent(DashBoard.this, ChangePassword.class);
            startActivity(i);

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_logout) {

            SharedPreferences.Editor editor = MainPreference.edit();
            editor.putString("login_user", "empty");
            editor.putString("login_phno", "empty");
            editor.putString("login_ADDRESS", "empty");
            editor.putString("login_PINCODE", "empty");
            editor.putString("login_REFERALCD", "empty");
            editor.putString("login_CUS_ID", "empty");
            editor.apply();
            ToastUtils.ShowToast(DashBoard.this, "Please login again to continue");
            Intent i = new Intent(DashBoard.this, LoginMobile.class);
            startActivity(i);
            finish();
        } else if (id == R.id.nav_share) {


        } else if (id == R.id.nav_about) {

            Intent i = new Intent(DashBoard.this, About.class);
            startActivity(i);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}