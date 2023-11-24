package com.example.newsapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.newsapp.R;

import com.example.newsapp.databinding.FragmentHomeBinding;
import com.example.newsapp.model.NewsApiResponse;
import com.example.newsapp.model.NewsHeadlines;
import com.example.newsapp.recyclerview.RecyclerAdapter;
import com.example.newsapp.recyclerview.RecyclerListener;
import com.example.newsapp.ui.NewsViewModel;
import com.example.newsapp.ui.ScreenNewspaper;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements RecyclerListener {

    private FragmentHomeBinding binding;

    private NewsViewModel viewModel;

    private Intent intent;
    private RecyclerAdapter adapter;

    private List<NewsHeadlines> list = new ArrayList<>();


    private static final String ARG_category = "general";
    private static final String ARG_PARAM2 = "param2";

    private String category = "general";
    private String query;

    public HomeFragment() {
        // Required empty public constructor
    }


    public static HomeFragment newInstance(String category, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_category, category);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            category = getArguments().getString(ARG_category);
            query = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        setupRecycler();
        setupViewModel();
        setupNewsDataObservation();
        return binding.getRoot();
    }


    private void setupRecycler() {
        adapter = new RecyclerAdapter(getContext(), list, this);
        binding.mainRecyclerview.setAdapter(adapter);
        binding.mainRecyclerview.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.mainRecyclerview.setHasFixedSize(true);

    }


    private void setupViewModel() {
        viewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        viewModel.getNews("us", category, query);
    }


    private void setupNewsDataObservation() {
        viewModel.mutableLiveData.observe(getActivity(), new Observer<NewsApiResponse>() {
            @Override
            public void onChanged(NewsApiResponse newsApiResponse) {

                adapter.setList(newsApiResponse.getArticles());

            }
        });

    }

    @Override
    public void ClickListener(View view, NewsHeadlines newsHeadlines) {
        intent = new Intent(getContext(), ScreenNewspaper.class);
        intent.putExtra("news", newsHeadlines);

        startActivity(intent);
    }
}