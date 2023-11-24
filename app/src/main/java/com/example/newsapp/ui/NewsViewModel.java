package com.example.newsapp.ui;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.newsapp.api.RetrofitBuilder;
import com.example.newsapp.model.NewsApiResponse;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class NewsViewModel extends ViewModel {
    public MutableLiveData<NewsApiResponse> mutableLiveData = new MutableLiveData<>();
    private static final String TAG = "NEWS";


    public void getNews(String country, String category, String query) {
        Single<NewsApiResponse> single = RetrofitBuilder.getInstance().getNews(country, category, query).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        single.subscribe(o -> mutableLiveData.setValue(o), e -> Log.d(TAG, e.getMessage()));


    }

    public LiveData<NewsApiResponse> getItemsLiveData() {
        return mutableLiveData;
    }


}
