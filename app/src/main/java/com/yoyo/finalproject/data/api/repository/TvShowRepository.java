package com.yoyo.finalproject.data.api.repository;


import android.util.Log;

import androidx.annotation.NonNull;

import com.yoyo.finalproject.Consts;
import com.yoyo.finalproject.data.api.Service;
import com.yoyo.finalproject.data.api.repository.callback.OnCallback;
import com.yoyo.finalproject.data.api.repository.callback.OnSearchCallback;
import com.yoyo.finalproject.data.api.repository.callback.OnTvDetailCallback;
import com.yoyo.finalproject.data.models.TvShow;
import com.yoyo.finalproject.data.models.TvShowResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TvShowRepository {
    private static TvShowRepository repository;
    private Service service;

    private TvShowRepository(Service service) {
        this.service = service;
    }

    public static TvShowRepository getInstance() {
        if (repository == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Consts.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            repository = new TvShowRepository(retrofit.create(Service.class));
        }
        return repository;
    }

    public void getTvShow(int page, final OnCallback<TvShow> callback) {
        service.getTvResults(Consts.API_KEY, Consts.getLang(), page)
                .enqueue(new Callback<TvShowResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<TvShowResponse> call, @NonNull Response<TvShowResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                if (response.body().getResults() != null) {
                                    Log.d("TV Show Response", response.body().getResults().toString());
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
                    public void onFailure(@NonNull Call<TvShowResponse> call, @NonNull Throwable t) {
                        callback.onFailure(t.getLocalizedMessage());
                    }
                });
    }

    public void getTvDetail(int id, final OnTvDetailCallback callback) {
        service.getTvDetail(id, Consts.API_KEY, Consts.getLang())
                .enqueue(new Callback<TvShow>() {
                    @Override
                    public void onResponse(@NonNull Call<TvShow> call, @NonNull Response<TvShow> response) {
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
                    public void onFailure(@NonNull Call<TvShow> call, @NonNull Throwable t) {
                        callback.onFailure(t.getLocalizedMessage());
                    }
                });
    }

    public void search(String query, int page, final OnSearchCallback<TvShow> callback) {
        service.searchTV(Consts.API_KEY, query, Consts.getLang(), page)
                .enqueue(new Callback<TvShowResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<TvShowResponse> call, @NonNull Response<TvShowResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                if (response.body().getResults() != null) {
                                    callback.onSuccess(response.body().getResults(), response.message(), response.body().getPage());
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
                    public void onFailure(@NonNull Call<TvShowResponse> call, @NonNull Throwable t) {
                        callback.onFailure(t.getLocalizedMessage());
                    }
                });
    }
}