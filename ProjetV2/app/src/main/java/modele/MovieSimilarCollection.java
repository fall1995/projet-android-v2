package modele;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieSimilarCollection {
    @SerializedName("page")

    private Integer page;
    @SerializedName("results")

    private List<Movie> results = null;
    @SerializedName("total_pages")

    private Integer totalPages;
    @SerializedName("total_results")

    private Integer totalResults;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }
}
