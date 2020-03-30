package modele;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MovieCollection {

    @SerializedName("results")
    private ArrayList<Movie> movieList;


    public ArrayList<Movie> getMovieList() {
        return movieList;
    }

}
