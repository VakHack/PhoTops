package com.example.photops.Data;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.photops.Network.Urls;

public class SharedPrefsStorage extends Storage {
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;

    private String APP_KEY = "photops";
    private String ACTIVE_PHOTO_KEY = "active_photo";

    public SharedPrefsStorage(Context context) {
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
        Urls urls = new Urls();
        urls.setFull(sharedPref.getString(ACTIVE_PHOTO_KEY, "empty"));
        return new Photo(urls);
    }

    @Override
    public void toggleLikePhoto(Photo photo) {
        editor = sharedPref.edit();
        editor.putBoolean(getActivePhoto().getUrls().getFull(), !isPhotoLiked(photo));
        editor.apply();
    }

    @Override
    public boolean isPhotoLiked(Photo photo) {
        return sharedPref.getBoolean(photo.getUrls().getFull(), false);
    }
}
