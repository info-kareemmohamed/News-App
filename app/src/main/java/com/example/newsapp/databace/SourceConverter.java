package com.example.newsapp.databace;

import androidx.room.TypeConverter;

import com.example.newsapp.model.Source;

public class SourceConverter {
    @TypeConverter
    public static Source fromString(String value) {
        if (value == null) {
            return null;
        }

        String[] parts = value.split(",");
        if (parts.length == 2) {
            return new Source(parts[0], parts[1]);
        }

        return null;
    }

    @TypeConverter
    public static String toString(Source source) {
        if (source == null) {
            return null;
        }

        return source.getId() + "," + source.getName();
    }


}

