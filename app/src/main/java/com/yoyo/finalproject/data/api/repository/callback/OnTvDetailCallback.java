package com.yoyo.finalproject.data.api.repository.callback;


import com.yoyo.finalproject.data.models.TvShow;

public interface OnTvDetailCallback {
    void onSuccess(TvShow tvShow, String message);

    void onFailure(String message);
}
