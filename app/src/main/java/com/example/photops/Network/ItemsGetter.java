package com.example.photops.Network;

import com.example.photops.Data.Photo.ItemsList;

import retrofit2.Call;
import retrofit2.http.POST;

public interface ItemsGetter {
    @POST("rest_mobile/get_photos_public/")
    Call<ItemsList> getItems();
}
