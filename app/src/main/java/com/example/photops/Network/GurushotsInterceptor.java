package com.example.photops.Network;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class GurushotsInterceptor implements Interceptor {

    @Override public Response intercept(Interceptor.Chain chain) throws IOException {

        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("member_id", "2a49ab04b1534574e578a08b8f9d7441")
                .addFormDataPart("get_likes", "true")
                .addFormDataPart("limit", "50")
                .addFormDataPart("start", "0")
                .build();
        Request request = new Request.Builder()
                .url("https://api.gurushots.com/rest_mobile/get_photos_public")
                .method("POST", body)
                .addHeader("X-API-VERSION", "20")
                .addHeader("X-ENV", "ANDROID")
                .build();

        return chain.proceed(request);
    }
}