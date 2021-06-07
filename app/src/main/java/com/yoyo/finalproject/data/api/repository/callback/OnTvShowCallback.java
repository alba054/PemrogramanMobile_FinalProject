package com.yoyo.finalproject.data.api.repository.callback;

import com.yoyo.finalproject.data.models.TvShow;

import java.util.List;


public interface OnTvShowCallback {
    void onSuccess(int page, List<TvShow> tvShowList);

    void onFailure(String message);
}
