package com.example.glasgowaccidents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class List_accidents extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_accidents);

        ArrayList<card_accident_item> exampleList = new ArrayList<>();
        exampleList.add(new card_accident_item("201897GA00102", "Date : 02/02/2018", "Severity : Slight", "Casualty : 1"));
        exampleList.add(new card_accident_item("201897GA00107", "Date : 02/02/2018", "Severity : Slight", "Casualty : 1"));
        exampleList.add(new card_accident_item("201897GA00108", "Date : 02/02/2018", "Severity : Slight", "Casualty : 2"));


        mRecyclerView = findViewById(R.id.accidents_recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new AccidentsAdapter(exampleList);


        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}
