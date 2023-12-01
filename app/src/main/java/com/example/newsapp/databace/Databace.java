package com.example.newsapp.databace;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.newsapp.Dao.NewsDao;
import com.example.newsapp.model.NewsHeadlines;

@Database(entities = {NewsHeadlines.class}, version = 1)
public abstract class Databace extends RoomDatabase {
    public abstract NewsDao dao();

    private static volatile Databace Instance;

    public static synchronized Databace getInstance(Context context) {
        if (Instance == null) {
            Instance = Room.databaseBuilder(context.getApplicationContext(), Databace.class, "NewsApp").fallbackToDestructiveMigration().build();
        }

        return Instance;
    }


}

