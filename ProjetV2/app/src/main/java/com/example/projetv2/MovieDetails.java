package com.example.projetv2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.projetv2.ui.favoris.FavorisFragment;
import com.example.projetv2.ui.films.FilmFragment;
import com.example.projetv2.ui.recherche.RechercheFragment;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.gson.Gson;
import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.List;

import modele.Cast;
import modele.CastingCollection;
import modele.Movie;
import modele.Video;
import modele.VideosCollection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import service.RetrofitClientInstance;
import service.TmdbService;

public class MovieDetails extends YouTubeBaseActivity
        implements YouTubePlayer.OnInitializedListener {
    private final String APIYoutube = "AIzaSyBbf-y_8UUB7AYcqkvHSbE_fJ7GVdIzcxw";
    public static final String key = "1abe855bc465dce9287da07b08a664eb";


    private YouTubePlayerView youTubePlayerView;
    private YouTubePlayer youTubePlayer;
    private Movie selectedMovie;
    private TextView movieTitle;
    private TextView sortie;
    private List<Cast> castinglist;
    private TextView movieOverview;
    private RatingBar ratingBar;
    private int pos;
    private LikeButton button;
    private ImageView imageView;
    private  RecyclerView recyclerCast;
    String listPopular="";
    String listFavoris="";
    String listNow="";
    String listsearch="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_movie_detail);
        recyclerCast = findViewById(R.id.cast);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerCast.setLayoutManager(horizontalLayoutManager);
        button = (LikeButton) findViewById(R.id.spark_button2);

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
        uploadFeature();
        youTubePlayerView = findViewById(R.id.youtubeplayerview);
        youTubePlayerView.initialize(APIYoutube, this);
        getDetails();


    }

    private void uploadFeature() {
        Bundle b = getIntent().getExtras();
        int value = -1; // or
        // other values


        if(b != null){
            listPopular= b.getString(FilmFragment.POPULAR);
            listNow= b.getString(FilmFragment.NOW);
            listsearch= b.getString(RechercheFragment.SEARCH);
            listFavoris= b.getString(FavorisFragment.FAVORIS);

            pos = b.getInt("pos");
          // Log.i("heazazaazzayyyy", listPopular);
        }
        if (listPopular!=null && listPopular.equals(FilmFragment.POPULAR) ) {
            selectedMovie = FilmFragment.moviePopular.get(pos);
         //   Log.i("skulurt", selectedMovie.getTitle());
        }else if ( listNow!=null && listNow.equals(FilmFragment.NOW) ) {
            selectedMovie = FilmFragment.movieNowPlaying.get(pos);
          //   Log.i("skulurt", selectedMovie.getTitle());
        }else if (listsearch!=null && listsearch.equals(RechercheFragment.SEARCH)  ) {
            selectedMovie = RechercheFragment.listMovie.get(pos);
            // Log.i("skulurt", selectedMovie.getTitle());
        }else if (listFavoris!=null && listFavoris.equals(FavorisFragment.FAVORIS)  ) {
            selectedMovie = FavorisFragment.listFavoris.get(pos);
          //  Log.i("skulurt", selectedMovie.getTitle());
        }


        setDetails();


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

    private void getDetails() {
        TmdbService tmdbService = RetrofitClientInstance.getlnstance().create(TmdbService.class);
        tmdbService.getMovieDetails(selectedMovie.getId(),key).enqueue(new Callback<CastingCollection>() {
            @Override
            public void onResponse(Call<CastingCollection> call, Response<CastingCollection> response) {

                castinglist =response.body().getCast();
             //   Log.i("ofcurse","actor " + castinglist.get(0).getName() );
                startRecyclerCast();

            }

            @Override
            public void onFailure(Call<CastingCollection> call, Throwable t) {
                Log.i("neein","neeein");
            }

        });

    }

    private void setDetails(){
        movieTitle=findViewById(R.id.titre);
        movieOverview=findViewById(R.id.overview);
        ratingBar = findViewById(R.id.movie_rating);
        imageView = (ImageView) findViewById(R.id.image_details);
        sortie = findViewById(R.id.sortie);
        int c =0;

        Glide.with(this).load(selectedMovie.getImage()).into(imageView);
        double voteDouble = selectedMovie.getVoteAverage();
        float vote = (float)voteDouble/2;

        for (int i = 0; i < FilmFragment.listFavoris.size(); i++) {

            if (FilmFragment.listFavoris.get(i).getTitle().equals(selectedMovie.getTitle())) {
                c++;
            }
        }
        Log.i("seyf1","t ="+  FilmFragment.listFavoris.size());
        Log.i("seyf", "c" + c);
        if (c > 0) {
            //   Log.i("bingodesbengos","enfiin");
            button.setLiked(true);
        } else {
            button.setLiked(false);
        }
        ratingBar.setRating(vote);
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);

        sortie.setText(selectedMovie.getSortie());
        movieTitle.setText(selectedMovie.getTitle());
        movieOverview.setText(selectedMovie.getOverview());
      //  movieRealisateur.setText(selectedMovie.getProperties().getCity());

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


    private void startRecyclerCast(){
        Log.i("castrecyler","castore");
        final AdapterCast recyclerViewAdapter = new AdapterCast(castinglist, new AdapterCast.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //Toast.makeText(getApplicationContext()," en position " + position, Toast.LENGTH_LONG).show();
               /* Intent movieClickActivity = new Intent(getApplicationContext(), MovieDetails.class);

                Bundle b = new Bundle();
                Movie movie = (Movie) moviePopular.get(position);
                String nomSelect = movie.getTitle();
                b.putString(POPULAR, POPULAR);
                b.putInt("pos",position);
                movieClickActivity.putExtras(b); //Put your id to your next Intent
                startActivity(movieClickActivity);*/
                //finish();
            }
        },3);
        recyclerCast.setAdapter(recyclerViewAdapter);

        recyclerCast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("click","je viens de cliquer sur ..");
            }
        });
    }

    private void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(FilmFragment.listFavoris);
        editor.putString("task list",json);
        editor.apply();

    }

    public void addFavoris(View v) {

        if (listPopular!=null && listPopular.equals(FilmFragment.POPULAR) ) {

            FilmFragment.listFavoris.add(FilmFragment.moviePopular.get(pos));

        }else if ( listNow!=null && listNow.equals(FilmFragment.NOW) ) {
            FilmFragment.listFavoris.add(FilmFragment.movieNowPlaying.get(pos));

            //   Log.i("skulurt", selectedMovie.getTitle());
        }

       // Log.i("add", "tailleListe " + FilmFragment.listFavoris.size());
        FilmFragment.recyclerNowPlaying.getAdapter().notifyDataSetChanged();
        FilmFragment.recyclerPopular.getAdapter().notifyDataSetChanged();
        saveData();
    }

    public void supFavoris(View v) {

        for (int i = 0; i < FilmFragment.listFavoris.size(); i++) {


            if (FilmFragment.listFavoris.get(i).getTitle().equals(selectedMovie.getTitle())){
                FilmFragment.listFavoris.remove(i);
                //notifyDataSetChanged();

            }


            if (FavorisFragment.recyclerFavoris != null) {
                FavorisFragment.recyclerFavoris.getAdapter().notifyDataSetChanged();

            }


                FilmFragment.recyclerPopular.getAdapter().notifyDataSetChanged();



                FilmFragment.recyclerNowPlaying.getAdapter().notifyDataSetChanged();



        }
        saveData();
    }
}
