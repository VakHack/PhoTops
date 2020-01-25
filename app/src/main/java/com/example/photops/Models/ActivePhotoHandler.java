package com.example.photops.Models;

import android.content.Context;

public abstract class ActivePhotoHandler {
    protected Context context;

    public ActivePhotoHandler(Context context) {
        this.context = context;
    }

    public abstract void setActivePhoto(Photo photo);
    public abstract Photo getActivePhoto();
    public abstract void likePhoto();
    public abstract boolean isPhotoLiked();
}
