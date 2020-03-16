package com.example.projetv2;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import modele.Movie;
import modele.MovieCollection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import service.RetrofitClientInstance;
import service.TmdbService;

import static com.example.projetv2.MainActivity.key;
import static com.example.projetv2.MainActivity.NOM_FILM;

public class MovieDetails extends AppCompatActivity {
    private Movie selectedMovie;
    private TextView movieTitle;
    private TextView movieRealisateur;
    private int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_movie_detail);
        uploadFeature();

       /* TextView movieTitle = findViewById(R.id.title);
        TextView movieRealisateur = findViewById(R.id.realisateur);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            int studentId = bundle.getInt(NOM_FILM);
            Movie movie = StudentContent.ITEMS.get(studentId);

            studentFirstName.setText(student.firstName);
            studentLastName.setText(student.lastName);
            studentAge.setText(student.age + " yo");
            studentGender.setText(student.gender);
        }*/
    }

    private void uploadFeature() {
        Bundle b = getIntent().getExtras();
        int value = -1; // or other values
        String nomfilm="";

        if(b != null){
            nomfilm= b.getString(NOM_FILM);
            pos = b.getInt("pos");
            Log.i("heazazaazzayyyy", nomfilm);
        }

        TmdbService tmdbService = RetrofitClientInstance.getlnstance().create(TmdbService.class);
        tmdbService.getMoviesNowPlaying(key).enqueue(new Callback<MovieCollection>() {
            @Override
            public void onResponse(Call<MovieCollection> call, Response<MovieCollection> response) {
                ArrayList<Movie> movieCollection=response.body().getMovieList();
                selectedMovie = movieCollection.get(pos);
               setDetails();
            }

            @Override
            public void onFailure(Call<MovieCollection> call, Throwable t) {


            }
        });
    }
    private void setDetails(){
        movieTitle=findViewById(R.id.title);
        movieRealisateur=findViewById(R.id.realisateur);


        movieTitle.setText(selectedMovie.getTitle());
      //  movieRealisateur.setText(selectedMovie.getProperties().getCity());
        //age.setText(student.age + " ans");
        //gender.setText(student.gender);
    }

}
