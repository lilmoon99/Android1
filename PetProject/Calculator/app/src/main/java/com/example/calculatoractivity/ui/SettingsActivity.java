package com.example.calculatoractivity.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.calculatoractivity.R;
import com.google.android.material.card.MaterialCardView;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        MaterialCardView theme1 = findViewById(R.id.theme1);
        MaterialCardView theme2 = findViewById(R.id.theme2);
        MaterialCardView theme3 = findViewById(R.id.theme3);
        Intent intent = new Intent();
        theme1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_OK,intent);
                intent.putExtra("Theme",1);
                finish();

            }
        });
        theme2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_OK,intent);
                intent.putExtra("Theme",2);
                finish();

            }
        });
        theme3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setResult(Activity.RESULT_OK,intent);
                intent.putExtra("Theme",3);
                finish();
            }
        });
    }
}