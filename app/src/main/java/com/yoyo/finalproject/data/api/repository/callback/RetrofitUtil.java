package com.yoyo.finalproject.data.api.repository.callback;

import com.yoyo.finalproject.Consts;
import com.yoyo.finalproject.data.api.Service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {


    public static RetrofitUtil getInstance() {
        if (repository == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Consts.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            repository = new MovieRepository(retrofit.create(Service.class));
        }
        return repository;
    }
}
