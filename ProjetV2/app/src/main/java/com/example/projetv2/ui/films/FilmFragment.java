package com.example.projetv2.ui.films;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetv2.MovieNowPlayingDetails;
import com.example.projetv2.MoviePopularDetails;
import com.example.projetv2.R;
import com.example.projetv2.RecyclerViewAdapter;

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
    public static List<Movie> listMovie; //liste avec tous les films de toutes les categories
    public static List<Movie> movieNowPlaying;
    public  static List<Movie> moviePopular;
    public static List<Movie> listFavoris = new ArrayList<>();
    public static final String key = "1abe855bc465dce9287da07b08a664eb";
    public static final String NOM_FILM = "nomFilm";
    private FilmViewModel homeViewModel;
    public Button button ;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



                fetchTmdbData();


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




       // homeViewModel =
             //   ViewModelProviders.of(this).get(FilmViewModel.class);
        View root = inflater.inflate(R.layout.fragment_films, container, false);


        recyclerNowPlaying = root.findViewById(R.id.recycler);
        //  recyclerFavoris.setHasFixedSize(true);
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

                movieNowPlaying =response.body().getMovieList();
              //  Log.i("log", "taille"+movieNowPlaying.size()  );
                 // Log.i("log", movieNowPlaying.get(0).getTitle()   );

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


    }


    private void startRecyclerNowPlaying(){
        final RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(movieNowPlaying, new RecyclerViewAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {


                Toast.makeText(getActivity(),"movie selectionnée en position " + position, Toast.LENGTH_LONG).show();
                Intent movieClickActivity = new Intent(getActivity(), MovieNowPlayingDetails.class);

                Bundle b = new Bundle();
                Movie movie = (Movie) movieNowPlaying.get(position);
                String nomSelect = movie.getTitle();
                b.putString(NOM_FILM, nomSelect);
                b.putInt("pos",position);
                movieClickActivity.putExtras(b); //Put your id to your next Intent
                startActivity(movieClickActivity);

            }
        },0);
        recyclerNowPlaying.setAdapter(recyclerViewAdapter);
        recyclerNowPlaying.setTag(recyclerViewAdapter);

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
        },0);
        recyclerPopular.setAdapter(recyclerViewAdapter);

        recyclerPopular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("click","je viens de cliquer sur ..");
            }
        });
    }

    public void ajouterList (List<Movie> movieList){
        int c =0;
       // Log.i("log46", "taille"+listMovie.size()  );
            for(int i=0; i<movieList.size();i++){
                for (int j=0; j<listMovie.size();j++){
                    if(movieList.get(i).getTitle() == listMovie.get(j).getTitle()){
                      c ++;
                    }
                    if(c ==0){
                        listMovie.add(movieList.get(i));
                    }
                    c =0;
                }
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