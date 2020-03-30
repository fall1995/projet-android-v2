package modele;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Movie {



    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title ;

    @SerializedName("poster_path")
    private String image ;

    @SerializedName("overview")
    private String overview ;

    public String getSortie() {
        return sortie;
    }

    @SerializedName("release_date")
    private String sortie ;

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    @SerializedName("vote_average")
    private Double voteAverage;

    public String getOverview() {
        return overview;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        if (image==null){
            String urlImage ="https://stica.fr/3155-large_default/clap-de-cinema.jpg";
            return urlImage;
        }
        String urlImage = "https://image.tmdb.org/t/p/w500";
        return urlImage+image;
    }
}
