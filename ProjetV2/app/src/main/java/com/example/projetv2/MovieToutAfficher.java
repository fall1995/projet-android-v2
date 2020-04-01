package com.example.projetv2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetv2.ui.favoris.FavorisFragment;
import com.example.projetv2.ui.films.FilmFragment;
import com.example.projetv2.ui.recherche.RechercheFragment;

import modele.Movie;

public class MovieToutAfficher extends AppCompatActivity {
    private final String APIYoutube = "AIzaSyBbf-y_8UUB7AYcqkvHSbE_fJ7GVdIzcxw";
    public static final String key = "1abe855bc465dce9287da07b08a664eb";


    private int pos;
    private RecyclerView recyclerView;
    public RecyclerViewAdapter recyclerViewAdapter;
    private String listPopular="";
    private String listFavoris="";
    private String listNow="";
    private String listsearch="";
    private String listUpComing="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.toutafficher);

        recyclerView = findViewById(R.id.recyclerToutAfficher);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        uploadFeature();



    }

    private void uploadFeature() {
        Bundle b = getIntent().getExtras();


        if(b != null){
            listPopular= b.getString(FilmFragment.POPULAR);
            listNow= b.getString(FilmFragment.NOW);
            listsearch= b.getString(RechercheFragment.SEARCH);
            listFavoris= b.getString(FavorisFragment.FAVORIS);
            listUpComing= b.getString(FilmFragment.UPCOMING);



        }
        if (listPopular!=null && listPopular.equals(FilmFragment.POPULAR) ) {
startRecyclerPopular();

        }else if ( listNow!=null && listNow.equals(FilmFragment.NOW)) {
          //Log.i("call","of");
            startRecyclerNow();
        }else if (listsearch!=null && listsearch.equals(RechercheFragment.SEARCH)  ) {

            // Log.i("skulurt", selectedMovie.getTitle());
        }else if (listUpComing!=null && listUpComing.equals(FilmFragment.UPCOMING)  ) {
                startRecyclerUpComing();

        }

    //    setDetails();


    }




    public void startRecyclerNow(){

        recyclerViewAdapter = new RecyclerViewAdapter(FilmFragment.movieNowPlaying, new RecyclerViewAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {

             //   Toast.makeText(MovieToutAfficher.this,"movie selectionnée en position " + position, Toast.LENGTH_LONG).show();
                Intent movieClickActivity = new Intent(MovieToutAfficher.this, MovieDetails.class);
                Bundle b = new Bundle();
                Movie movie = (Movie) FilmFragment.movieNowPlaying.get(position);
                String nomSelect = movie.getTitle();
                b.putString(FilmFragment.NOW, FilmFragment.NOW);
                b.putInt("pos",position);
                movieClickActivity.putExtras(b); //Put your id to your next Intent
                startActivity(movieClickActivity);

            }
        },2);
        recyclerView.setAdapter(recyclerViewAdapter);

        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("click","je viens de cliquer sur ..");
            }
        });

    }


    public void startRecyclerPopular(){

        recyclerViewAdapter = new RecyclerViewAdapter(FilmFragment.moviePopular, new RecyclerViewAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {

                //   Toast.makeText(MovieToutAfficher.this,"movie selectionnée en position " + position, Toast.LENGTH_LONG).show();
                Intent movieClickActivity = new Intent(MovieToutAfficher.this, MovieDetails.class);
                Bundle b = new Bundle();
                Movie movie = (Movie) FilmFragment.moviePopular.get(position);
                String nomSelect = movie.getTitle();
                b.putString(FilmFragment.NOW, FilmFragment.NOW);
                b.putInt("pos",position);
                movieClickActivity.putExtras(b); //Put your id to your next Intent
                startActivity(movieClickActivity);

            }
        },2);
        recyclerView.setAdapter(recyclerViewAdapter);

        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("click","je viens de cliquer sur ..");
            }
        });

    }

    public void startRecyclerUpComing(){

        recyclerViewAdapter = new RecyclerViewAdapter(FilmFragment.movieUpComing, new RecyclerViewAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {

                //   Toast.makeText(MovieToutAfficher.this,"movie selectionnée en position " + position, Toast.LENGTH_LONG).show();
                Intent movieClickActivity = new Intent(MovieToutAfficher.this, MovieDetails.class);
                Bundle b = new Bundle();
                Movie movie = (Movie) FilmFragment.movieUpComing.get(position);
                String nomSelect = movie.getTitle();
                b.putString(FilmFragment.NOW, FilmFragment.NOW);
                b.putInt("pos",position);
                movieClickActivity.putExtras(b); //Put your id to your next Intent
                startActivity(movieClickActivity);

            }
        },2);
        recyclerView.setAdapter(recyclerViewAdapter);

        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("click","je viens de cliquer sur ..");
            }
        });

    }


}
