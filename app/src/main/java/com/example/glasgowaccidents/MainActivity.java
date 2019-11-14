package com.example.glasgowaccidents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button_landing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_landing = findViewById(R.id.button_landing);
        button_landing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Clicked!!", Toast.LENGTH_SHORT).show();

                Intent intent_list = new Intent(getApplicationContext(), List_accidents.class);
                startActivity(intent_list);
            }
        });
    }
}