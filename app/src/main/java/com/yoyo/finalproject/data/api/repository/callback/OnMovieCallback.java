package com.yoyo.finalproject.data.api.repository.callback;

import com.yoyo.finalproject.data.models.Movie;

import java.util.List;


public interface OnMovieCallback {
    void onSuccess(int page, List<Movie> movieList);

    void onFailure(String message);
}
