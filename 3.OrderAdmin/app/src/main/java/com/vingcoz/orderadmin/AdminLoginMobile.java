package com.vingcoz.orderadmin;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class AdminLoginMobile extends AppCompatActivity {


    Button btnNext;
    EditText txtMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login_mobile);

        btnNext = findViewById(R.id.btnNext);
        txtMobile = findViewById(R.id.txtMobile);

        btnNext.setOnClickListener(v -> AdminLoginMobile.this.Continue());
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
            }

            @Override
            protected void onPostExecute(String J_Result) {
                super.onPostExecute(J_Result);
                try {
                    JSONObject obj = new JSONObject(J_Result);
                    Error[0] = obj.getString("error");
                    if (Error[0].equalsIgnoreCase("true"))
                    {

                        ToastUtils.ShowToast(AdminLoginMobile.this, "Number is Wrong!!!");

                    }
                    else
                    {
                        ToastUtils.ShowToast(AdminLoginMobile.this, "Please Enter Your Password");
                        Intent myLogin = new Intent(AdminLoginMobile.this, AdminLogin.class);
                        myLogin.putExtra("PhoneNumber", GetPhoneNumber);
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
                params.put("ADMIN_PHONE", GetPhoneNumber);
                return requestHandler.sendPostRequest(ApiUtils.MainUrl + ApiUtils.CheckAdminPhone, params);
            }
        }
        CheckPH ul = new CheckPH();
        ul.execute();
    }
}

