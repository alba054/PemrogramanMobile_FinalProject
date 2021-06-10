package com.yoyo.finalproject.UI.adapters.clickListeners;

import com.yoyo.finalproject.data.local.models.FavoriteMovie;
import com.yoyo.finalproject.data.local.models.FavoriteTv;
import com.yoyo.finalproject.data.models.Movie;
import com.yoyo.finalproject.data.models.TvShow;

public interface OnItemClickListener {
    void onClick(TvShow tvShow);
    void onClick(Movie movie);
    void onClick(FavoriteMovie movie);
    void onClick(FavoriteTv tv);
}
