package com.example.glasgowaccidents;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class AccidentsAdapter extends RecyclerView.Adapter<AccidentsAdapter.AccidentsViewHolder> {

    private ArrayList<card_accident_item> mAccidentList;
    private Context mContext;


    public AccidentsAdapter(ArrayList<card_accident_item> accidentList, Context context) {
        mAccidentList = accidentList;
        mContext = context;

    }


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


    @NonNull
    @Override
    public AccidentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.card_item, parent, false);
        return new AccidentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccidentsViewHolder holder, int position) {
            card_accident_item currentItem = mAccidentList.get(position);

            final String acc_index = currentItem.getText1();
            String acc_Date = currentItem.getText2();
            String acc_severe = currentItem.getText3();
            String acc_cas = currentItem.getText4();


            holder.mTextView1.setText("Index : "+acc_index);
            holder.mTextView2.setText("Date : " + acc_Date);
            holder.mTextView3.setText("Severity : " + acc_severe);
            holder.mTextView4.setText("Casualties : " +acc_cas);

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Intent intent = new Intent(mContext, info_accident.class);
                    intent.putExtra("acc",acc_index);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                    return false;
                }
            });


    }

    @Override
    public int getItemCount() {
        return mAccidentList.size();
    }

    /**public void swapCursor(Cursor newCursor) {
        if (mCursor != null) {
            mCursor.close();
        }

        mCursor = newCursor;

        if (newCursor != null) {
            notifyDataSetChanged();
        }
    }**/

}
