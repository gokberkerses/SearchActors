package com.searchactors;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultsPage {

    Integer page ;
    List<Actor> results ;

    @SerializedName("total_results")
    Integer totalResults ;

    @SerializedName("total_pages")
    Integer totalPages ;

    public ResultsPage(Integer page, List<Actor> results, Integer totalResults, Integer totalPages) {
        this.page = page;
        this.results = results;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
    }

    public Integer getPage() {
        return page;
    }

    public List<Actor> getResults() {
        return results;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }
}
