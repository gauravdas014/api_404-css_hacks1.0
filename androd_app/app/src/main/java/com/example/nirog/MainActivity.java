package com.example.nirog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.nirog.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set up view binding for main activity
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
    }
}