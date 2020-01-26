package com.example.photops.Network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UnsplashClientHandler {
    public static final String UNSPLASH_ACCESS_KEY = "0d22a326c931a0213b3e3aa7e4ec887585ddc2788a6a6261e2b71b9fdebc167f";
    public static final String UNSPLASH_URL_PREFIX = "https://api.unsplash.com/";
    
    private static Retrofit retrofit = null;
    public static Retrofit getUnsplashClient() {
        if (retrofit == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new HeaderHandler(UNSPLASH_ACCESS_KEY)).build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(UNSPLASH_URL_PREFIX)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
