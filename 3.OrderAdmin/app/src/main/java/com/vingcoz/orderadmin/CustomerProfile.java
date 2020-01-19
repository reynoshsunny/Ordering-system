package com.vingcoz.orderadmin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import static com.vingcoz.orderadmin.ApiUtils.CheckAdminView;
import static com.vingcoz.orderadmin.ApiUtils.MainUrl;


public class CustomerProfile extends AppCompatActivity {


    Button btnNext;
    EditText txtID_No;
    String PhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                PhoneNumber = null;
            } else {
                PhoneNumber = extras.getString("PhoneNumber");
            }
        } else {
            PhoneNumber = (String) savedInstanceState.getSerializable("PhoneNumber");
        }
        txtID_No = findViewById(R.id.txtID_No);
        btnNext = findViewById(R.id.btnNext);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomerProfile.this.Continue();
            }
        });
    }

    public void Continue() {
        if (Validate()) {
            CheckPassword();
        }
    }

    public Boolean Validate() {

        if (txtID_No.getText().toString() == "") {
            txtID_No.setError("Enter Password");
            return false;
        } else {

            return true;
        }
    }

    private void CheckPassword() {

        class CheckPHP extends AsyncTask<Void, Void, String> {

            String GetMail = txtID_No.getText().toString();
            final String[] Error = new String[1];

            SharedPreferences MainPreference;
            String LoginUser;
            String PhNo;
            String Address;
            String PinCode;
            String ReferralCode;
            String CustomerCode;
            String Place;
            String Mail;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String J_Result) {
                super.onPostExecute(J_Result);

                try {
                    JSONObject obj = new JSONObject(J_Result);
                    Error[0] = obj.getString("error");
                    if (Error[0].equalsIgnoreCase("true")) {
                        ToastUtils.ShowToast(CustomerProfile.this, "Password error");

                    } else {

                        MainPreference = CustomerProfile.this.getSharedPreferences("com.vingcoz.orderingsys", Context.MODE_PRIVATE);
                        LoginUser = MainPreference.getString("login_user", "empty");
                        Place = MainPreference.getString("login_place", "empty");
                        PhNo = MainPreference.getString("login_phno", "empty");
                        Address = MainPreference.getString("login_ADDRESS", "empty");
                        PinCode = MainPreference.getString("login_PINCODE", "empty");
                        Mail = MainPreference.getString("login_Mail", "empty");
                        ReferralCode = MainPreference.getString("login_REFERALCD", "empty");
                        CustomerCode = MainPreference.getString("login_CUS_ID", "empty");


                        JSONArray f = obj.getJSONArray("user");

                        for (int i = 0; i < f.length(); i++) {
                            JSONObject js3 = f.getJSONObject(i);
                            ToastUtils.ShowToast(CustomerProfile.this, "Welcome back " + js3.getString("NAME"));
                            LoginUser = js3.getString("NAME");
                            Address = js3.getString("ADDRESS");
                            Place = js3.getString("PLACE");
                            PinCode = js3.getString("PINCODE");
                            Mail = js3.getString("MAIL");
                            ReferralCode = js3.getString("REFERALCD");
                            CustomerCode = js3.getString("CUS_ID");
                        }

                        SharedPreferences.Editor editor = MainPreference.edit();
                        editor.putString("login_user", LoginUser);
                        editor.putString("login_ADDRESS", Address);
                        editor.putString("login_phno", PhoneNumber);
                        editor.putString("login_place", Place);
                        editor.putString("login_PINCODE", PinCode);
                        editor.putString("Login_Mail", Mail);
                        editor.putString("login_REFERALCD", ReferralCode);
                        editor.putString("login_CUS_ID", CustomerCode);

                        editor.apply();
                        Intent myLogin = new Intent(CustomerProfile.this, CustomerList.class);
                        startActivity(myLogin);
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {

                RequestHandler requestHandler = new RequestHandler();
                HashMap<String, String> params = new HashMap<>();
                params.put("NAME", LoginUser);
                params.put("ADDRESS", Address);
                params.put("PLACE", Place);
                params.put("PINCODE", PinCode);
                params.put("PHONE_NO", PhoneNumber);
                params.put("EMAIL_ID", GetMail);
                params.put("X_CORD", "TEST X");
                params.put("Y_CORD", "TEST Y");
                params.put("JOINDT", "JoinDate");
                params.put("REFERALCD", ReferralCode);
                return requestHandler.sendPostRequest(MainUrl + CheckAdminView, params);
            }
        }
        CheckPHP ul = new CheckPHP();
        ul.execute();
    }
}


