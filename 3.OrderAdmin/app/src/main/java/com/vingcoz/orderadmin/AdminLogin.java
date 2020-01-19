package com.vingcoz.orderadmin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import static com.vingcoz.orderadmin.ApiUtils.CheckLogin;
import static com.vingcoz.orderadmin.ApiUtils.MainUrl;

public class AdminLogin extends AppCompatActivity {

    TextView txtForgotPassword;
    Button btnLogin;
    EditText txtPass;
    String PhoneNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);


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


        btnLogin.setOnClickListener(v -> {
            Continue();
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
            String LoginAdmin;
            String AdminPhNo;
            String AdminAddress;
            String AdminCode;

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
                        ToastUtils.ShowToast(AdminLogin.this, "Password error");
                    } else {

                        MainPreference = AdminLogin.this.getSharedPreferences("com.vingcoz.orderadmin", Context.MODE_PRIVATE);
                        LoginAdmin = MainPreference.getString("login_user", "empty");
                        AdminPhNo = MainPreference.getString("login_phno", "empty");
                        AdminAddress = MainPreference.getString("login_ADDRESS", "empty");
                        AdminCode = MainPreference.getString("login_AD_ID", "empty");

                        JSONArray f = obj.getJSONArray("user");

                        for (int i = 0; i < f.length(); i++) {
                            JSONObject js3 = f.getJSONObject(i);
                            ToastUtils.ShowToast(AdminLogin.this, "Welcome back " + js3.getString("ADMIN_NAME"));
                            LoginAdmin = js3.getString("ADMIN_NAME");
                            AdminAddress = js3.getString("ADMIN_ADDRESS");
                            AdminCode = js3.getString("ADMIN_ID");
                        }

                        SharedPreferences.Editor editor = MainPreference.edit();
                        editor.putString("login_user", LoginAdmin);
                        editor.putString("login_phno", PhoneNumber);
                        editor.putString("login_ADDRESS", AdminAddress);
                        editor.putString("login_AD_ID", AdminCode);

                        editor.apply();
                        Intent myLogin = new Intent(AdminLogin.this, DashBoard.class);
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
                params.put("ADMIN_PHONE", PhoneNumber);
                params.put("ADMIN_PASSWORD", GetPassword);
                return requestHandler.sendPostRequest(MainUrl + CheckLogin, params);
            }
        }
        CheckPHP ul = new CheckPHP();
        ul.execute();
    }
}
