package com.searchactors;

import java.lang.annotation.Target;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TheMovieDBAPI {
    String BASE_URL = "https://api.themoviedb.org/3/" ;

    
    @GET("person/popular?api_key=90f9f56e299e21f06338b0197a5ff6f6&language=en-US&page={currentPage}")
    Call<ResultsPage> getPopularResults(
            @Path("currentPage") String currentPage
    ) ;

    @GET("search/person?api_key=90f9f56e299e21f06338b0197a5ff6f6&language=en-US&page={currentPage}&include_adult=false")
    ResultsPage getSearchResults(
            @Path("currentPage") String currentPage
    ) ;

}
