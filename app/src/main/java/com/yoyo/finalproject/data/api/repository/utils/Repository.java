package com.yoyo.finalproject.data.api.repository.utils;

import com.yoyo.finalproject.data.api.Service;
import com.yoyo.finalproject.data.api.repository.callback.OnCallback;
import com.yoyo.finalproject.data.api.repository.callback.OnDetailCallback;
import com.yoyo.finalproject.data.api.repository.callback.OnSearchCallback;

public abstract class Repository<T> {
    protected Service service;

    protected abstract void getModel(int page, final OnCallback<T> callback);
    protected abstract void getModelDetail(int id, final OnDetailCallback<T> callback);
    protected abstract void search(String query, int page, final OnSearchCallback<T> callback);
}
