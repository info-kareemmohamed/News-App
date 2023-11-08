package com.example.newsapp.databace;

import androidx.room.TypeConverter;

import com.example.newsapp.model.Source;

public class SourceConverter {
    @TypeConverter
    public static Source getSource(String name) {

        return name != null ? new Source(name, name) : null;
    }

    @TypeConverter
    public static String FormSource(Source source) {

        return source.getName();
    }
}
