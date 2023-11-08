package com.example.newsapp.Dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.newsapp.model.NewsHeadlines;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

@Dao
public interface NewsDao {

    @Insert
    public Completable add_note(NewsHeadlines... news);

    @Delete
    public Completable delete_note(NewsHeadlines... news);


    @Query("select * from NewsHeadlines")
    public LiveData<List<NewsHeadlines>> get_all_news();


}
