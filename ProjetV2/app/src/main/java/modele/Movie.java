package modele;

import com.google.gson.annotations.SerializedName;

public class Movie {

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title ;

    @SerializedName("poster_path")
    private String image ;

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    @SerializedName("vote_average")
    private Double voteAverage;

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
