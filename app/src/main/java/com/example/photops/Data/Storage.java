package com.example.photops.Data;

import android.content.Context;

//separating between the current implementation (shared prefs) to its interface
public abstract class Storage {
    protected Context context;

    public Storage(Context context) {
        this.context = context;
    }

    public abstract void setActivePhoto(Photo photo);
    public abstract Photo getActivePhoto();
    public abstract void toggleLikePhoto(Photo photo);
    public abstract boolean isPhotoLiked(Photo photo);
}
