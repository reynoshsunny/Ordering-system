package com.vingcoz.orderadmin.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vingcoz.orderadmin.R;
import com.vingcoz.orderadmin.model.PinCodes;

import java.util.List;

public class PinCodeListAdapter extends RecyclerView.Adapter<PinCodeListAdapter.CustomViewHolder> {

    private List<PinCodes> dataList;

    public PinCodeListAdapter(List<PinCodes> dataList) {

        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View myView;
        TextView txtPincode, txtPlace;

        CustomViewHolder(View itemView) {
            super(itemView);
            myView = itemView;

            txtPincode = myView.findViewById(R.id.pincode);
            txtPlace = myView.findViewById(R.id.place);

        }
    }

    @Override

    public PinCodeListAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_pincodelist, parent, false);
        return new PinCodeListAdapter.CustomViewHolder(view);
    }

    @Override

    public void onBindViewHolder(PinCodeListAdapter.CustomViewHolder holder, int position) {
        holder.txtPincode.setText(dataList.get(position).getPinCode());
        holder.txtPlace.setText(dataList.get(position).getPlace());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}