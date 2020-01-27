package com.example.photops.Data;

import android.content.Context;
import android.content.SharedPreferences;

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
    public void setActivePhoto(Item photo) {
        String strPhoto = gson.toJson(photo);

        editor = sharedPref.edit();
        editor.putString(PHOTO_KEY, strPhoto);
        editor.apply();
    }

    @Override
    public Item getActivePhoto() {
        String strPhoto = sharedPref.getString(PHOTO_KEY, "");
        return gson.fromJson(strPhoto, Item.class);
    }

    @Override
    public void toggleLikePhoto(Item photo) {
        editor = sharedPref.edit();
        editor.putBoolean(IS_PHOTO_LIKED_KEY + getActivePhoto().getId()
                , !isPhotoLiked(photo));
        editor.apply();
    }

    @Override
    public boolean isPhotoLiked(Item photo) {
        return sharedPref.getBoolean(IS_PHOTO_LIKED_KEY + photo.getId()
                , false);
    }
}
