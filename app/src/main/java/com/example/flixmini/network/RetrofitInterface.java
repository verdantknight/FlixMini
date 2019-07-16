package com.example.flixmini.network;

import com.example.flixmini.dto.PageEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitInterface {

    @GET("discover/movie")
    Call<PageEntity> getMovies(@Query("api_key") String apiKey, @Query("language") String language);

    @GET("search/movie")
    Call<PageEntity> searchMovies(@Query("api_key") String apiKey,
                                  @Query("language") String language,
                                  @Query("query") String query);

}
