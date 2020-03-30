package modele;

import com.google.gson.annotations.SerializedName;

public class Cast {

    @SerializedName("character")
    private String character;


    @SerializedName("name")
    private String name;

    @SerializedName("profile_path")
    private String profile_path;

    public String getCharacter() {
        return character;
    }

    public String getName() {
        return name;
    }

    public String getProfile_path() {
        String path = "https://image.tmdb.org/t/p/w300";
        return path+profile_path;
    }

}
