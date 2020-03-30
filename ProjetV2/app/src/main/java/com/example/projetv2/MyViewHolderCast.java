package com.example.projetv2;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.projetv2.ui.favoris.FavorisFragment;
import com.example.projetv2.ui.films.FilmFragment;
import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.List;

import modele.Cast;
import modele.Movie;

public class MyViewHolderCast extends RecyclerView.ViewHolder {

    private TextView title;
    private View newItemView;
    private ImageView imageView;
    private LikeButton button;
    private List<Cast> list;
    private int n;

    public MyViewHolderCast(View itemView, List<Cast> list) {
        super(itemView);
        this.list = list;
        this.newItemView = itemView;
        title = (TextView)itemView.findViewById(R.id.actor_name);
        imageView = (ImageView) itemView.findViewById(R.id.actor_image);


        //button = (Button) itemView.findViewById(R.id.ajouter);


        //button.setOnClickListener(this);
        /*button.setEventListener(new SparkEventListener() {
            @Override
            public void onEvent(ImageView button, boolean buttonState) {
                if (buttonState) {
                    // Button is active
                    addFavoris(button.getRootView());

                } else {
                    // Button is inactive
                }
            }

            @Override
            public void onEventAnimationEnd(ImageView button, boolean buttonState) {

            }

            @Override
            public void onEventAnimationStart(ImageView button, boolean buttonState) {

            }
        });*/



    }

    public void display(Cast cast){

        Log.i("azert","uiop");
       String name = cast.getName();
       title.setText(name);
        Glide.with(newItemView).load(cast.getProfile_path()).into(imageView);




    }






}
