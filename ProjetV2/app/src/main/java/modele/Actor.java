package modele;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Actor {
    @SerializedName("birthday")

    private String birthday;
    @SerializedName("known_for_department")

    private String knownForDepartment;
    @SerializedName("deathday")

    private Object deathday;
    @SerializedName("id")

    private Integer id;
    @SerializedName("name")

    private String name;
    @SerializedName("also_known_as")

    private List<String> alsoKnownAs = null;
    @SerializedName("gender")

    private Integer gender;
    @SerializedName("biography")

    private String biography;
    @SerializedName("popularity")

    private Double popularity;
    @SerializedName("place_of_birth")

    private String placeOfBirth;
    @SerializedName("profile_path")

    private String profilePath;
    @SerializedName("adult")

    private Boolean adult;
    @SerializedName("imdb_id")

    private String imdbId;
    @SerializedName("homepage")

    private Object homepage;

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getKnownForDepartment() {
        return knownForDepartment;
    }

    public void setKnownForDepartment(String knownForDepartment) {
        this.knownForDepartment = knownForDepartment;
    }

    public Object getDeathday() {
        return deathday;
    }

    public void setDeathday(Object deathday) {
        this.deathday = deathday;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAlsoKnownAs() {
        return alsoKnownAs;
    }

    public void setAlsoKnownAs(List<String> alsoKnownAs) {
        this.alsoKnownAs = alsoKnownAs;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getProfilePath() {
        if (profilePath==null){
            String urlImage ="https://cdn.icon-icons.com/icons2/1369/PNG/512/-person_90382.png";
            return urlImage;
        }
        String path = "https://image.tmdb.org/t/p/w300";
        return path+profilePath;
    }


    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public Object getHomepage() {
        return homepage;
    }

    public void setHomepage(Object homepage) {
        this.homepage = homepage;
    }
}
