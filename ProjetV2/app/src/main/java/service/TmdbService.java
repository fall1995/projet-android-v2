package service;

import modele.MovieCollection;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface TmdbService {
    @GET( "movie/now_playing")
    Call<MovieCollection> getMoviesNowPlaying(@Query("api_key") String key);

    @GET("movie/popular")
    Call<MovieCollection> getMoviesPopular(@Query("api_key") String key);

    @GET("search/movie")
    Call<MovieCollection> getSearch(@Query("api_key") String key, @Query("query") String search);

}
