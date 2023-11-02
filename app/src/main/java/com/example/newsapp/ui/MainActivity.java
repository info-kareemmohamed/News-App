package com.example.newsapp.ui;

import androidx.appcompat.app.AppCompatActivity;




import android.os.Bundle;
import android.widget.Toast;

import com.example.newsapp.R;



public class MainActivity extends AppCompatActivity {

   private NewsViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}