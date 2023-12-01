package com.example.newsapp.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.newsapp.model.NewsHeadlines;
import com.example.newsapp.repository.News_Repository;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavoriteViewModel extends AndroidViewModel {
    private News_Repository repository;
    private MutableLiveData<Boolean> isUrlExistsLiveData = new MutableLiveData<>();

    public LiveData<Boolean> getIsUrlExistsLiveData() {
        return isUrlExistsLiveData;
    }

    public FavoriteViewModel(@NonNull Application application) {
        super(application);
        repository = new News_Repository(application);
    }


    public void add_news(NewsHeadlines... news) {
        repository.add_news(news);
    }

    public void delete_news(NewsHeadlines... news) {
        repository.delete_news(news);
    }

    public void delete_news(String url) {
        repository.delete_news(url);
    }

    public LiveData<List<NewsHeadlines>> get_all_news() {
        return repository.get_all_news();

    }

    public void checkIfUrlExists(String url) {
        repository.isUrlExists(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(isExists -> {
                    isUrlExistsLiveData.postValue(isExists);
                });
    }


}
