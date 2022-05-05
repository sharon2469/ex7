package com.example.ex7;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

// Note that mainActivity is empty because all the work done inside the fragment
public class MainActivity extends AppCompatActivity implements CountriesFragment.CountriesFragmentListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
