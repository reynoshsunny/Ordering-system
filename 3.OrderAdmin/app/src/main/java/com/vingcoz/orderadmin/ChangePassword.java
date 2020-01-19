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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class ChangePassword extends AppCompatActivity {

    Button btnPassword;
    EditText txtPassNew;
    SharedPreferences MainPreference;
    String AdminCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);


        MainPreference = ChangePassword.this.getSharedPreferences("com.vingcoz.orderadmin", Context.MODE_PRIVATE);
        AdminCode = MainPreference.getString("login_AD_ID", "empty");

        btnPassword = findViewById(R.id.btnPassword);
        txtPassNew = findViewById(R.id.txtPassNew);

        btnPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangePassword.this.Continue();
            }
        });
    }
    public void Continue() {
        if (Validate()) {
            checkAdminPasswordNew();
        }
    }
    public Boolean Validate() {

        if (txtPassNew.getText().toString().length() < 4) {
            txtPassNew.setError("Invalid Password");
            return false;
        } else {
            return true;
        }
    }
    private void checkAdminPasswordNew() {

        class CheckPass extends AsyncTask<Void, Void, String> {

            String GetPasswordNew = txtPassNew.getText().toString();
            final String[] Error = new String[1];

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                ToastUtils.ShowToast(ChangePassword.this, "Checking Password");
            }

            @Override
            protected void onPostExecute(String J_Result) {
                super.onPostExecute(J_Result);

                try {
                    JSONObject obj = new JSONObject(J_Result);
                    Error[0] = obj.getString("error");
                    if (Error[0].equalsIgnoreCase("true")) {

                        ToastUtils.ShowToast(ChangePassword.this, "No Change");
                        Intent myPassNew = new Intent(ChangePassword.this, DashBoard.class);
                        startActivity(myPassNew);
                        finish();
                    } else {
                        ToastUtils.ShowToast(ChangePassword.this, "Password Changed!!");
                        Intent myPassNew = new Intent(ChangePassword.this, DashBoard.class);
                        startActivity(myPassNew);
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
                params.put("ADMIN_PASSWORD", GetPasswordNew);
                params.put("ADMIN_ID", AdminCode);
                return requestHandler.sendPostRequest(ApiUtils.MainUrl + ApiUtils.CheckAdminPasswordUpdate, params);
            }
        }
        CheckPass ul = new CheckPass();
        ul.execute();
    }
}


