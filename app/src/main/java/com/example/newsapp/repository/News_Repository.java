package com.example.newsapp.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.newsapp.Dao.NewsDao;
import com.example.newsapp.databace.Databace;
import com.example.newsapp.model.NewsHeadlines;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class News_Repository {
    private Databace Instance;
    private NewsDao dao;

    public News_Repository(Application application) {
        Instance = Databace.getInstance(application);
        dao = Instance.dao();
    }


    public void add_news(NewsHeadlines... news) {
        dao.add_news(news).subscribeOn(Schedulers.io()).subscribe();
    }

    public void delete_news(NewsHeadlines... news) {
        dao.delete_news(news)
                .subscribeOn(Schedulers.io()).subscribe();


    }

    public void delete_news(String url) {

        dao.delete_news(url)
                .subscribeOn(Schedulers.io()).subscribe();
    }

    public LiveData<List<NewsHeadlines>> get_all_news() {
        return dao.get_all_news();

    }


    public Single<Boolean> isUrlExists(String url) {
        return dao.checkIfUrlExists(url)
                .map(count -> count > 0);
    }
}
