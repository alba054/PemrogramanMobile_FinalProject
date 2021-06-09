package com.yoyo.finalproject.data.models;

import com.google.gson.annotations.SerializedName;
import com.yoyo.finalproject.Consts;
import com.yoyo.finalproject.ImageSize;

public class Cast {
    @SerializedName("name")
    private String name;
    @SerializedName("character")
    private String character;
    @SerializedName("profile_path")
    private String profilePath;

    public String getName() {
        return name;
    }

    public String getCharacter() {
        return character;
    }

    public String getProfilePath(ImageSize size) {
        return Consts.IMG_URL + size.getValue() + profilePath;
    }
}