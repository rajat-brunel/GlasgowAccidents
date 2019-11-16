package com.example.glasgowaccidents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
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
    private int visibleThreshold = 5;
    int firstVisibleItem, visibleItemCount, totalItemCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_accidents);


        DatabaseHelper dbHelper = new DatabaseHelper(this);
        mDatabase = dbHelper.getWritableDatabase();

        mRecyclerView = findViewById(R.id.accidents_recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new AccidentsAdapter(this,getItems());


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

                    Log.d("Yaeye!", "end called");
                    Toast.makeText(List_accidents.this, "Scrolled!!", Toast.LENGTH_SHORT).show();

                    // Do something
                    mAdapter.notifyDataSetChanged();
                    loading = true;
                }
            }
        });
    }



    private Cursor getItems() {
        return mDatabase.query("table_1",
                null,
                null,
                null,
                null,
                null,
                null,
                "0,15");
    }
}
