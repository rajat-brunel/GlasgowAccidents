package com.example.glasgowaccidents;

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


public class AccidentsAdapter extends RecyclerView.Adapter<AccidentsAdapter.AccidentsViewHolder> {

    private Context mContext;
    private Cursor mCursor;


    public AccidentsAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;

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
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.card_item, parent, false);
        return new AccidentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccidentsViewHolder holder, int position) {
            if (!mCursor.moveToPosition(position)) {
                return;
            }

            final String acc_index = mCursor.getString(mCursor.getColumnIndex("Accident_Index"));
            String acc_Date = mCursor.getString(mCursor.getColumnIndex("Date"));
            String acc_severe = mCursor.getString(mCursor.getColumnIndex("Accident_Severity"));
            String acc_cas = mCursor.getString(mCursor.getColumnIndex("Number_of_Casualties"));


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
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        if (mCursor != null) {
            mCursor.close();
        }

        mCursor = newCursor;

        if (newCursor != null) {
            notifyDataSetChanged();
        }
    }

}
