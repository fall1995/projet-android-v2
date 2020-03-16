package modele;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MovieCollection {

    @SerializedName("results")
    private ArrayList<Movie> movieList;


    public ArrayList<Movie> getMovieList() {
        return movieList;
    }

  /*  public int getNumMovie(String title){
        String maValeur = title;
        int monIndice =0;

        for (int i=0 ; i< movieList.size() ; i++) {
            if (maValeur.equals(movieList.indexOf(i))){
                monIndice = i;
            }
    }
        return monIndice;

}*/
}
