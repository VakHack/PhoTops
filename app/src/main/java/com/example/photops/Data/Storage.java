package com.example.photops.Data;

import android.content.Context;

//separating between the current implementation (shared prefs) to its interface
public abstract class Storage {
    protected Context context;

    public Storage(Context context) {
        this.context = context;
    }

    public abstract void setActivePhoto(Item photo);
    public abstract Item getActivePhoto();
    public abstract void toggleLikePhoto(Item photo);
    public abstract boolean isPhotoLiked(Item photo);
}
