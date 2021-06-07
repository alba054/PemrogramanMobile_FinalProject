package com.yoyo.finalproject.data.api.repository;


import android.util.Log;

import androidx.annotation.NonNull;

import com.yoyo.finalproject.Consts;
import com.yoyo.finalproject.data.api.Service;
import com.yoyo.finalproject.data.api.repository.callback.OnMovieCallback;
import com.yoyo.finalproject.data.api.repository.callback.OnMovieDetailCallback;
import com.yoyo.finalproject.data.api.repository.callback.OnSearchCallback;
import com.yoyo.finalproject.data.models.Movie;
import com.yoyo.finalproject.data.models.MovieResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRepository {
    private static MovieRepository repository;
    private Service service;

    private MovieRepository(Service service) {
        this.service = service;
    }



    public void getMovie(int page, final OnMovieCallback callback) {
        service.getMovieResults(Consts.API_KEY, Consts.getLang(), page)
                .enqueue(new Callback<MovieResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                if (response.body().getResults() != null) {

                                    callback.onSuccess(response.body().getPage(), response.body().getResults());
                                } else {
                                    callback.onFailure("response.body().getResults() is null");
                                }
                            } else {
                                callback.onFailure("response.body() is null");
                            }
                        } else {
                            callback.onFailure(response.message());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {
                        callback.onFailure(t.getLocalizedMessage());
                    }
                });
    }

    public void getTvDetail(int id, final OnMovieDetailCallback callback) {
        service.getMovieDetail(id, Consts.API_KEY, Consts.getLang())
                .enqueue(new Callback<Movie>() {
                    @Override
                    public void onResponse(@NonNull Call<Movie> call, @NonNull Response<Movie> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                callback.onSuccess(response.body(), response.message());
                            } else {
                                callback.onFailure("response.body() is null");
                            }
                        } else {
                            callback.onFailure(response.message() + ", Error Code : " + response.code());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Movie> call, @NonNull Throwable t) {
                        callback.onFailure(t.getLocalizedMessage());
                    }
                });
    }

    public void search(String query, int page, final OnSearchCallback callback) {
        service.searchMovie(Consts.API_KEY, query, Consts.getLang(), page)
                .enqueue(new Callback<MovieResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                if (response.body().getResults() != null) {
                                    callback.onSuccess(null, response.body().getResults(),
                                            response.message(), response.body().getPage());
                                } else {
                                    callback.onFailure("No Results");
                                }
                            } else {
                                callback.onFailure("response.body() is null");
                            }
                        } else {
                            callback.onFailure(response.message() + " : " + response.code());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {
                        callback.onFailure(t.getLocalizedMessage());
                    }
                });
    }
}