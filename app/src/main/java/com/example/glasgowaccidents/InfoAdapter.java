package com.example.glasgowaccidents;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Adapter class for Accident Information page, adds data to the views
 */

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.InfoViewHolder> {

    private ArrayList<information_item> mAccident_info;


    public InfoAdapter(ArrayList<information_item> accidentList){
        mAccident_info = accidentList;

    }

    public class InfoViewHolder extends RecyclerView.ViewHolder{

        public TextView mTextView1_info;
        public TextView mTextView2_info;

        public InfoViewHolder(@NonNull View itemView) {
            super(itemView);

            mTextView1_info = itemView.findViewById(R.id.row_name);
            mTextView2_info = itemView.findViewById(R.id.row_val);

        }
    }

    @NonNull
    @Override
    public InfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater_info = LayoutInflater.from(parent.getContext());
        View view_info = inflater_info.inflate(R.layout.info_item, parent, false);
        return new InfoViewHolder(view_info);
    }

    @Override
    public void onBindViewHolder(@NonNull InfoViewHolder holder, int position) {
        information_item currentItem = mAccident_info.get(position);


        holder.mTextView1_info.setText(currentItem.getText1());
        holder.mTextView2_info.setText(currentItem.getText2());}



    @Override
    public int getItemCount() {
        return mAccident_info.size();
    }

}
