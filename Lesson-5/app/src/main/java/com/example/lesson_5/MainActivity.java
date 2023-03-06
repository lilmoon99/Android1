package com.example.lesson_5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.lesson_5.Calculator.UI.CalculatorActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = new Intent(this, CalculatorActivity.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(intent);
    }
}
