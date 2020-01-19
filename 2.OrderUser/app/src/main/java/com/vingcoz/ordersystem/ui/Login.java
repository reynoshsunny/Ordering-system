package com.vingcoz.ordersystem.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.victor.loading.rotate.RotateLoading;
import com.vingcoz.ordersystem.R;
import com.vingcoz.ordersystem.utils.RequestHandler;
import com.vingcoz.ordersystem.utils.ToastUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import static com.vingcoz.ordersystem.utils.ApiUtils.CheckPassword;
import static com.vingcoz.ordersystem.utils.ApiUtils.MainUrl;

public class Login extends AppCompatActivity {

    TextView txtForgotPassword;
    Button btnLogin;
    EditText txtPass;
    String PhoneNumber;
    RotateLoading newtonCradleLoading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        newtonCradleLoading = findViewById(R.id.rotateloading);
        newtonCradleLoading.setLoadingColor(R.color.colorPrimary);

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
        txtPass = findViewById(R.id.edtPass);
        txtForgotPassword = findViewById(R.id.txtForgetPassword);
        btnLogin = findViewById(R.id.btnLogin);

        txtForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myLogin = new Intent(Login.this, CheckOtp.class);
                Login.this.startActivity(myLogin);

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login.this.Continue();
            }
        });
    }

    public void Continue() {
        if (Validate()) {
            CheckPassword();
        }
    }

    public Boolean Validate() {

        if (txtPass.getText().toString() == "") {
            txtPass.setError("Enter Password");
            return false;
        } else {

            return true;
        }
    }

    private void CheckPassword() {

        class CheckPHP extends AsyncTask<Void, Void, String> {

            String GetPassword = txtPass.getText().toString();
            final String[] Error = new String[1];

            SharedPreferences MainPreference;
            String LoginUser;
            String PhNo;
            String Address;
            String PinCode;
            String ReferralCode;
            String CustomerCode;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                newtonCradleLoading.start();
            }

            @Override
            protected void onPostExecute(String J_Result) {
                super.onPostExecute(J_Result);

                try {
                    JSONObject obj = new JSONObject(J_Result);
                    Error[0] = obj.getString("error");
                    if (Error[0].equalsIgnoreCase("true")) {
                        ToastUtils.ShowToast(Login.this, "Password error");
                        newtonCradleLoading.stop();

                    } else {

                        MainPreference = Login.this.getSharedPreferences("com.vingcoz.orderingsys", Context.MODE_PRIVATE);
                        LoginUser = MainPreference.getString("login_user", "empty");
                        PhNo = MainPreference.getString("login_phno", "empty");
                        Address = MainPreference.getString("login_ADDRESS", "empty");
                        PinCode = MainPreference.getString("login_PINCODE", "empty");
                        ReferralCode = MainPreference.getString("login_REFERALCD", "empty");
                        CustomerCode = MainPreference.getString("login_CUS_ID", "empty");

                        JSONArray f = obj.getJSONArray("user");

                        for (int i = 0; i < f.length(); i++) {
                            JSONObject js3 = f.getJSONObject(i);
                            ToastUtils.ShowToast(Login.this, "Welcome back " + js3.getString("NAME"));
                            LoginUser = js3.getString("NAME");
                            Address = js3.getString("ADDRESS");
                            PinCode = js3.getString("PINCODE");
                            ReferralCode = js3.getString("REFERALCD");
                            CustomerCode = js3.getString("CUS_ID");
                        }

                        SharedPreferences.Editor editor = MainPreference.edit();
                        editor.putString("login_user", LoginUser);
                        editor.putString("login_phno", PhoneNumber);
                        editor.putString("login_ADDRESS", Address);
                        editor.putString("login_PINCODE", PinCode);
                        editor.putString("login_REFERALCD", ReferralCode);
                        editor.putString("login_CUS_ID", CustomerCode);

                        editor.apply();
                        newtonCradleLoading.stop();
                        Intent myLogin = new Intent(Login.this, DashBoard.class);
                        startActivity(myLogin);
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    newtonCradleLoading.stop();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {

                RequestHandler requestHandler = new RequestHandler();
                HashMap<String, String> params = new HashMap<>();
                params.put("PHONE_NO", PhoneNumber);
                params.put("PASSWORD", GetPassword);
                return requestHandler.sendPostRequest(MainUrl + CheckPassword, params);
            }
        }
        CheckPHP ul = new CheckPHP();
        ul.execute();
    }
}


