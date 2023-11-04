package com.example.newsapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;


import android.app.Activity;

import android.os.Bundle;

import com.example.newsapp.R;
import com.example.newsapp.databinding.ActivityMainBinding;
import com.example.newsapp.model.NewsApiResponse;
import com.example.newsapp.model.NewsHeadlines;
import com.example.newsapp.recyclerview.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private NewsViewModel viewModel;
    private RecyclerAdapter adapter;
    private List<NewsHeadlines> list = new ArrayList<>();
    private ActivityMainBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupRecycler();
        setupViewModel();
        setupNewsDataObservation();

    }


    private void setupViewModel() {
        viewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        viewModel.getNews("us", "health", null);
    }

    private void setupNewsDataObservation() {
        viewModel.mutableLiveData.observe(this, new Observer<NewsApiResponse>() {
            @Override
            public void onChanged(NewsApiResponse newsApiResponse) {

                adapter.setList(newsApiResponse.getArticles());

            }
        });

    }

    private void setupRecycler() {
        adapter = new RecyclerAdapter(this, list);
        binding.mainRecyclerview.setAdapter(adapter);
        binding.mainRecyclerview.setLayoutManager(new GridLayoutManager(this, 2));
        binding.mainRecyclerview.setHasFixedSize(true);

    }

}