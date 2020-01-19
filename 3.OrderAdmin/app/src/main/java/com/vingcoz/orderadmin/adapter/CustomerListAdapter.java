package com.vingcoz.orderadmin.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.vingcoz.orderadmin.R;
import com.vingcoz.orderadmin.model.Customers;

import java.util.List;

public class CustomerListAdapter extends RecyclerView.Adapter<CustomerListAdapter.CustomViewHolder> {

    private List<Customers> dataList;
    private RecyclerView myRecyclerView;

    public CustomerListAdapter(List<Customers> dataList){

        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View myView;
        TextView textUser, textNumber;

        CustomViewHolder(View itemView) {
            super(itemView);
            myView = itemView;

            textUser = myView.findViewById(R.id.user);
            textNumber = myView.findViewById(R.id.uNumber);


        }
    }

    @Override

    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_customerlist, parent, false);
        return new CustomViewHolder(view);
    }

    @Override

    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.textUser.setText(dataList.get(position).getName());
        holder.textNumber.setText(dataList.get(position).getPhone_no());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}