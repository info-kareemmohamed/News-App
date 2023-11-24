package com.example.newsapp.Dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import com.example.newsapp.model.NewsHeadlines;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface NewsDao {

    @Insert()
    public Completable add_news(NewsHeadlines... news);

    @Delete
    public Completable delete_news(NewsHeadlines... news);
    @Query("delete  from NewsHeadlines where url=:url")
    public Completable  delete_news(String url);

    @Query("SELECT COUNT(*) FROM newsheadlines WHERE url = :url")
    public Single<Integer> checkIfUrlExists(String url);


    @Query("select * from NewsHeadlines")
    public LiveData<List<NewsHeadlines>> get_all_news();

}
