package com.example.photops.Models.Storage;

import android.content.Context;

import com.example.photops.Models.Items.Item;

//separating between the current implementation (shared prefs) to its interface
public abstract class Storage {
    protected Context context;

    public Storage(Context context) {
        this.context = context;
    }

    //saving the photo currently being displayed on storage
    public abstract void setActivePhoto(Item photo);
    public abstract Item getActivePhoto();

    //saving the user "like" data
    public abstract void toggleLikePhoto(Item photo);
    public abstract boolean isPhotoLiked(Item photo);
}
