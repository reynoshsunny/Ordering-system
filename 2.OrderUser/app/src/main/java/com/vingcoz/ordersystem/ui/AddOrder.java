package com.vingcoz.ordersystem.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.vingcoz.ordersystem.R;
import com.vingcoz.ordersystem.utils.ApiUtils;
import com.vingcoz.ordersystem.utils.RequestHandler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;


public class AddOrder extends AppCompatActivity {

    public static final String TAG = "MY MESSAGE";
    private int PICK_IMAGE_REQUEST = 1;
    private Button buttonChoose;
    private Button buttonUpload;
    private ImageView imageView;
    private Bitmap bitmap;
    private Uri filePath;
    SharedPreferences MainPreference;
    String CustomerID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);
        setTitle("Add order");

        MainPreference = AddOrder.this.getSharedPreferences("com.vingcoz.orderingsys", Context.MODE_PRIVATE);
        CustomerID = MainPreference.getString("login_CUS_ID", "empty");

        buttonChoose = findViewById(R.id.buttonChoose);
        buttonUpload = findViewById(R.id.buttonUpload);
        imageView = findViewById(R.id.imageView);

        buttonChoose.setOnClickListener(v -> {
            showFileChooser();
        });
        buttonUpload.setOnClickListener(v -> {
            uploadImage();
        });
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void uploadImage() {
        class UploadImage extends AsyncTask<Bitmap, Void, String> {

            ProgressDialog loading;
            RequestHandler rh = new RequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(AddOrder.this, "Sending Order", "Please wait...", true, true);
            }

            @Override
            protected void onPostExecute(String s) {

                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Bitmap... params) {
                Bitmap bitmap = params[0];
                String uploadImage = getStringImage(bitmap);

                HashMap<String, String> data = new HashMap<>();
                data.put("CUS_ID", CustomerID);
                data.put("IMAGE_DATA", uploadImage);
                data.put("REMARKS", "xyz");

                String result = rh.sendPostRequest(ApiUtils.MainUrl + ApiUtils.AddOrder, data);

                return result;


            }
        }

        UploadImage ui = new UploadImage();
        ui.execute(bitmap);
    }


}

