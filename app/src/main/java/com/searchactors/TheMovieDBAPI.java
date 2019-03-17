package com.searchactors;

import java.lang.annotation.Target;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TheMovieDBAPI {
    String BASE_URL = "https://api.themoviedb.org/3/" ;


    @GET("person/popular")
    Call<ResultsPage> getPopularResults(
            @Query("api_key") String apiKey,
            @Query("page") int queriedPage
    ) ;

    @GET("search/person")
    Call<ResultsPage> getSearchResults(
            @Query("api_key") String apiKey,
            @Query("query") String query,
            @Query("page") int queriedPage
            // check whether include_adult is false
    ) ;

}
