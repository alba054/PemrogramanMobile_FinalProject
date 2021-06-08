package com.yoyo.finalproject.data.api.repository.callback;


public interface OnDetailCallback <T> {
    void onSuccess(T media, String message);

    void onFailure(String message);
}
