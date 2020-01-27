package com.example.photops.Models.Network;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class InterceptorImp implements Interceptor {
    APIParams apiParams;

    public InterceptorImp(APIParams apiParams) {
        this.apiParams = apiParams;
    }

    @Override public Response intercept(Interceptor.Chain chain) throws IOException {

        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("member_id", apiParams.getMemberId())
                .addFormDataPart("get_likes", apiParams.getGetLikes())
                .addFormDataPart("limit", apiParams.getLimit())
                .addFormDataPart("start", apiParams.getStart())
                .build();
        Request request = new Request.Builder()
                .url(apiParams.getBaseUrl()+apiParams.getSpecificPath())
                .method(apiParams.getRequestMethod(), body)
                .addHeader("X-API-VERSION", apiParams.getApiVersion())
                .addHeader("X-ENV", apiParams.getEnv())
                .build();

        return chain.proceed(request);
    }
}