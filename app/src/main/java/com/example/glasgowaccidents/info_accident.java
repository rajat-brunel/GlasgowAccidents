package com.example.glasgowaccidents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class info_accident extends AppCompatActivity {

    private SQLiteDatabase mDatabase_info;
    private RecyclerView mRecyclerView_info;
    private RecyclerView.Adapter mAdapter_info;
    private LinearLayoutManager mLayoutManager_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_accident);


        Bundle bundle = getIntent().getExtras();
        String acc = bundle.getString("acc");
        String where = "Accident_Index='"+acc+"'";

        DatabaseHelper dbhelper_info = new DatabaseHelper(this);
        mDatabase_info = dbhelper_info.getWritableDatabase();


        mRecyclerView_info = findViewById(R.id.info_recyclerView);
        mLayoutManager_info = new LinearLayoutManager(this);
        Cursor c1 = getInfo(where);
        ArrayList<card_accident_item> exampleList = new ArrayList<>();


        if (c1 != null) {

            //more to the first row
            c1.moveToFirst();

            //iterate over rows
            for (int i = 0; i < c1.getCount(); i++) {

                //iterate over the columns
                for (int j = 0; j < c1.getColumnCount(); j++) {

                    String col = c1.getColumnName(j);
                    col = col.replace("_", " ");
                    String val = c1.getString(j);

                    //append the column value to the string builder and delimit by a pipe symbol
                    exampleList.add(new card_accident_item(col, val));
                    Log.d("Column_name", col);
                }
                c1.moveToNext();
            }
            c1.close();
        }

        mAdapter_info = new InfoAdapter(exampleList);

        mRecyclerView_info.setLayoutManager(mLayoutManager_info);
        mRecyclerView_info.setAdapter(mAdapter_info);


    }

    private Cursor getInfo(String where) {
        return mDatabase_info.query("table_1",
                null,
                 where,
                null,
                null,
                null,
                null);
    }
}
