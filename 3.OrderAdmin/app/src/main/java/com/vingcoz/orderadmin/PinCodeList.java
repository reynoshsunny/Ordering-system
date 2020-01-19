package com.vingcoz.orderadmin;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.victor.loading.rotate.RotateLoading;
import com.vingcoz.orderadmin.adapter.CustomerListAdapter;
import com.vingcoz.orderadmin.adapter.PinCodeListAdapter;
import com.vingcoz.orderadmin.model.Customers;
import com.vingcoz.orderadmin.model.PinCodes;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PinCodeList extends AppCompatActivity {

    RotateLoading newtonCradleLoading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_code_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        newtonCradleLoading = findViewById(R.id.rotateloading);
        newtonCradleLoading.setLoadingColor(R.color.colorPrimary);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        ApiUtils service = RetrofitClient.getRetrofitInstance().create(ApiUtils.class);

        Call<List<PinCodes>> call = service.getPinCodes();

        newtonCradleLoading.start();


        call.enqueue(new Callback<List<PinCodes>>() {

            @Override
            public void onResponse(Call<List<PinCodes>> call, Response<List<PinCodes>> response) {
                loadDataList(response.body());
                if(newtonCradleLoading.isStart()){
                    newtonCradleLoading.stop();
                }
            }

            @Override
            public void onFailure(Call<List<PinCodes>> call, Throwable throwable) {

                Toast.makeText(PinCodeList.this, "Unable to load Pin codes", Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void loadDataList(List<PinCodes> PinCodelist) {

        RecyclerView myRecyclerView = findViewById(R.id.myRecyclerView);
        PinCodeListAdapter myAdapter = new PinCodeListAdapter(PinCodelist);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(PinCodeList.this);
        myRecyclerView.setLayoutManager(layoutManager);

        myRecyclerView.setAdapter(myAdapter);
    }


}
