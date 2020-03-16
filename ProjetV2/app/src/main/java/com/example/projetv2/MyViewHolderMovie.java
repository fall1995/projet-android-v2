package com.example.projetv2;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import modele.Movie;

public class MyViewHolderMovie extends RecyclerView.ViewHolder {

    private TextView title;
    private View newItemView;
    private ImageView imageView;

    public MyViewHolderMovie(View itemView) {
        super(itemView);
        this.newItemView = itemView;
        title = (TextView)itemView.findViewById(R.id.item_title);
        imageView = (ImageView) itemView.findViewById(R.id.item_image);


    }

    public void display(Movie movie){
       /* String name = feature.getProperties().getName();
        String rue = feature.getProperties().getStreet();
        String codePostal = ""+feature.getProperties().getPostalCode();
        String ville = feature.getProperties().getCity();
        String adresse = rue + "\n" + codePostal + " - " + ville;

*/
       String name = movie.getTitle();
       title.setText(name);
        Glide.with(newItemView).load(movie.getImage()).into(imageView);

    }


}