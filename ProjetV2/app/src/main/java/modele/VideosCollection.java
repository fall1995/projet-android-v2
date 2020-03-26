package modele;

import java.util.ArrayList;
import java.util.List;

public class VideosCollection {
    private String id;
    private List <Video> results = new ArrayList<Video>();

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public List<Video> getResults() {
        return results;
    }
    public void setResults(List<Video> results) {
        this.results = results;
    }
}
