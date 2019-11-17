package com.example.glasgowaccidents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class List_accidents extends AppCompatActivity {

    private SQLiteDatabase mDatabase;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 1;
    int firstVisibleItem, visibleItemCount, totalItemCount;
    ArrayList<card_accident_item> acc_list = new ArrayList<>();
    Cursor page;
    String where;
    String limit;
    int offset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_accidents);


        DatabaseHelper dbHelper = new DatabaseHelper(this);
        mDatabase = dbHelper.getWritableDatabase();
        limit = ",15";
        offset = 0;


        mRecyclerView = findViewById(R.id.accidents_recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        page = getItems("0,15");

        addData(page);

        mAdapter = new AccidentsAdapter(acc_list,this);
        where = "Accident_Severity = 'Serious'";

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = mRecyclerView.getChildCount();
                totalItemCount = mLayoutManager.getItemCount();
                firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition();

                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false;
                        previousTotal = totalItemCount;
                    }
                }
                if (!loading && (totalItemCount - visibleItemCount)
                        <= (firstVisibleItem + visibleThreshold)) {
                    // End has been reached

                        pagination();
                }
            }
        });
    }

    private void pagination(){
        Toast.makeText(List_accidents.this, "Loaded More!", Toast.LENGTH_SHORT).show();
        offset = offset +15;
        String value = Integer.toString(offset) + limit;
        page = getItems(value);
        loading = true;

        addData(page);

        mAdapter.notifyDataSetChanged();
    }



    private Cursor getItems(String limit) {
        return mDatabase.query("table_1",
                null,
                null,
                null,
                null,
                null,
                null,
                limit);
    }

    private Cursor getItems_where(String where) {
        return mDatabase.query("table_1",
                null,
                where,
                null,
                null,
                null,
                null,
                "0,10");
    }

    private void addData(Cursor cursor){
        if (cursor != null) {

            //more to the first row
            cursor.moveToFirst();

            //iterate over rows
            for (int i = 0; i < cursor.getCount(); i++) {


                String ind  = cursor.getString(cursor.getColumnIndex("Accident_Index"));
                String date = cursor.getString(cursor.getColumnIndex("Date"));
                String severe = cursor.getString(cursor.getColumnIndex("Accident_Severity"));
                String casualty = cursor.getString(cursor.getColumnIndex("Number_of_Casualties"));


                acc_list.add(new card_accident_item(ind,date,severe,casualty));

                page.moveToNext();
            }
            page.close();
        }
    }

}
