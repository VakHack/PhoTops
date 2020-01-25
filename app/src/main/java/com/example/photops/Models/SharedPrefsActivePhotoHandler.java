package com.example.photops.Models;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.photops.Models.Networking.Urls;

public class SharedPrefsActivePhotoHandler extends ActivePhotoHandler {
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;

    private String APP_KEY = "photops";
    private String ACTIVE_PHOTO_KEY = "active_photo";

    public SharedPrefsActivePhotoHandler(Context context) {
        super(context);
        sharedPref = context.getSharedPreferences(APP_KEY, Context.MODE_PRIVATE);
    }

    @Override
    public void setActivePhoto(Photo photo) {
        editor = sharedPref.edit();
        editor.putString(ACTIVE_PHOTO_KEY, photo.getUrls().getFull());
        editor.apply();
    }

    @Override
    public Photo getActivePhoto() {
        Urls url = new Urls();
        url.setFull(sharedPref.getString(ACTIVE_PHOTO_KEY, "empty"));
        return new Photo(url);
    }

    @Override
    public void likePhoto() {
        editor = sharedPref.edit();
        editor.putBoolean(getActivePhoto().getUrls().getFull(), true);
        editor.apply();
    }

    @Override
    public boolean isPhotoLiked() {
        return sharedPref.getBoolean(getActivePhoto().getUrls().getFull(), false);
    }
}
