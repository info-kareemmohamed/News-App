package com.example.newsapp.recyclerview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;
import com.example.newsapp.R;
import com.example.newsapp.model.NewsHeadlines;


import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter<ImageHolder> {
    private List<NewsHeadlines> list;
    private Context context;
    private RecyclerListener listener;


    public RecyclerAdapter(Context context, List<NewsHeadlines> list,RecyclerListener listener) {
        this.context = context;
        this.list = list;
        this.listener=listener;
    }



    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            return new ImageHolder(LayoutInflater.from(context).inflate(R.layout.carditemimage, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ImageHolder holder, int position) {
        holder.bind(list.get(position));
        holder.CardView_Recyclerview_Listener(listener);
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
