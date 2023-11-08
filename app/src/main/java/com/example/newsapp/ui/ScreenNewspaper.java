package com.example.newsapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.newsapp.R;

public class ScreenNewspaper extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Base_Theme_NewsApp);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_newspaper);
    }
}