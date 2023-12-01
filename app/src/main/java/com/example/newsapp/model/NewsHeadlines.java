package com.example.newsapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.newsapp.databace.SourceConverter;

@Entity
@TypeConverters({SourceConverter.class})
public class NewsHeadlines implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private Source source;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String content;
    private boolean favorite;


    public NewsHeadlines() {
    }

    public NewsHeadlines(long id, Source source, String author, String title, String description, String url, String urlToImage, String publishedAt, String content, boolean favorite) {
        this.id = id;
        this.source = source;
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;
        this.favorite = favorite;
    }

    public NewsHeadlines(Source source, String author, String title, String description, String url, String urlToImage, String publishedAt, String content, boolean favorite) {
        this.source = source;
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;
        this.favorite = favorite;
    }


    protected NewsHeadlines(Parcel in) {
        id = in.readLong();
        source = in.readParcelable(Source.class.getClassLoader());
        author = in.readString();
        title = in.readString();
        description = in.readString();
        url = in.readString();
        urlToImage = in.readString();
        publishedAt = in.readString();
        content = in.readString();
        favorite = in.readByte() != 0;
    }

    public static final Creator<NewsHeadlines> CREATOR = new Creator<NewsHeadlines>() {
        @Override
        public NewsHeadlines createFromParcel(Parcel in) {
            return new NewsHeadlines(in);
        }

        @Override
        public NewsHeadlines[] newArray(int size) {
            return new NewsHeadlines[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeParcelable(source, flags);
        dest.writeString(author);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(url);
        dest.writeString(urlToImage);
        dest.writeString(publishedAt);
        dest.writeString(content);
        dest.writeByte((byte) (favorite ? 1 : 0));
    }
}
