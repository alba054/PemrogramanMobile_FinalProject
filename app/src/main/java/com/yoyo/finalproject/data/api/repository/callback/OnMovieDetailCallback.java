package com.yoyo.finalproject.data.api.repository.callback;


import com.yoyo.finalproject.data.models.Movie;

public interface OnMovieDetailCallback {
    void onSuccess(Movie movie, String message);

    void onFailure(String message);
}
