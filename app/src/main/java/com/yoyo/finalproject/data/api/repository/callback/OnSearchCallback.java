package com.yoyo.finalproject.data.api.repository.callback;

import com.yoyo.finalproject.data.models.Movie;
import com.yoyo.finalproject.data.models.TvShow;

import java.util.List;


public interface OnSearchCallback {
    void onSuccess(List<TvShow> tvShowList, List<Movie> movieList, String msg, int page);

    void onFailure(String msg);
}
