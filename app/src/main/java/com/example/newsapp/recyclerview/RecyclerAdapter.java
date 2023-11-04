package com.example.newsapp.recyclerview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.newsapp.R;
import com.example.newsapp.model.NewsHeadlines;
import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter<ImageHolder> {
    private List<NewsHeadlines> list;
    private Context context;


    public RecyclerAdapter(Context context, List<NewsHeadlines> list) {
        this.context = context;
        this.list = list;
    }



    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            return new ImageHolder(LayoutInflater.from(context).inflate(R.layout.carditemimage, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ImageHolder holder, int position) {
       if (!list.get(position).getTitle().equals("[Removed]"))
            holder.bind(list.get(position));
        else
            list.remove(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public List<NewsHeadlines> getList() {
        return list;
    }

    public void setList(List<NewsHeadlines> list) {
        this.list = list;
        notifyDataSetChanged();
    }

}
