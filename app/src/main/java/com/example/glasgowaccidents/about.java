package com.example.glasgowaccidents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class about extends AppCompatActivity {

    private TextView mTextViewAbout_1;
    private TextView mTextViewAbout_2;
    private TextView mTextViewAbout_3;
    private TextView mTextViewAbout_4;
    private TextView mTextViewAbout_5;
    private TextView mTextViewAbout_6;
    private TextView mTextViewAbout_7;
    private TextView mTextViewAbout_8;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Toolbar toolbar = findViewById(R.id.toolbar_about);
        toolbar.setTitle("About");
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });

        mTextViewAbout_1 = findViewById(R.id.about_1);
        mTextViewAbout_2 = findViewById(R.id.about_2);
        mTextViewAbout_3 = findViewById(R.id.about_3);
        mTextViewAbout_4 = findViewById(R.id.about_4);
        mTextViewAbout_5 = findViewById(R.id.about_5);
        mTextViewAbout_6 = findViewById(R.id.about_6);
        mTextViewAbout_7 = findViewById(R.id.about_7);
        mTextViewAbout_8 = findViewById(R.id.about_8);

        String about_q1 = "What is this app about ?";
        String about_a1 = "This app shows statistics about road accidents in Glasgow in 2018, the data is" +
                "captured via Glasgow open Data, and presented in a mobile readable format." +
                "The Original data could be found at the UK Government Data portal data.gov.uk";

        String about_q2 = "How to look up the Accident List?";
        String about_a2 = "Taping on the Accident List button on the landing page will take you to" +
                "the accident list page, all the accidents are listed on this place, ordered by their" +
                "Index,each accident shows the Date the accident occurred, the Number of Casualty" +
                "and the Severity of the accident. As pagination has been implemented, if you scroll" +
                "down, more data will be loaded automatically, if available.";

        String about_q3 = "Are there any filters available?";
        String about_a3 = "To find the information you need quicker, 2 filters have been implemented."+
                "The list can be filtered by Number of Casualties and The Severity of Accidents.";

        String about_q4 = "How to get more information about a specific Accident ?";
        String about_a4 = "To get more information about an accident, simply long press on the accident" +
                "you want more information about, and a new page will open, showing extensive information" +
                "about that accident.";

        mTextViewAbout_1.setText(about_q1);
        mTextViewAbout_2.setText(about_a1);
        mTextViewAbout_3.setText(about_q2);
        mTextViewAbout_4.setText(about_a2);
        mTextViewAbout_5.setText(about_q3);
        mTextViewAbout_6.setText(about_a3);
        mTextViewAbout_7.setText(about_q4);
        mTextViewAbout_8.setText(about_a4);
    }
}
