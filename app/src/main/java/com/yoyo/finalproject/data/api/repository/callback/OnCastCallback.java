package com.yoyo.finalproject.data.api.repository.callback;

import com.yoyo.finalproject.data.models.Cast;

import java.util.List;

public interface OnCastCallback {
    void onSuccess(List<Cast> castList, String message);
    void onFailure(String message);
}
