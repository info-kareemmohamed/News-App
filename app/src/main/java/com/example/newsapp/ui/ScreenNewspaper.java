package com.example.newsapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;

import com.example.newsapp.R;
import com.example.newsapp.databinding.ActivityScreenNewspaperBinding;
import com.example.newsapp.model.NewsHeadlines;
import com.squareup.picasso.Picasso;

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
        getDataFormIntent();
        setDatainText();

        checkFavorite();
      clickURL();
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


    private void getDataFormIntent() {
        Intent intent = getIntent();
        newsHeadlines = intent.getParcelableExtra("news");
        Picasso.get().load(newsHeadlines.getUrlToImage()).into(binding.ScreenNewspaperImageView);

    }
private void setDatainText(){
    binding.ScreenNewspaperTitle.setText(newsHeadlines.getTitle());
    binding.ScreenNewspaperDescription.setText(newsHeadlines.getDescription());
    binding.ScreenNewspaperAuthor.setText(newsHeadlines.getAuthor());
    binding.ScreenNewspaperSource.setText(newsHeadlines.getSource().getName());
    binding.ScreenNewspaperContent.setText(newsHeadlines.getContent());
    binding.ScreenNewspaperPublishedAt.setText(newsHeadlines.getPublishedAt());
    binding.ScreenNewspaperUrl.setText(newsHeadlines.getUrl());

}
private void clickURL(){
        binding.ScreenNewspaperUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToURL(newsHeadlines.getUrl());
            }
        });

}
    private void goToURL(String s){
        Uri uri=Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
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