package com.example.newsapp.api;

import com.example.newsapp.model.NewsApiResponse;


import io.reactivex.rxjava3.core.Single;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {
    @GET("top-headlines")
    public Single<NewsApiResponse> callHeadlines(@Query("country") String country, @Query("category") String category,
                                                 @Query("q") String query, @Query("apiKey") String api_key);
}
