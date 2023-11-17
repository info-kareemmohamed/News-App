package com.example.newsapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;

import com.example.newsapp.R;
import com.example.newsapp.databinding.ActivityMainBinding;
import com.example.newsapp.databinding.ActivityScreenNewspaperBinding;
import com.example.newsapp.model.NewsHeadlines;

public class ScreenNewspaper extends AppCompatActivity {

    private ActivityScreenNewspaperBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Base_Theme_NewsApp);
        super.onCreate(savedInstanceState);
        binding=ActivityScreenNewspaperBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
       getDataFormIntent_and_setupWebview();
    }

    private void getDataFormIntent_and_setupWebview() {
        Intent intent = getIntent();

            NewsHeadlines newsHeadlines=intent.getParcelableExtra("news");
            binding.webview.loadUrl(newsHeadlines.getUrl());
            WebSettings webSettings=binding.webview.getSettings();
            webSettings.setJavaScriptEnabled(true);

    }
}