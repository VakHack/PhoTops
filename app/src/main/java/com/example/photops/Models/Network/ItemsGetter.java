package com.example.photops.Models.Network;

import com.example.photops.Models.Items.ItemsList;

import retrofit2.Call;
import retrofit2.http.POST;

public interface ItemsGetter {
    @POST("rest_mobile/get_photos_public/")
    Call<ItemsList> getItems();
}
