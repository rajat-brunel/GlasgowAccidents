package com.example.glasgowaccidents;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AccidentsAdapter extends RecyclerView.Adapter<AccidentsAdapter.AccidentsViewHolder> {
    private ArrayList<card_accident_item> mAccidentList;

    public static class AccidentsViewHolder extends RecyclerView.ViewHolder{

        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mTextView3;
        public TextView mTextView4;

        public AccidentsViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView1 = itemView.findViewById(R.id.acc_name);
            mTextView2 = itemView.findViewById(R.id.acc_date);
            mTextView3 = itemView.findViewById(R.id.acc_severity);
            mTextView4 = itemView.findViewById(R.id.acc_casualties);
        }
    }

    public AccidentsAdapter(ArrayList<card_accident_item> accidentList) {
            mAccidentList = accidentList;
    }

    @NonNull
    @Override
    public AccidentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View V = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        AccidentsViewHolder avh = new AccidentsViewHolder(V);
        return avh;
    }

    @Override
    public void onBindViewHolder(@NonNull AccidentsViewHolder holder, int position) {
            card_accident_item currentItem = mAccidentList.get(position);

            holder.mTextView1.setText(currentItem.getText1());
            holder.mTextView2.setText(currentItem.getText2());
            holder.mTextView3.setText(currentItem.getText3());
            holder.mTextView4.setText(currentItem.getText4());
    }

    @Override
    public int getItemCount() {
        return mAccidentList.size();
    }
}
