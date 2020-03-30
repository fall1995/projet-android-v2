package modele;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CastingCollection {
    @SerializedName("id")
    private Integer id;

    @SerializedName("cast")
    private List<Cast> cast ;

    @SerializedName("crew")
    private List<Crew> crew ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Cast> getCast() {
        return cast;
    }

    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }

    public List<Crew> getCrew() {
        return crew;
    }

    public void setCrew(List<Crew> crew) {
        this.crew = crew;
    }

}
