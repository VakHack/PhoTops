package com.example.photops.Models;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.photops.Models.Networking.Urls;

public class SharedPrefsAppDB extends AppDB {
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private String APP_KEY = "photops";
    private String STATE_KEY = "state";
    private String ACTIVE_PHOTO_KEY = "active_photo";
    private SharedPreferences.OnSharedPreferenceChangeListener listener;

    public SharedPrefsAppDB(Context context) {
        super(context);
        sharedPref = context.getSharedPreferences(APP_KEY, Context.MODE_PRIVATE);
        setAppState("start");
    }

    @Override
    public void setAppState(String state) {
        editor = sharedPref.edit();
        editor.putString(STATE_KEY, state);
        editor.apply();
    }

    @Override
    public String getAppState() {
        return sharedPref.getString(STATE_KEY, "empty");
    }

    @Override
    public void waitOnAppState(StateChangeActionHandler actionHandler) {
         listener = (prefs, key) -> {
             actionHandler.performOnStateChange();
         };
        sharedPref.registerOnSharedPreferenceChangeListener(listener);
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
}
