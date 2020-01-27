package com.example.photops.Network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientHandler {

    public static final String GURUSHOTS_BASE_URL = "https://api.gurushots.com/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new GurushotsInterceptor()).build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(GURUSHOTS_BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
