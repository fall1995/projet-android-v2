package modele;

import com.google.gson.annotations.SerializedName;

public class Cast {

    @SerializedName("character")
    private String character;

    @SerializedName("id")
    private int id;

    public int getId() {
        return id;
    }

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
        if (profile_path==null){
            String urlImage ="https://cdn.icon-icons.com/icons2/1369/PNG/512/-person_90382.png";
            return urlImage;
        }
        String path = "https://image.tmdb.org/t/p/w300";
        return path+profile_path;
    }

}
