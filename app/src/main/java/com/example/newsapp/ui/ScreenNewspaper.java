package com.example.newsapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;

import com.example.newsapp.R;
import com.example.newsapp.databinding.ActivityScreenNewspaperBinding;
import com.example.newsapp.model.NewsHeadlines;

public class ScreenNewspaper extends AppCompatActivity {

    private FavoriteViewModel viewModel;
    private ActivityScreenNewspaperBinding binding;
    private boolean Favorite;
    private NewsHeadlines newsHeadlines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Base_Theme_NewsApp);
        super.onCreate(savedInstanceState);
        binding = ActivityScreenNewspaperBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(FavoriteViewModel.class);
        getDataFormIntent_and_setupWebview();

        checkFavorite();

        clickListenerFloatingactionbutton();
    }


    private void checkFavorite() {
        viewModel.checkIfUrlExists(newsHeadlines.getUrl());
        viewModel.getIsUrlExistsLiveData().observe(this, isExists -> {

            checkFavoriteToImage(isExists);
            Favorite = isExists;
        });

    }

    private void checkFavoriteToImage(boolean favorite) {
        if (favorite) {
            binding.floatingactionbutton.setImageResource(R.drawable.ic_favorite_w_24);
        } else {

            binding.floatingactionbutton.setImageResource(R.drawable.ic_favorite_24);
        }
    }


    private void getDataFormIntent_and_setupWebview() {
        Intent intent = getIntent();

        newsHeadlines = intent.getParcelableExtra("news");
        binding.webview.loadUrl(newsHeadlines.getUrl());


        WebSettings webSettings = binding.webview.getSettings();
        webSettings.setJavaScriptEnabled(true);

    }


    private void clickListenerFloatingactionbutton() {
        binding.floatingactionbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Favorite) {
                    Favorite = false;
                } else {
                    Favorite = true;
                }
                checkFavoriteToImage(Favorite);

            }
        });

    }


    @Override
    public void onBackPressed() {

        if (Favorite) {

            newsHeadlines.setFavorite(true);
            viewModel.add_news(newsHeadlines);
        } else {

            viewModel.delete_news(newsHeadlines.getUrl());


        }


        super.onBackPressed();
    }


}