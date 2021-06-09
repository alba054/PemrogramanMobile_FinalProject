package com.yoyo.finalproject.data.api.repository;


import androidx.annotation.NonNull;

import com.yoyo.finalproject.Consts;
import com.yoyo.finalproject.data.api.Service;
import com.yoyo.finalproject.data.api.repository.callback.OnCallback;
import com.yoyo.finalproject.data.api.repository.callback.OnDetailCallback;
import com.yoyo.finalproject.data.api.repository.callback.OnSearchCallback;
import com.yoyo.finalproject.data.api.repository.utils.Repository;
import com.yoyo.finalproject.data.api.repository.utils.SingleRequest;
import com.yoyo.finalproject.data.models.Movie;
import com.yoyo.finalproject.data.models.MovieResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository extends Repository<Movie> {

    private MovieRepository(Service service) {
        this.service = service;
    }

    private static MovieRepository repository;

    public static MovieRepository getInstance() {
        if (repository == null) {
            Service retrofit = SingleRequest.getInstance();
            repository = new MovieRepository(retrofit);
        }
        return repository;
    }

    public void getModel(int page, final OnCallback<Movie> callback) {
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

    public void getModelDetail(int id, final OnDetailCallback<Movie> callback) {
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

    public void search(String query, int page, final OnSearchCallback<Movie> callback) {
        service.searchMovie(Consts.API_KEY, query, Consts.getLang(), page)
                .enqueue(new Callback<MovieResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                if (response.body().getResults() != null) {
                                    callback.onSuccess(response.body().getResults(),
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