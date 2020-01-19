package com.vingcoz.ordersystem.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.victor.loading.rotate.RotateLoading;
import com.vingcoz.ordersystem.R;
import com.vingcoz.ordersystem.utils.RequestHandler;
import com.vingcoz.ordersystem.utils.ApiUtils;
import com.vingcoz.ordersystem.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;

public class LoginMobile extends AppCompatActivity {

    Button btnNext;
    EditText txtMobile;
    RotateLoading newtonCradleLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_mobile);

        newtonCradleLoading = findViewById(R.id.rotateloading);
        newtonCradleLoading.setLoadingColor(R.color.colorPrimary);

        btnNext = findViewById(R.id.btnNext);
        txtMobile = findViewById(R.id.txtMobile);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginMobile.this.Continue();
            }
        });
    }

    public void Continue() {
        if (Validate()) {
            CheckPhoneNumber();
        }
    }

    public Boolean Validate() {

        if (txtMobile.getText().toString().length() != 10) {
            txtMobile.setError("Invalid Mobile number");
            return false;
        } else {

            return true;
        }
    }


    private void CheckPhoneNumber() {

        class CheckPH extends AsyncTask<Void, Void, String> {

            String GetPhoneNumber = txtMobile.getText().toString();
            final String[] Error = new String[1];

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                newtonCradleLoading.start();
            }

            @Override
            protected void onPostExecute(String J_Result) {
                super.onPostExecute(J_Result);
                newtonCradleLoading.stop();

                try {
                    JSONObject obj = new JSONObject(J_Result);
                    Error[0] = obj.getString("error");
                    if (Error[0].equalsIgnoreCase("true")) {

                        ToastUtils.ShowToast(LoginMobile.this, "Please SignUp to continue");
                        newtonCradleLoading.stop();
                        Intent myLogin = new Intent(LoginMobile.this, SignUp.class);
                        startActivity(myLogin);
                        finish();
                    } else {
                        newtonCradleLoading.stop();
                        ToastUtils.ShowToast(LoginMobile.this, "Please Enter Your Password");
                        Intent myLogin = new Intent(LoginMobile.this, Login.class);
                        myLogin.putExtra("PhoneNumber", GetPhoneNumber);
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
                params.put("PHONE_NO", GetPhoneNumber);
                return requestHandler.sendPostRequest(ApiUtils.MainUrl + ApiUtils.CheckPhone, params);
            }
        }

        CheckPH ul = new CheckPH();
        ul.execute();
    }
}
