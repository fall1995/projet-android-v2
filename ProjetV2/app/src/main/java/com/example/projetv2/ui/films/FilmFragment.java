package com.example.projetv2.ui.films;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetv2.MovieDetails;
import com.example.projetv2.MovieToutAfficher;
import com.example.projetv2.R;
import com.example.projetv2.RecyclerViewAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import modele.Movie;
import modele.MovieCollection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import service.RetrofitClientInstance;
import service.TmdbService;

public class FilmFragment extends Fragment {

    public static RecyclerView recyclerNowPlaying;
    public static RecyclerView recyclerPopular;
    public static RecyclerView recyclerupComming;
    public static List<Movie> movieUpComing;
    public static List<Movie> movieNowPlaying;
    public  static List<Movie> moviePopular;
    public static List<Movie> listFavoris ;
    public static final String key = "1abe855bc465dce9287da07b08a664eb";
    public static final String UPCOMING = "upcoming";
    public static final String POPULAR = "popular";
    public static final String NOW = "now";
    public Button button ;
    public Button button2 ;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_films, container, false);
        recyclerNowPlaying = root.findViewById(R.id.recycler);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerNowPlaying.setLayoutManager(horizontalLayoutManager);
        recyclerPopular = root.findViewById(R.id.recycler2);
        LinearLayoutManager horizontalLayoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerPopular.setLayoutManager(horizontalLayoutManager2);
//        recyclerupComming = root.findViewById(R.id.recycler3);
    //    LinearLayoutManager horizontalLayoutManager3 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
//        recyclerupComming.setLayoutManager(horizontalLayoutManager2);

        loadData();


        if(moviePopular == null && movieNowPlaying == null){
            fetchTmdbData();
        }else {
            startRecyclerNowPlaying();
            startRecyclerPopular();
          //  startRecyclerUpComing();

        }

        Button button = (Button) root.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), MovieToutAfficher.class);
                Bundle b = new Bundle();
                b.putString(NOW,NOW);
              //  b.putInt("pop",1);
                myIntent.putExtras(b);
                startActivity(myIntent);

            }
        });

        Button button2 = (Button) root.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), MovieToutAfficher.class);
                Bundle b = new Bundle();
                b.putString(POPULAR,POPULAR);
                myIntent.putExtras(b);
                startActivity(myIntent);

            }
        });

        Button button3 = (Button) root.findViewById(R.id.button3);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), MovieToutAfficher.class);
                Bundle b = new Bundle();
                b.putString(UPCOMING,UPCOMING);
                myIntent.putExtras(b);
                startActivity(myIntent);

            }
        });


       /* Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

    listMovie = movieNowPlaying;
  //  ajouterList(moviePopular);
    listMovie.addAll(moviePopular);
                  // Log.i("log", "taille"+listMovie.size()  );
            }
        },3000);*/








        return root;
    }


    private void fetchTmdbData(){
        TmdbService tmdbService = RetrofitClientInstance.getlnstance().create(TmdbService.class);

        tmdbService.getMoviesNowPlaying(key).enqueue(new Callback<MovieCollection>() {
            @Override
            public void onResponse(Call<MovieCollection> call, Response<MovieCollection> response) {

                movieNowPlaying =response.body().getMovieList();
                startRecyclerNowPlaying();
              //  Log.i("log", "taille"+movieNowPlaying.size()  );
                 //
                //
             //   Log.i("id", "id "+  movieNowPlaying.get(0).getId()   );


            }

            @Override
            public void onFailure(Call<modele.MovieCollection> call, Throwable t) {
                Log.i("toutafficher","marche paaaaas");
            }

        });



        tmdbService.getMoviesPopular(key).enqueue(new Callback<MovieCollection>() {
            @Override
            public void onResponse(Call<MovieCollection> call, Response<MovieCollection> response) {
             //   Log.i("log4", "rattatata"  );
                moviePopular =response.body().getMovieList();
               // Log.i("log45", "taille"+moviePopular.size()  );
                //ajouterList(moviePopular);



               startRecyclerPopular();
            }

            @Override
            public void onFailure(Call<modele.MovieCollection> call, Throwable t) {
                Log.i("test2","nooopeeee");
            }
        });

        tmdbService.getMoviesUpcoming(key).enqueue(new Callback<MovieCollection>() {
            @Override
            public void onResponse(Call<MovieCollection> call, Response<MovieCollection> response) {
                //   Log.i("log4", "rattatata"  );
                movieUpComing =response.body().getMovieList();
                // Log.i("log45", "taille"+moviePopular.size()  );
                //ajouterList(moviePopular);



              //  startRecyclerUpComing();
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


            //    Toast.makeText(getActivity(),"movie selectionnée en position " + position, Toast.LENGTH_LONG).show();
                Intent movieClickActivity = new Intent(getActivity(), MovieDetails.class);

                Bundle b = new Bundle();
                Movie movie = (Movie) movieNowPlaying.get(position);
                String nomSelect = movie.getTitle();
                b.putString(NOW, NOW);
                b.putInt("pos",position);
                movieClickActivity.putExtras(b); //Put your id to your next Intent
                startActivity(movieClickActivity);

            }
        },100);
        recyclerNowPlaying.setAdapter(recyclerViewAdapter);
        recyclerNowPlaying.setTag(recyclerViewAdapter);

        recyclerNowPlaying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Log.i("click","je viens de cliquer sur ..");
            }
        });
    }

    private void startRecyclerPopular(){
        final RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(moviePopular, new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
               // Toast.makeText(getActivity()," en position " + position, Toast.LENGTH_LONG).show();
                Intent movieClickActivity = new Intent(getActivity(), MovieDetails.class);

                Bundle b = new Bundle();
                Movie movie = (Movie) moviePopular.get(position);
                  String nomSelect = movie.getTitle();
                  b.putString(POPULAR, POPULAR);
                 b.putInt("pos",position);
                  movieClickActivity.putExtras(b); //Put your id to your next Intent
                startActivity(movieClickActivity);
                //finish();
            }
        },200);
        recyclerPopular.setAdapter(recyclerViewAdapter);

        recyclerPopular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Log.i("click","je viens de cliquer sur ..");
            }
        });
    }

    private void  loadData(){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("shared preferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list",null);
        Type type = new TypeToken< ArrayList<Movie>>() {} .getType();
        FilmFragment.listFavoris = gson.fromJson(json,type);
        if (FilmFragment.listFavoris == null){
            FilmFragment.listFavoris = new ArrayList<>();
        }

    }

    public void addFavoris (View v){
        Log.i("log21","ajouter au favoris");
    }
    public List<Movie> getMovieNowPlaying() {
        return movieNowPlaying;
    }

    public List<Movie> getMoviePopular() {
        return moviePopular;
    }
}