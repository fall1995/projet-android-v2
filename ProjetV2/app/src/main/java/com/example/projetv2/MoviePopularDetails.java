package com.example.projetv2;

import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetv2.ui.films.FilmFragment;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import modele.Movie;
import modele.Video;
import modele.VideosCollection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import service.RetrofitClientInstance;
import service.TmdbService;

import static com.example.projetv2.MainActivity.NOM_FILM;

public class MoviePopularDetails extends YouTubeBaseActivity
        implements YouTubePlayer.OnInitializedListener {
    private final String APIYoutube = "AIzaSyBbf-y_8UUB7AYcqkvHSbE_fJ7GVdIzcxw";
    public static final String key = "1abe855bc465dce9287da07b08a664eb";


    private YouTubePlayerView youTubePlayerView;
    private YouTubePlayer youTubePlayer;
    private Movie selectedMovie;
    private TextView movieTitle;
    private TextView movieRealisateur;
    private RatingBar ratingBar;
    private int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_movie_detail);
        uploadFeature();
        youTubePlayerView = findViewById(R.id.youtubeplayerview);
        youTubePlayerView.initialize(APIYoutube, this);
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


        selectedMovie = FilmFragment.moviePopular.get(pos);
        Log.i("skulurt", selectedMovie.getTitle());
        setDetails();
      /*  TmdbService tmdbService = RetrofitClientInstance.getlnstance().create(TmdbService.class);
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
        });*/
    }

    private void getMovieVideos() {
        TmdbService tmdbService = RetrofitClientInstance.getlnstance().create(TmdbService.class);
        Call<VideosCollection> call;

        call = tmdbService.getMovieVideos(selectedMovie.getId(),key);
        call.enqueue(new Callback<VideosCollection>() {
            @Override
            public void onResponse(Call<VideosCollection> call, Response<VideosCollection> response) {
                VideosCollection videosRecues = null;
                videosRecues = response.body();

                if(videosRecues!=null && videosRecues.getResults()!=null && videosRecues.getResults().size()>0){
                    Log.i("youtubeAPI", "Youtube réussi !! " + videosRecues.getResults().size());
                    Video bandeAnnonce = videosRecues.getResults().get(0);
                    String lienYoutube = ""+bandeAnnonce.getKey();
                    Log.i("youtubeAPI", "Youtube réussi !! " + bandeAnnonce.getId() + "  "  );
                    youTubePlayer.cueVideo(lienYoutube);
                }
                else{

                }
            }

            @Override
            public void onFailure(Call<VideosCollection> call, Throwable t) {
                Log.i("youtubeAPI", "Youtube pas réussi !! ");

            }
        });
    }

    private void setDetails(){
        movieTitle=findViewById(R.id.title);
        movieRealisateur=findViewById(R.id.realisateur);
        ratingBar = findViewById(R.id.movie_rating);

        double voteDouble = selectedMovie.getVoteAverage();
        float vote = (float)voteDouble/2;
        Log.i("nooote", "je note ici " + vote);

        ratingBar.setRating(vote);
//        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
//        stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);

        movieTitle.setText(selectedMovie.getTitle());
        //  movieRealisateur.setText(selectedMovie.getProperties().getCity());
        //age.setText(student.age + " ans");
        //gender.setText(student.gender);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlaye, boolean b) {
        youTubePlayer = youTubePlaye;
        if (!b) {
            getMovieVideos();
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }
}
