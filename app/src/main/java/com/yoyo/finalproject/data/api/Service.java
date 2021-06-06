package com.yoyo.finalproject.data.api;


import com.yoyo.finalproject.data.models.TvShow;
import com.yoyo.finalproject.data.models.TvShowResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Service {
    @GET("tv/{sort_by}")
    Call<TvShowResponse> getResults(
            @Path("sort_by") String sortBy,
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );

    @GET("tv/{id}")
    Call<TvShow> getDetail(
            @Path("id") int id,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("search/tv")
    Call<TvShowResponse> search(
            @Query("api_key") String apiKey,
            @Query("query") String query,
            @Query("language") String language,
            @Query("page") int page
    );
}