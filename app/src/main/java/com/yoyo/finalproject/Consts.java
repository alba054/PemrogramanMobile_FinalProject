package com.yoyo.finalproject;

import java.util.Locale;

public class Consts {

    public static final String API_KEY = "b6a72f1771f634c736174064d30b7e02";
    public static final String BASE_URL = "http://api.themoviedb.org/3/";
    public static final String IMG_URL = "https://image.tmdb.org/t/p/";

    public static String getLang() {
        switch (Locale.getDefault().toString()) {
            case "in_ID":
                return "id-ID";
            default:
                return "en-US";
        }
    }

}
