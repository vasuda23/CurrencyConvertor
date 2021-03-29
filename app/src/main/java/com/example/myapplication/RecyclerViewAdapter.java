package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    String[] name;
    String[] value;
    Double input;

    public RecyclerViewAdapter(Context mContext, String[] name,Double input,String[] value) {
        this.mContext = mContext;
        this.name =  name;
        this.input=input;
        this.value=value;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.recycler_row,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
                return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText(name[position]);
        Double constant = Double.parseDouble(value[position]);
        holder.textView2.setText(""+(constant*input));
        holder.textView3.setText("1 EUR =" +value[position] + name[position]);


    }

    @Override
    public int getItemCount() {
        return value.length;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView textView,textView2,textView3;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);
            textView3 = itemView.findViewById(R.id.textView3);
        }
    }

}
