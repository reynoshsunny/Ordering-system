package com.vingcoz.ordersystem.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vingcoz.ordersystem.R;
import com.vingcoz.ordersystem.utils.RequestHandler;
import com.vingcoz.ordersystem.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import static com.vingcoz.ordersystem.utils.ApiUtils.CheckSignUp;
import static com.vingcoz.ordersystem.utils.ApiUtils.MainUrl;

public class SignUp extends AppCompatActivity {

    EditText edtSignName, edtSignAddress, edtSignPlace, edtSignPin,
            edtSignMobile, edtSignMail, edtSignPassword, edtSignConfirm;

    Button btnSign;
    String mName, mAddress, mPlace, mPin, mMobile, mMail, mPassword, mConfirm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        btnSign = findViewById(R.id.btnSign);
        btnSign.setOnClickListener(this::onClickRegister);
        edtSignName = findViewById(R.id.textSignName);
        edtSignAddress = findViewById(R.id.textSignAddress);
        edtSignPlace = findViewById(R.id.textSignPlace);
        edtSignPin = findViewById(R.id.textSignPin);
        edtSignMobile = findViewById(R.id.textSignMobile);
        edtSignMail = findViewById(R.id.textSignMail);
        edtSignPassword = findViewById(R.id.textSignPassword);
        edtSignConfirm = findViewById(R.id.textSignConfirm);

        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUp.this.Continue();
            }
        });
    }

    public void onClickRegister(View v) {

        boolean invalid = false;

        switch (v.getId()) {

            case R.id.btnSign:

                System.out.println("REGISTER BUTTON CLICK");
                mName = edtSignName.getText().toString();
                mAddress = edtSignAddress.getText().toString();
                mPlace = edtSignPlace.getText().toString();
                mPin = edtSignPin.getText().toString();
                mMobile = edtSignMobile.getText().toString();
                mMail = edtSignMail.getText().toString();
                mPassword = edtSignPassword.getText().toString();
                mConfirm = edtSignConfirm.getText().toString();


                if (mName.equals("")) {
                    invalid = true;
                    Toast.makeText(getApplicationContext(), "Please Enter Your Name",
                            Toast.LENGTH_SHORT).show();
                } else if (mAddress.equals("")) {
                    invalid = true;
                    Toast.makeText(getApplicationContext(),
                            "Please Enter Your Address", Toast.LENGTH_SHORT)
                            .show();
                } else if (mPlace.equals("")) {
                    invalid = true;
                    Toast.makeText(getApplicationContext(),
                            "Please Enter Your Place", Toast.LENGTH_SHORT)
                            .show();
                } else if (mPin.equals("")) {
                    invalid = true;
                    Toast.makeText(getApplicationContext(),
                            "Please Enter Your Pin Code", Toast.LENGTH_SHORT)
                            .show();

                } else if (mMobile.equals("")) {
                    invalid = true;
                    Toast.makeText(getApplicationContext(),
                            "Please Enter Your Mobile Number", Toast.LENGTH_SHORT).show();
                } else if (!mMobile.matches("[0-9]{10}")) {
                    edtSignMobile.setError("Invalid Mobile Number");
                    //Intent intent = new Intent(SignUp.this, CheckOtp.class);
                    //intent.putExtra("mMobile", mMobile);
                    //startActivity(intent);

                } else if (mMail.equals("")) {
                    invalid = true;
                    Toast.makeText(getApplicationContext(), "Please Enter Your E-Mail", Toast.LENGTH_SHORT).show();
                } else if (!mMail.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {
                    edtSignMail.setError("Invalid Mail ID");
                } else if (mPassword.equals("")) {
                    invalid = true;
                    Toast.makeText(getApplicationContext(),
                            "Please Enter Your Password", Toast.LENGTH_SHORT).show();
                    // } else
                    // if (!mPassword.matches("((?=.*\\\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})")) {
                    // edtSignPassword.setError("Too short!! Minimum 6 Characters" + "\n"
                    //  + "Uppercase Characters is Required" + "\n" + "Special Symbol “*” is not Allowed" + "\n"
                    // + "Digit is required" + "\n" + "Lower case Character is Required");
                } else if (mConfirm.equals("")) {
                    invalid = true;
                    Toast.makeText(getApplicationContext(),
                            "Please Enter Confirm Password", Toast.LENGTH_SHORT).show();
                } else if (!mPassword.equals(mConfirm)) {
                    edtSignConfirm.setError("Password Not Matching");
                }
                break;
        }

        if (!invalid) {
            Intent mySign = new Intent(this, Login.class);
            startActivity(mySign);
            Toast.makeText(this, "Successfully Registered", Toast.LENGTH_LONG).show();
        }
    }


    public void Continue() {
        if (Validate()) {
            CheckSignUp();
        }
    }

    public Boolean Validate() {

        if ((edtSignName.getText().toString() == "") || (edtSignAddress.getText().toString() == "")
                || (edtSignPlace.getText().toString() == "") || (edtSignPin.getText().toString() == "")
                || (edtSignMobile.getText().toString() == "") || (edtSignMail.getText().toString() == "")
                || (edtSignPassword.getText().toString() == "")) {

            return false;
        } else {

            return true;
        }
    }

    private void CheckSignUp() {

        class CheckSign extends AsyncTask<Void, Void, String> {

            String GetName = edtSignName.getText().toString();
            String GetAddress = edtSignAddress.getText().toString();
            String GetPlace = edtSignPlace.getText().toString();
            String GetPin = edtSignPin.getText().toString();
            String GetMobile = edtSignMobile.getText().toString();
            String GetMail = edtSignMail.getText().toString();
            String GetPassword = edtSignPassword.getText().toString();
            final String[] Error = new String[1];

            SharedPreferences MainPreference;
            String SignUser;
            String SignAddress;
            String SignPlace;
            String SignPin;
            String SignMobile;
            String SignMail;
            String SignPassword;


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                ToastUtils.ShowToast(SignUp.this, "Checking Registration");
            }

            @Override
            protected void onPostExecute(String J_Result) {
                super.onPostExecute(J_Result);

                try {
                    JSONObject obj = new JSONObject(J_Result);
                    Error[0] = obj.getString("error");
                    if (Error[0].equalsIgnoreCase("true")) {
                        ToastUtils.ShowToast(SignUp.this, "No Details Entered");

                    } else {
                        ToastUtils.ShowToast(SignUp.this, "Please login to continue");
                        Intent myLogin = new Intent(SignUp.this, LoginMobile.class);
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
                params.put("NAME", GetName);
                params.put("ADDRESS", GetAddress);
                params.put("PLACE", GetPlace);
                params.put("PINCODE", GetPin);
                params.put("PHONE_NO", GetMobile);
                params.put("EMAIL_ID", GetMail);
                params.put("PASSWORD", GetPassword);
                params.put("X_CORD", "TEST X");
                params.put("Y_CORD", "TEST Y");


                return requestHandler.sendPostRequest(MainUrl + CheckSignUp, params);
            }
        }
        CheckSign ul = new CheckSign();
        ul.execute();
    }
}
