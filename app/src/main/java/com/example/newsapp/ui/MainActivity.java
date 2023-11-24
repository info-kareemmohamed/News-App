package com.example.newsapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import android.app.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.newsapp.R;
import com.example.newsapp.databinding.ActivityMainBinding;
import com.example.newsapp.fragments.FavoriteFragment;
import com.example.newsapp.fragments.HomeFragment;
import com.example.newsapp.fragments.SettingsFragment;

import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private ActivityMainBinding binding;

    private String category = "general";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Base_Theme_NewsApp);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        checkedDarkMode(sharedPreferences.getBoolean(SettingsFragment.NIGHTMODE, false));

        checkConfigurationChanges(savedInstanceState);

        listenerNavigationItemSelected();
        TabSelectedListener();
        SwiperefreshListener();
        SearchviewOnQueryTextListener();


    }


    private void checkConfigurationChanges(Bundle savedInstanceState) {//check Configuration Changes
        if (savedInstanceState == null) {
            replaceFragment(new HomeFragment());

        }
    }

    private void SwiperefreshListener() {
        binding.mainSwiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (getSupportFragmentManager().findFragmentById(R.id.main_FrameLayout) instanceof HomeFragment) {
                    replaceFragment(HomeFragment.newInstance(category, ""));
                } else {

                    replaceFragment(new FavoriteFragment());
                }
                binding.mainSwiperefresh.setRefreshing(false);
            }
        });

    }

    private void SearchviewOnQueryTextListener() {
        binding.mainSearchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                replaceFragment(HomeFragment.newInstance(category, newText));
                return true;
            }
        });

    }


    private void TabSelectedListener() {
        binding.mainTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.mainSearchview.setQuery("", false);
                binding.mainSearchview.clearFocus();
                category = tab.getText().toString();
                replaceFragment(HomeFragment.newInstance(category, ""));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


    private void listenerNavigationItemSelected() {
        binding.mainBottomnavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.menu_settings) {
                    binding.mainSwiperefresh.setEnabled(false);
                    binding.mainTab.setVisibility(View.GONE);
                    binding.mainSearchview.setVisibility(View.GONE);
                    replaceFragment(new SettingsFragment());
                } else if (item.getItemId() == R.id.menu_home) {
                    binding.mainSwiperefresh.setEnabled(true);
                    binding.mainTab.setVisibility(View.VISIBLE);
                    binding.mainSearchview.setVisibility(View.VISIBLE);
                    replaceFragment(new HomeFragment());
                } else {
                    binding.mainSwiperefresh.setEnabled(true);
                    binding.mainTab.setVisibility(View.GONE);
                    binding.mainSearchview.setVisibility(View.GONE);
                    replaceFragment(new FavoriteFragment());
                }
                return true;
            }
        });


    }


    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_FrameLayout, fragment);
        fragmentTransaction.commit();
    }

    private void checkedDarkMode(boolean nightmode) {//check Dark Mode
        if (nightmode)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        else
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {


        super.onSaveInstanceState(outState);
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.main_FrameLayout);

        if (currentFragment != null) {

            getSupportFragmentManager().putFragment(outState, "lastFragment", currentFragment);
        }

    }


    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);

        Fragment lastFragment = getSupportFragmentManager().getFragment(savedInstanceState, "lastFragment");


        if (lastFragment != null && lastFragment instanceof SettingsFragment) {
            binding.mainTab.setVisibility(View.GONE);
            binding.mainSearchview.setVisibility(View.GONE);
            replaceFragment(new SettingsFragment());
        }

    }


}
