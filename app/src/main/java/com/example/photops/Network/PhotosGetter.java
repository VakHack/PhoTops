package com.example.photops.Network;

import com.example.photops.Data.Photo.ItemsList;

import retrofit2.Call;
import retrofit2.http.POST;

public interface PhotosGetter {
    @POST("rest_mobile/get_photos_public/")
    Call<ItemsList> getPhotos();
}
