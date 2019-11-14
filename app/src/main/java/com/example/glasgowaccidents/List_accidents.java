package com.example.glasgowaccidents;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class List_accidents extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_accidents);

        ArrayList<card_accident_item> exampleList = new ArrayList<>();
        exampleList.add(new card_accident_item("Line1", "Line1.1", "Line1.2", "Line1.3"));
        exampleList.add(new card_accident_item("Line2", "Line2.1", "Line2.2", "Line2.3"));
        exampleList.add(new card_accident_item("Line3", "Line3.1", "Line3.2", "Line3.3"));
    }
}
