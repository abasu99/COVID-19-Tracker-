package com.example.proapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StateAdapter extends RecyclerView.Adapter<StateAdapter.StateViewHolder> {

    private Context mContext;
    private ArrayList<StateList> mStateList;

    public StateAdapter(Context mContext, ArrayList<StateList> mStateList) {
        this.mContext = mContext;
        this.mStateList = mStateList;
    }

    @NonNull
    @Override
    public StateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.state_lists,parent,false);
        return new StateViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StateViewHolder holder, int position) {

        StateList currentItem=mStateList.get(position);

        String head=currentItem.gethead();
        String data1=currentItem.getdata1();
        String data2=currentItem.getdata2();
        String data3=currentItem.getdata3();
        String data4=currentItem.getdata4();
        String data5=currentItem.getdata5();
//        int data6= currentItem.getdata6();
//        int data7= currentItem.getdata7();


        holder.mhead.setText(head);
        holder.mdata1.setText("Total cases: "+data1);
        holder.mdata2.setText("Recovered: "+data2);
        holder.mdata3.setText("Deceased: "+data3);
        holder.mdata4.setText("Active: "+data4);
        holder.mdata5.setText("Last Updated Time: "+data5);
//        holder.mdata6.setText("Recovery Rate: "+data6+"%");
//        holder.mdata7.setText("Death Rate: "+data7+"%");

    }

    @Override
    public int getItemCount() {
        return mStateList.size();
    }


    public class StateViewHolder extends RecyclerView.ViewHolder{

        public TextView mhead;
        public TextView mdata1;
        public TextView mdata2;
        public TextView mdata3;
        public TextView mdata4;
        public TextView mdata5;
//        public TextView mdata6;
//        public TextView mdata7;

        public StateViewHolder(@NonNull View itemView) {
            super(itemView);
            mhead=itemView.findViewById(R.id.head);
            mdata1=itemView.findViewById(R.id.data1);
            mdata2=itemView.findViewById(R.id.data2);
            mdata3=itemView.findViewById(R.id.data3);
            mdata4=itemView.findViewById(R.id.data4);
            mdata5=itemView.findViewById(R.id.data5);
//            mdata6=itemView.findViewById(R.id.data6);
//            mdata7=itemView.findViewById(R.id.data7);
        }
    }
}
