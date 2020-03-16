package com.example.projetv2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import modele.Movie;
import modele.MovieCollection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import service.RetrofitClientInstance;
import service.TmdbService;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerNowPlaying;
    private RecyclerView recyclerPopular;
    public List<Movie> movieNowPlaying;
    public  List<Movie> moviePopular;
    public static final String key = "1abe855bc465dce9287da07b08a664eb";
    public static final String NOM_FILM = "nomFilm";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

       // fetchTmdbData();

    }

    private void fetchTmdbData(){
        TmdbService tmdbService = RetrofitClientInstance.getlnstance().create(TmdbService.class);

        tmdbService.getMoviesNowPlaying(key).enqueue(new Callback<MovieCollection>() {
            @Override
            public void onResponse(Call<MovieCollection> call, Response<MovieCollection> response) {
                Log.i("log", "gjfgjhf,fhfffh,,fhf,h"  );
                movieNowPlaying =response.body().getMovieList();
                //   Log.i("log", movieNowPlaying.get(0).getTitle()   );

                startRecyclerNowPlaying();
            }

            @Override
            public void onFailure(Call<modele.MovieCollection> call, Throwable t) {
                Log.i("test","marche paaaaas");
            }
        });

        tmdbService.getMoviesPopular(key).enqueue(new Callback<MovieCollection>() {
            @Override
            public void onResponse(Call<MovieCollection> call, Response<MovieCollection> response) {
                Log.i("log4", "rattatata"  );
                moviePopular =response.body().getMovieList();
                //   Log.i("log", movieNowPlaying.get(0).getTitle()   );

                startRecyclerPopular();
            }

            @Override
            public void onFailure(Call<modele.MovieCollection> call, Throwable t) {
                Log.i("test2","nooopeeee");
            }
        });


    }


    private void startRecyclerNowPlaying(){
        final RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(movieNowPlaying, new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getApplicationContext(),"movie selectionnée en position " + position, Toast.LENGTH_LONG).show();
                Intent movieClickActivity = new Intent(getApplicationContext(), MovieDetails.class);

                Bundle b = new Bundle();
                Movie movie = (Movie) movieNowPlaying.get(position);
                String nomSelect = movie.getTitle();
                b.putString(NOM_FILM, nomSelect);
                b.putInt("pos",position);
                movieClickActivity.putExtras(b); //Put your id to your next Intent
                startActivity(movieClickActivity);
                //finish();
            }
        });
        recyclerNowPlaying.setAdapter(recyclerViewAdapter);

        recyclerNowPlaying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("click","je viens de cliquer sur ..");
            }
        });
    }

    private void startRecyclerPopular(){
        final RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(moviePopular, new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getApplicationContext()," en position " + position, Toast.LENGTH_LONG).show();
                Intent movieClickActivity = new Intent(getApplicationContext(), MovieDetails.class);

                Bundle b = new Bundle();
                Movie movie = (Movie) moviePopular.get(position);
                //  String nomSelect = movie.getTitle();
                //  b.putString(NOM_FILM, nomSelect);
                //  b.putInt("pos",position);
                //   movieClickActivity.putExtras(b); //Put your id to your next Intent
                startActivity(movieClickActivity);
                //finish();
            }
        });
        recyclerPopular.setAdapter(recyclerViewAdapter);

        recyclerPopular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("click","je viens de cliquer sur ..");
            }
        });
    }

}
