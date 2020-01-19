package com.vingcoz.orderadmin;

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

public class DashBoard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    String LoginUser;
    String strPhoneNumber;
    SharedPreferences MainPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MainPreference = DashBoard.this.getSharedPreferences("com.vingcoz.orderadmin", Context.MODE_PRIVATE);
        LoginUser = MainPreference.getString("login_user", "empty");
        strPhoneNumber = MainPreference.getString("login_phno", "empty");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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

        if (id == R.id.nav_TOrders) {
            // Handle the camera action
        } else if (id == R.id.nav_RPendingOrders) {

        } else if (id == R.id.nav_RConfirmedOrders) {

        } else if (id == R.id.nav_RRejectedOrders) {

        } else if (id == R.id.nav_RDeliveredOrders) {

        } else if (id == R.id.nav_CusMast) {

            Intent i = new Intent(DashBoard.this, CustomerList.class);
            startActivity(i);

        } else if (id == R.id.nav_PinCode) {
            Intent i = new Intent(DashBoard.this, PinCodeList.class);
            startActivity(i);

        }else if (id == R.id.nav_PassChange) {

            Intent i = new Intent(DashBoard.this, ChangePassword.class);
            startActivity(i);

        } else if (id == R.id.nav_about) {

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
