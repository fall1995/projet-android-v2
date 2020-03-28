package com.example.projetv2;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.like.LikeButton;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.projetv2.ui.favoris.FavorisFragment;
import com.example.projetv2.ui.films.FilmFragment;

import com.like.OnLikeListener;
import com.varunest.sparkbutton.SparkButton;
import com.varunest.sparkbutton.SparkEventListener;

import java.util.List;

import modele.Movie;

public class MyViewHolderMovie extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView title;
    private View newItemView;
    private ImageView imageView;
    private LikeButton button;
    private List<Movie> list;
    private int n;

    public MyViewHolderMovie(View itemView, List<Movie> list) {
        super(itemView);
        this.list = list;
        this.newItemView = itemView;
        title = (TextView)itemView.findViewById(R.id.item_title);
        imageView = (ImageView) itemView.findViewById(R.id.item_image);


        //button = (Button) itemView.findViewById(R.id.ajouter);
        button = (LikeButton) itemView.findViewById(R.id.spark_button);

        button.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                addFavoris(likeButton.getRootView());
            }

            @Override
            public void unLiked(LikeButton likeButton) {
            supFavoris(likeButton.getRootView());
            }
        });
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

    public void display(Movie movie){
       /* String name = feature.getProperties().getName();
        String rue = feature.getProperties().getStreet();
        String codePostal = ""+feature.getProperties().getPostalCode();
        String ville = feature.getProperties().getCity();
        String adresse = rue + "\n" + codePostal + " - " + ville;

*/
       int c=0;
       String name = movie.getTitle();
       title.setText(name);
        Glide.with(newItemView).load(movie.getImage()).into(imageView);



       // Log.i("movdsiplay","moovie="+movie.getTitle());
        for (int i=0;i<FilmFragment.listFavoris.size();i++){
            if (FilmFragment.listFavoris.get(i).getTitle().equals(name)){
                c++;
            }
        }
        if (c>0){
         //   Log.i("bingodesbengos","enfiin");
            button.setLiked(true);
        }else{
            button.setLiked(false);
        }
    }


    @Override
    public void onClick(View v) {
            addFavoris(v);
    }

    public void addFavoris (View v)
    {


       FilmFragment.listFavoris.add(list.get(this.getAdapterPosition()));
        Log.i("add","tailleListe "+FilmFragment.listFavoris.size());
        FilmFragment.recyclerNowPlaying.getAdapter().notifyDataSetChanged();
        FilmFragment.recyclerPopular.getAdapter().notifyDataSetChanged();

    }
    public void supFavoris(View v){

        for (int i=0;i<FilmFragment.listFavoris.size();i++){
            Log.i("supp","tailleListeavantsupp "+FilmFragment.listFavoris.size());

            Log.i("testi","i = "+i);
            Log.i(" listadapter","t = "+ this.getLayoutPosition());

            if (FilmFragment.listFavoris.get(i).getTitle().equals(list.get(this.getLayoutPosition()).getTitle())){
                FilmFragment.listFavoris.remove(i);
                //notifyDataSetChanged();

            }
            Log.i("supp2","tailleListeApressupp "+FilmFragment.listFavoris.size());

            if (FavorisFragment.recyclerFavoris != null) {
                FavorisFragment.recyclerFavoris.getAdapter().notifyDataSetChanged();

            }

            FilmFragment.recyclerNowPlaying.getAdapter().notifyDataSetChanged();
            FilmFragment.recyclerPopular.getAdapter().notifyDataSetChanged();
        }

    }


}
