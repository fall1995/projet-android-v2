package modele;

import com.google.gson.annotations.SerializedName;

public class Movie {

    @SerializedName("title")
    private String title ;

    @SerializedName("poster_path")
    private String image ;





    public String getTitle() {
        return title;
    }

    public String getImage() {

        String urlImage = "https://image.tmdb.org/t/p/w500";
        return urlImage+image;
    }
}
