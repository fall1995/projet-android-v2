package com.example.projetv2.ui.films;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetv2.MovieDetails;
import com.example.projetv2.MoviePopularDetails;
import com.example.projetv2.R;
import com.example.projetv2.RecyclerViewAdapter;

import java.util.List;

import modele.Movie;
import modele.MovieCollection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import service.RetrofitClientInstance;
import service.TmdbService;

public class FilmFragment extends Fragment {

    private RecyclerView recyclerNowPlaying;
    private RecyclerView recyclerPopular;
    public List<Movie> movieNowPlaying;
    public  List<Movie> moviePopular;
    public static final String key = "1abe855bc465dce9287da07b08a664eb";
    public static final String NOM_FILM = "nomFilm";



    private FilmViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        fetchTmdbData();





        homeViewModel =
                ViewModelProviders.of(this).get(FilmViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);



        recyclerNowPlaying = root.findViewById(R.id.recycler);
        //  recyclerView.setHasFixedSize(true);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerNowPlaying.setLayoutManager(horizontalLayoutManager);

        recyclerPopular = root.findViewById(R.id.recycler2);
        //recyclerPopular.setHasFixedSize(true);
        LinearLayoutManager horizontalLayoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerPopular.setLayoutManager(horizontalLayoutManager2);




      /*  final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/





        return root;
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


                Toast.makeText(getActivity(),"movie selectionnée en position " + position, Toast.LENGTH_LONG).show();
                Intent movieClickActivity = new Intent(getActivity(), MovieDetails.class);

                Bundle b = new Bundle();
                Movie movie = (Movie) movieNowPlaying.get(position);
                String nomSelect = movie.getTitle();
                b.putString(NOM_FILM, nomSelect);
                b.putInt("pos",position);
                movieClickActivity.putExtras(b); //Put your id to your next Intent
                startActivity(movieClickActivity);

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
                Toast.makeText(getActivity()," en position " + position, Toast.LENGTH_LONG).show();
                Intent movieClickActivity = new Intent(getActivity(), MoviePopularDetails.class);

                Bundle b = new Bundle();
                Movie movie = (Movie) moviePopular.get(position);
                  String nomSelect = movie.getTitle();
                  b.putString(NOM_FILM, nomSelect);
                 b.putInt("pos",position);
                  movieClickActivity.putExtras(b); //Put your id to your next Intent
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

    public List<Movie> getMovieNowPlaying() {
        return movieNowPlaying;
    }

    public List<Movie> getMoviePopular() {
        return moviePopular;
    }
}