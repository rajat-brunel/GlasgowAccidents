package com.example.glasgowaccidents;

import androidx.annotation.NonNull;
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
                for (int j = 0; j < c1.getColumnCount(); j++) {

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

        mRecyclerView_info.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = mRecyclerView_info.getChildCount();
                totalItemCount = mLayoutManager_info.getItemCount();
                firstVisibleItem = mLayoutManager_info.findFirstVisibleItemPosition();

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

        String add_s = Integer.toString(add);
        mAdapter_info.notifyDataSetChanged();
        add = add + 1;
        Log.d("TAG", add_s);
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
