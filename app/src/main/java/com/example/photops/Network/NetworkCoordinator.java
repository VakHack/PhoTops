package com.example.photops.Network;

import retrofit2.Retrofit;

public class NetworkCoordinator {
    private InterceptorImp interceptor;
    private ClientHandler clientHandler;

    public NetworkCoordinator(APIParams params) {
        interceptor = new InterceptorImp(params);
        clientHandler = new ClientHandler(params.getBaseUrl(), interceptor);
    }

    public Retrofit getClient() {
        return clientHandler.getClient();
    }
}
