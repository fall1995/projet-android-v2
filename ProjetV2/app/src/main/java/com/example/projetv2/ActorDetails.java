package com.example.projetv2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.projetv2.ui.favoris.FavorisFragment;
import com.example.projetv2.ui.films.FilmFragment;
import com.example.projetv2.ui.recherche.RechercheFragment;

import java.util.List;

import modele.Actor;
import modele.Cast;
import modele.CastingCollection;
import modele.Movie;
import modele.MovieActorCollection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import service.RetrofitClientInstance;
import service.TmdbService;

public class ActorDetails extends AppCompatActivity {

    private TextView name;
    private TextView birth;
    private TextView biographie;
    private TextView street;
    private String nom;
    private int pos;
    private String image;
    private String date;
    private String bio;
    private ImageView imageView;
    public  static List<Movie> movieActor;
    private RecyclerView recyclerMovieActor;
    public static final String MACTOR = "mactor";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.actor_details);
        recyclerMovieActor = findViewById(R.id.golf8);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerMovieActor.setLayoutManager(horizontalLayoutManager);
        name = findViewById(R.id.nom_acteur);
        imageView= findViewById(R.id.image_actor);
        birth= findViewById(R.id.birth);
        biographie = findViewById(R.id.bio);
      /*  street = findViewById(R.id.feature_street);
        address = findViewById(R.id.feature_address);
        type = findViewById(R.id.feature_type);*/
        Bundle b = getIntent().getExtras();

        if(b != null){
         //nom = b.getString(MovieDetails.ACTOR);
            pos = b.getInt("pos");
            getdetails();
        }
    }
    private void getdetails() {

        TmdbService tmdbService = RetrofitClientInstance.getlnstance().create(TmdbService.class);
       tmdbService.getActorMovie(MovieDetails.castinglist.get(pos).getId(), FilmFragment.key).enqueue(new Callback<MovieActorCollection>() {
            @Override
            public void onResponse(Call<MovieActorCollection> call, Response<MovieActorCollection> response) {
            movieActor = response.body().getMovieList();
startRecyclerActorMovies();
                Log.i("pago","nom film =" + response.body().getMovieList().get(0).getTitle());
            }

           @Override
           public void onFailure(Call<MovieActorCollection> call, Throwable t) {

           }


        });

        Log.i("ActorId","id = "+MovieDetails.castinglist.get(pos).getId());
        tmdbService.getActorDetails(MovieDetails.castinglist.get(pos).getId(), FilmFragment.key).enqueue(new Callback<Actor>() {
            @Override
            public void onResponse(Call<Actor> call, Response<Actor> response) {
                nom = response.body().getName();
                image = response.body().getProfilePath();
                date = response.body().getBirthday();
                bio = response.body().getBiography();
                displayData();

            }

            @Override
            public void onFailure(Call<Actor> call, Throwable t) {

            }



        });

    }

    private void startRecyclerActorMovies(){
        final RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(movieActor, new RecyclerViewAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {


                //    Toast.makeText(getActivity(),"movie selectionnée en position " + position, Toast.LENGTH_LONG).show();
                Intent movieClickActivity = new Intent(getApplicationContext(), MovieDetails.class);

                Bundle b = new Bundle();
                Movie movie = (Movie) movieActor.get(position);
                String nomSelect = movie.getTitle();
                b.putString(MACTOR, MACTOR);
                b.putInt("pos",position);
                movieClickActivity.putExtras(b); //Put your id to your next Intent
                startActivity(movieClickActivity);

            }
        },100);
        recyclerMovieActor.setAdapter(recyclerViewAdapter);
        recyclerMovieActor.setTag(recyclerViewAdapter);

        recyclerMovieActor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Log.i("click","je viens de cliquer sur ..");
            }
        });
    }
    private void displayData() {
        name.setText(nom);
        birth.setText(date);
        biographie.setText(bio);
        Glide.with(this).load(image).into(imageView);
    }
}
