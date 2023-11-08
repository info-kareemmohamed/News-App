package com.example.newsapp.recyclerview;

import android.view.View;

import com.example.newsapp.model.NewsHeadlines;

public interface RecyclerListener {
    public void ClickListener(View view, NewsHeadlines newsHeadlines);
}
