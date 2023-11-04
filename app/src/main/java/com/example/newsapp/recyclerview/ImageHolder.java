package com.example.newsapp.recyclerview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.newsapp.R;
import com.example.newsapp.model.NewsHeadlines;
import com.squareup.picasso.Picasso;

public class ImageHolder extends RecyclerView.ViewHolder {

    private CardView cardView;
    private TextView titel, description;
    private ImageView imageView;


    public ImageHolder(@NonNull View itemView) {
        super(itemView);
        titel = itemView.findViewById(R.id.card_titly);
        description = itemView.findViewById(R.id.card_Source);
        imageView = itemView.findViewById(R.id.card_imageView);

    }


    public void bind(NewsHeadlines item) {
        titel.setText(item.getTitle());
        description.setText(item.getSource().getName());
        if (item.getUrlToImage() != null)
            Picasso.get().load(item.getUrlToImage()).into(imageView);

    }


}


