package com.vingcoz.ordersystem.ui;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.vingcoz.ordersystem.R;
import com.vingcoz.ordersystem.utils.RequestHandler;
import com.vingcoz.ordersystem.utils.ToastUtils;
import com.vingcoz.ordersystem.utils.ApiUtils;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;

public class ChangeAddress extends AppCompatActivity {

    Button btnAddress;
    EditText txtAddressNew;
    EditText txtPlaceNew;
    EditText txtPinNew;
    SharedPreferences MainPreference;
    String CustomerID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_address);
        setTitle("Change Address");



        MainPreference = ChangeAddress.this.getSharedPreferences("com.vingcoz.orderingsys", Context.MODE_PRIVATE);
        CustomerID =  MainPreference.getString("login_CUS_ID", "empty");


        btnAddress = findViewById(R.id.btnAddress);
        txtAddressNew = findViewById(R.id.txtAddressNew);
        txtPlaceNew = findViewById(R.id.txtPlaceNew);
        txtPinNew = findViewById(R.id.txtPinNew);

        btnAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeAddress.this.Continue();
            }
        });
    }
    public void Continue() {
        if (Validate()) {
            CheckAddress();
        }
    }

    public Boolean Validate() {

        if (txtAddressNew.getText().toString().length() == 0) {
            txtAddressNew.setError("Enter New Address");
            return false;
        }  else {

            return true;
        }
    }


    private void CheckAddress() {

        class CheckAdd extends AsyncTask<Void, Void, String> {

            String GetAddressNew = txtAddressNew.getText().toString();
            String GetPlaceNew = txtPlaceNew.getText().toString();
            String GetPinNew = txtPinNew.getText().toString();

            final String[] Error = new String[1];

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                ToastUtils.ShowToast(ChangeAddress.this, "Checking Address");
            }

            @Override
            protected void onPostExecute(String J_Result) {
                super.onPostExecute(J_Result);

                try {
                    JSONObject obj = new JSONObject(J_Result);
                    Error[0] = obj.getString("error");
                    if (Error[0].equalsIgnoreCase("true")) {

                        ToastUtils.ShowToast(ChangeAddress.this, "Please Continue");
                        Intent myAddress = new Intent(ChangeAddress.this, DashBoard.class);
                        startActivity(myAddress);
                        finish();
                    } else {
                        ToastUtils.ShowToast(ChangeAddress.this, " Address Changed Successfully");
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
                params.put("ADDRESS", GetAddressNew);
               params.put("PLACE", GetPlaceNew);
               params.put("PINCODE", GetPinNew);
                params.put("CUS_ID", CustomerID);
                return requestHandler.sendPostRequest(ApiUtils.MainUrl + ApiUtils.CheckAddress, params);
            }
        }

        CheckAdd ul = new CheckAdd();
        ul.execute();
    }
}