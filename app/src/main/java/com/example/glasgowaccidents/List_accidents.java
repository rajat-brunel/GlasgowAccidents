package com.example.glasgowaccidents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
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

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Accident List");
        setSupportActionBar(toolbar);

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        mDatabase = dbHelper.getWritableDatabase();
        limit = ",15";
        offset = 0;


        mRecyclerView = findViewById(R.id.accidents_recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        page = getItems("0,15",null);

        addData(page);

        mAdapter = new AccidentsAdapter(acc_list,this);
        where = null;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.icon_filter:
                mLayoutManager.scrollToPositionWithOffset(0, 0);
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(List_accidents.this, R.style.AlertDialogTheme);
                View mView = getLayoutInflater().inflate(R.layout.filter_dialog, null);
                final Spinner mSpinner = mView.findViewById(R.id.spinner_cas);
                ArrayAdapter<String> severeAdapter = new ArrayAdapter<String>(List_accidents.this,
                        android.R.layout.simple_spinner_item,
                        getResources().getStringArray(R.array.casualtyList));
                severeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mSpinner.setAdapter(severeAdapter);

                mBuilder.setPositiveButton("Filter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(!mSpinner.getSelectedItem().toString().equalsIgnoreCase("Any")){
                            Toast.makeText(List_accidents.this,
                                    "Number_of_Casualties = " +mSpinner.getSelectedItem().toString(),
                                    Toast.LENGTH_SHORT)
                                    .show();
                            where = "Number_of_Casualties = " +mSpinner.getSelectedItem().toString();
                            page = getItems_where(where);
                            acc_list.clear();
                            addData(page);
                            mAdapter.notifyDataSetChanged();
                            dialog.dismiss();
                        }
                    }
                });

                mBuilder.setNegativeButton("Dismiss!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void pagination(){
        Toast.makeText(List_accidents.this, where, Toast.LENGTH_SHORT).show();
        offset = offset +15;
        String value = Integer.toString(offset) + limit;
        page = getItems(value, where );
        loading = true;

        addData(page);

        mAdapter.notifyDataSetChanged();
    }



    private Cursor getItems(String limit, String where) {
        return mDatabase.query("table_1",
                null,
                where,
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
                "0,15");
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
