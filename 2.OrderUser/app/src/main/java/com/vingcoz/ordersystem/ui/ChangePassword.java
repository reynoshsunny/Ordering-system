package com.vingcoz.ordersystem.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.victor.loading.rotate.RotateLoading;
import com.vingcoz.ordersystem.R;
import com.vingcoz.ordersystem.utils.RequestHandler;
import com.vingcoz.ordersystem.utils.ToastUtils;
import com.vingcoz.ordersystem.utils.ApiUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class ChangePassword extends AppCompatActivity {

    Button btnPassword;
    EditText txtPassNew;
    SharedPreferences MainPreference;
    String CustomerID;
    RotateLoading newtonCradleLoading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        setTitle("Change Password");

        MainPreference = ChangePassword.this.getSharedPreferences("com.vingcoz.orderingsys", Context.MODE_PRIVATE);
        CustomerID = MainPreference.getString("login_CUS_ID", "empty");


        newtonCradleLoading = findViewById(R.id.rotateloading);
        newtonCradleLoading.setLoadingColor(R.color.colorPrimary);

        btnPassword = findViewById(R.id.btnPassword);
        txtPassNew = findViewById(R.id.txtPassNew);

        btnPassword.setOnClickListener(v -> ChangePassword.this.Continue());
    }

    public void Continue() {
        if (Validate()) {
            CheckPasswordNew();
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

    private void CheckPasswordNew() {

        class CheckPass extends AsyncTask<Void, Void, String> {

            String GetPasswordNew = txtPassNew.getText().toString();
            final String[] Error = new String[1];

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
                        newtonCradleLoading.stop();
                        ToastUtils.ShowToast(ChangePassword.this, "No Change");
                        Intent myPassNew = new Intent(ChangePassword.this, DashBoard.class);
                        startActivity(myPassNew);
                        finish();
                    } else {
                        newtonCradleLoading.stop();
                        ToastUtils.ShowToast(ChangePassword.this, "Password Changed!!");
                        SharedPreferences.Editor editor = MainPreference.edit();
                        editor.putString("login_user", "empty");
                        editor.putString("login_phno", "empty");
                        editor.putString("login_ADDRESS", "empty");
                        editor.putString("login_PINCODE", "empty");
                        editor.putString("login_REFERALCD", "empty");
                        editor.putString("login_CUS_ID", "empty");
                        editor.apply();
                        Intent myPassNew = new Intent(ChangePassword.this, LoginMobile.class);
                        startActivity(myPassNew);
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
                params.put("PASSWORD", GetPasswordNew);
                params.put("CUS_ID", CustomerID);
                return requestHandler.sendPostRequest(ApiUtils.MainUrl + ApiUtils.CheckPasswordNew, params);
            }
        }
        CheckPass ul = new CheckPass();
        ul.execute();
    }
}
