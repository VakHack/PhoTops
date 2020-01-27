package com.example.photops.Network;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClientHandler {
    private Retrofit retrofit;
    private Interceptor interceptor;
    private String baseUrl;

    public ClientHandler(String baseUrl, Interceptor interceptor) {
        this.baseUrl = baseUrl;
        this.interceptor = interceptor;

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Retrofit getClient() {
        return retrofit;
    }
}
