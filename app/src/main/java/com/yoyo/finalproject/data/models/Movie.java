package com.yoyo.finalproject.data.models;

import com.google.gson.annotations.SerializedName;
import com.yoyo.finalproject.Consts;
import com.yoyo.finalproject.ImageSize;
import com.yoyo.finalproject.data.models.Genre;

import java.util.List;

public class Movie {

    @SerializedName("original_title")
    private String title;

    @SerializedName("backdrop_path")
    private String backdropImage;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("poster_path")
    private String posterPath;

    private int id;

    @SerializedName("genres")
    private List<Genre> genres;

    private String overview;

    public String getTitle() {
        return title;
    }


    public String getBackdropImage() {
        return backdropImage;
    }


    public String getPosterPath(ImageSize size) {
        return Consts.IMG_URL + size.getValue() + posterPath;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Genre> getGenres() {
        return genres;
    }


    public String getOverview() {
        return overview;
    }

}

