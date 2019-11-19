package com.example.glasgowaccidents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class info_accident extends AppCompatActivity {

    private SQLiteDatabase mDatabase_info;
    private RecyclerView mRecyclerView_info;
    private RecyclerView.Adapter mAdapter_info;
    private LinearLayoutManager mLayoutManager_info;
    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 5;
    int firstVisibleItem, visibleItemCount, totalItemCount;
    ArrayList<information_item> exampleList = new ArrayList<>();
    int add = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_accident);

        Toolbar toolbar_info = findViewById(R.id.toolbar_info);
        toolbar_info.setTitle("Accident Information");
        setSupportActionBar(toolbar_info);

        toolbar_info.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        toolbar_info.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });


        Bundle bundle = getIntent().getExtras();
        String acc = bundle.getString("acc");
        String where = "Accident_Index='"+acc+"'";

        DatabaseHelper dbhelper_info = new DatabaseHelper(this);
        mDatabase_info = dbhelper_info.getWritableDatabase();


        mRecyclerView_info = findViewById(R.id.info_recyclerView);
        mLayoutManager_info = new LinearLayoutManager(this);
        Cursor c1 = getInfo(where);


        if (c1 != null) {

            //more to the first row
            c1.moveToFirst();

            //iterate over rows
            for (int i = 0; i < c1.getCount(); i++) {

                //iterate over the columns
                for (int j = 0; j < c1.getColumnCount()-2; j++) {

                    String col = c1.getColumnName(j);
                    col = col.replace("_", " ");
                    String val = c1.getString(j);
                    exampleList.add(new information_item(col, val));
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return true;
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
