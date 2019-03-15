package com.searchactors;

import java.lang.annotation.Target;

import retrofit2.http.GET;

public interface TheMovieDBAPI {
    String BASE_URL = "https://api.themoviedb.org/3/" ;

    Integer currentPopularPage = 1 ;
    Integer currentSearchPage = 1 ;

    //use @PATH ? dig annotations. 
    @GET("person/popular?api_key=90f9f56e299e21f06338b0197a5ff6f6&language=en-US&page=" + currentPopularPage)
    ResultsPage getPopularResults() ;

    @GET("search/person?api_key=<<api_key>>&language=en-US&page=" + currentSearchPage + "&include_adult=false")
    ResultsPage getSearchResults() ;

}
