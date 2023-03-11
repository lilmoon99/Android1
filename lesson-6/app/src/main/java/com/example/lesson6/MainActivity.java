package com.example.lesson6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CitiesFragment citiesFragment = new CitiesFragment();
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.coat_of_arms_container, CoatOfArmsFragment.newInstance(0))
                    .commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, citiesFragment)
                    .commit();
        }
    }
}