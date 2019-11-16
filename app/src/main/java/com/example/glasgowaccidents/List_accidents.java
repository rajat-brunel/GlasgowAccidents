package com.example.glasgowaccidents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.ArrayList;

public class List_accidents extends AppCompatActivity {

    private SQLiteDatabase mDatabase;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


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
    }

    private Cursor getItems() {
        return mDatabase.query("table_1",
                null,
                null,
                null,
                null,
                null,
                null);
    }
}
