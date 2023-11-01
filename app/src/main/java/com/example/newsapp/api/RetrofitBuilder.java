package com.example.newsapp.api;

import com.example.newsapp.model.NewsApiResponse;


import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {
private  final String BASE_URL="https://newsapi.org/v2/";
private  final String API_KEY="c70f2fc7f39144b880948cd1bb2792e6";

private  NewsApi newsApi;
private static  RetrofitBuilder Instance;

private RetrofitBuilder(){
    Retrofit retrofit=new Retrofit.Builder().
            baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build();
    newsApi =retrofit.create(NewsApi.class);
}

public static RetrofitBuilder getInstance(){
    if(Instance==null){
        Instance=new RetrofitBuilder();
    }
return Instance;
}

public Single<NewsApiResponse> getNews(String country ,  String category,String query){
    return newsApi.callHeadlines(country,category,query,API_KEY);
}


}
