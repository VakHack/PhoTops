package com.example.photops.Data;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.photops.Network.Serializers.User;
import com.example.photops.Network.Serializers.Urls;
import com.google.gson.Gson;

public class SharedPrefsStorage extends Storage {
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    Gson gson = new Gson();

    private final String APP_KEY = "photops";
    private final String PHOTO_KEY = "photo";
    private final String IS_PHOTO_LIKED_KEY = "liked_";

    public SharedPrefsStorage(Context context) {
        super(context);
        sharedPref = context.getSharedPreferences(APP_KEY, Context.MODE_PRIVATE);
    }

    @Override
    public void setActivePhoto(Photo photo) {
        String strPhoto = gson.toJson(photo);

        editor = sharedPref.edit();
        editor.putString(PHOTO_KEY, strPhoto);
        editor.apply();
    }

    @Override
    public Photo getActivePhoto() {
        String strPhoto = sharedPref.getString(PHOTO_KEY, "");
        return gson.fromJson(strPhoto, Photo.class);
    }

    @Override
    public void toggleLikePhoto(Photo photo) {
        editor = sharedPref.edit();
        editor.putBoolean(IS_PHOTO_LIKED_KEY + getActivePhoto().getUrls().getFull()
                , !isPhotoLiked(photo));
        editor.apply();
    }

    @Override
    public boolean isPhotoLiked(Photo photo) {
        return sharedPref.getBoolean(IS_PHOTO_LIKED_KEY + photo.getUrls().getFull()
                , false);
    }
}
