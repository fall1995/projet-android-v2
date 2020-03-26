package service;

import modele.MovieCollection;
import modele.VideosCollection;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface TmdbService {
    @GET( "movie/now_playing")
    Call<MovieCollection> getMoviesNowPlaying(@Query("api_key") String key);

    @GET("movie/popular")
    Call<MovieCollection> getMoviesPopular(@Query("api_key") String key);

    @GET("search/movie")
    Call<MovieCollection> getSearch(@Query("api_key") String key, @Query("query") String search);

    @GET( "movie/{movie_id}/videos")
    Call<VideosCollection> getMovieVideos(@Path("movie_id") int movie_id, @Query ("api_key") String key);


}
