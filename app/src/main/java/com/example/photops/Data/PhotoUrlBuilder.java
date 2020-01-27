package com.example.photops.Data;

public class PhotoUrlBuilder {
    public static final String PICS_URL_PREFIX = "https://photos.gurushots.com/unsafe/";

    public static String build(Item photo){
        return PICS_URL_PREFIX + photo.getWidth() + "x" + photo.getHeight()
                + "/" +  photo.getMemberId() + "/" + "3_" + photo.getId() + ".jpg";
    }
}