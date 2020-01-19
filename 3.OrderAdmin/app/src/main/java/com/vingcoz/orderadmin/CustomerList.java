package com.vingcoz.orderadmin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.victor.loading.rotate.RotateLoading;
import com.vingcoz.orderadmin.adapter.CustomerListAdapter;
import com.vingcoz.orderadmin.model.Customers;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerList extends AppCompatActivity {

    private RecyclerView myRecyclerView;
    private CustomerListAdapter myAdapter;

    RotateLoading newtonCradleLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);

        setTitle("Customers");

        newtonCradleLoading = findViewById(R.id.rotateloading);
        newtonCradleLoading.setLoadingColor(R.color.colorPrimary);

        ApiUtils service = RetrofitClient.getRetrofitInstance().create(ApiUtils.class);
        Call<List<Customers>> call = service.getUsers();

        newtonCradleLoading.start();

        call.enqueue(new Callback<List<Customers>>() {

            @Override
            public void onResponse(Call<List<Customers>> call, Response<List<Customers>> response) {
                loadDataList(response.body());

                if(newtonCradleLoading.isStart()){
                    newtonCradleLoading.stop();
                }
            }

            @Override
            public void onFailure(Call<List<Customers>> call, Throwable throwable) {
                Toast.makeText(CustomerList.this, "Unable to load Customers", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadDataList(List<Customers> usersList) {

        myRecyclerView = findViewById(R.id.myRecyclerView);
        myAdapter = new CustomerListAdapter(usersList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CustomerList.this);
        myRecyclerView.setLayoutManager(layoutManager);

        myRecyclerView.setAdapter(myAdapter);
    }

}