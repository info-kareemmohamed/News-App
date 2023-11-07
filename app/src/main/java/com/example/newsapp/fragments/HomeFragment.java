package com.example.newsapp.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.newsapp.R;
import com.example.newsapp.databinding.FragmentHomeBinding;
import com.example.newsapp.model.NewsApiResponse;
import com.example.newsapp.model.NewsHeadlines;
import com.example.newsapp.recyclerview.RecyclerAdapter;
import com.example.newsapp.ui.NewsViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private NewsViewModel viewModel;


    private RecyclerAdapter adapter;

    private List<NewsHeadlines> list = new ArrayList<>();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
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
        adapter = new RecyclerAdapter(getContext(), list);
        binding.mainRecyclerview.setAdapter(adapter);
        binding.mainRecyclerview.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.mainRecyclerview.setHasFixedSize(true);

    }


    private void setupViewModel() {
        viewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        viewModel.getNews("us", "general", null);
    }
    private void setupNewsDataObservation() {
        viewModel.mutableLiveData.observe(this, new Observer<NewsApiResponse>() {
            @Override
            public void onChanged(NewsApiResponse newsApiResponse) {

                adapter.setList(newsApiResponse.getArticles());

            }
        });

    }

}