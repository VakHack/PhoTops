package com.example.photops.Models.Network;

import retrofit2.Retrofit;

public class NetworkCoordinator {
    private InterceptorImp interceptor;
    private RetrofitClientGenerator retrofitClientGenerator;

    public NetworkCoordinator(APIParams params) {
        interceptor = new InterceptorImp(params);
        retrofitClientGenerator = new RetrofitClientGenerator(params.getBaseUrl(), interceptor);
    }

    public Retrofit getClient() {
        return retrofitClientGenerator.getClient();
    }
}
