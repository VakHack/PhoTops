package com.example.photops.Models;

import android.content.Context;

public abstract class AppDB {
    protected Context context;

    public AppDB(Context context) {
        this.context = context;
    }

    public abstract void setActivePhoto(Photo photo);
    public abstract void setAppState(String state);
    public abstract String getAppState();
    public abstract void waitOnAppState(StateChangeActionHandler actionHandler);
    public abstract Photo getActivePhoto();
}
