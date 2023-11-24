package com.example.newsapp.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.newsapp.R;
import com.example.newsapp.databinding.FragmentSettingsBinding;


public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    public static final String NIGHTMODE = "nightmode";
    private boolean nightmode;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public SettingsFragment() {
        // Required empty public constructor
    }


    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
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
        binding = FragmentSettingsBinding.inflate(inflater, container, false);


        sharedPreferences = getActivity().getSharedPreferences("MODE", Context.MODE_PRIVATE);

        nightmode = sharedPreferences.getBoolean(NIGHTMODE, false);

        checkedModetoSelectedOnSwitchcompat();

        OnClickListenerModeOnSwitchcompat();
        return binding.getRoot();
    }


    private void checkedModetoSelectedOnSwitchcompat() {
        if (nightmode) {
            binding.settingSwitchcompat.setChecked(true);

        } else
            binding.settingSwitchcompat.setChecked(false);
    }


    private void OnClickListenerModeOnSwitchcompat() {

        binding.settingSwitchcompat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor = sharedPreferences.edit();
                if (nightmode) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                    editor.putBoolean(NIGHTMODE, false);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

                    editor.putBoolean(NIGHTMODE, true);
                }

                editor.apply();
            }
        });


    }


}