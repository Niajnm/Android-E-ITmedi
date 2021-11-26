package com.example.e_itmedi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_itmedi.Database.DataResponse;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    Context context;
    ArrayList<DataResponse> rdata;

    public CustomAdapter(Context context, ArrayList<DataResponse> rdata) {
        this.context = context;
        this.rdata = rdata;
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.sample_item_layout, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, int position) {
        holder.viewTitle.setText(rdata.get(position).getTT());
        holder.viewDesc.setText(rdata.get(position).getDD());
        holder.viewPrice.setText(rdata.get(position).getPP());
    }

    @Override
    public int getItemCount() {
        return rdata.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView viewTitle, viewPrice, viewDesc;

        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            viewTitle = itemView.findViewById(R.id.textView_title);
            viewPrice = itemView.findViewById(R.id.textView_price);
            viewDesc = itemView.findViewById(R.id.textView_desc);

        }
    }
}
