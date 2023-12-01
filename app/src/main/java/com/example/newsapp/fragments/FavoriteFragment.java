package com.example.newsapp.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.newsapp.R;
import com.example.newsapp.databinding.FragmentFavoriteBinding;
import com.example.newsapp.model.NewsHeadlines;
import com.example.newsapp.recyclerview.RecyclerAdapter;
import com.example.newsapp.recyclerview.RecyclerListener;
import com.example.newsapp.ui.FavoriteViewModel;
import com.example.newsapp.ui.ScreenNewspaper;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;


public class FavoriteFragment extends Fragment implements RecyclerListener {

    private FragmentFavoriteBinding binding;
    private FavoriteViewModel viewModel;
    private Intent intent;
    private RecyclerAdapter adapter;
    private List<NewsHeadlines> favoritelist = new ArrayList<>();


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public FavoriteFragment() {
        // Required empty public constructor
    }


    public static FavoriteFragment newInstance(String param1, String param2) {
        FavoriteFragment fragment = new FavoriteFragment();
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
        binding = FragmentFavoriteBinding.inflate(inflater, container, false);
        setupRecycler();
        setupViewModel();
        setupNewsDataObservation();
        swipe();
        return binding.getRoot();
    }


    private void setupRecycler() {
        adapter = new RecyclerAdapter(getContext(), favoritelist, this);
        binding.FavoriteRecyclerview.setAdapter(adapter);
        binding.FavoriteRecyclerview.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.FavoriteRecyclerview.setHasFixedSize(true);

    }


    private void setupViewModel() {
        viewModel = new ViewModelProvider(this).get(FavoriteViewModel.class);

    }


    private void setupNewsDataObservation() {
        viewModel.get_all_news().observe(getActivity(), new Observer<List<NewsHeadlines>>() {
            @Override
            public void onChanged(List<NewsHeadlines> list) {
                if (list.size() > 0) {
                    binding.lottieAnimationView.setVisibility(View.GONE);
                } else {
                    binding.lottieAnimationView.setVisibility(View.VISIBLE);
                }
                adapter.setList(list);
                favoritelist = list;

            }
        });

    }

    @Override
    public void ClickListener(View view, NewsHeadlines newsHeadlines) {
        intent = new Intent(getContext(), ScreenNewspaper.class);
        intent.putExtra("news", newsHeadlines);
        startActivity(intent);
    }


    private void swipe() {

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                NewsHeadlines deletedItem = adapter.getNewsPosition(position);

                //Delete the item from the list
                favoritelist.remove(position);
                adapter.notifyItemRemoved(position);

                String textsnackbar = getString(R.string.swipeSnackbarDelete);
                String undo = getString(R.string.swipeSnackbarUndo);

                //Display a Snackbar for Undo
                Snackbar snackbar = Snackbar.make(binding.FavoriteRecyclerview, textsnackbar, Snackbar.LENGTH_LONG);
                snackbar.setAction(undo, view -> {
                    //Return the deleted item to the list
                    favoritelist.add(position, deletedItem);
                    adapter.notifyItemInserted(position);

                });

                snackbar.addCallback(new Snackbar.Callback() {// In this code, we add a callback to the Snackbar to handle dismissal events.
                    @Override
                    public void onDismissed(Snackbar snackbar, int event) {   // If the dismissal event is not due to clicking the "Undo" button.
                        if (event != DISMISS_EVENT_ACTION) {
                            // Here, we delete the deleted item using the model.
                            viewModel.delete_news(deletedItem);
                        }
                    }
                });
                snackbar.show();// Then, we display the Snackbar.
                adapter.setList(favoritelist);// Finally, we update the list of items in the adapter using the Main_list.
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(binding.FavoriteRecyclerview);
    }
}