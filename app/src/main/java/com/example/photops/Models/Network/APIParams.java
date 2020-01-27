package com.example.photops.Models.Network;

public class APIParams {
    private String requestMethod;
    private String baseUrl;
    private String specificPath;
    private String apiVersion;
    private String env;
    private String memberId;
    private String getLikes;
    private String limit;
    private String start;


    public APIParams(String requestMethod, String baseUrl, String specificPath,
                     String apiVersion, String env, String memberId,
                     String getLikes, String limit, String start) {

        this.requestMethod = requestMethod;
        this.baseUrl = baseUrl;
        this.specificPath = specificPath;
        this.apiVersion = apiVersion;
        this.env = env;
        this.memberId = memberId;
        this.getLikes = getLikes;
        this.limit = limit;
        this.start = start;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getSpecificPath() {
        return specificPath;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public String getEnv() {
        return env;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getGetLikes() {
        return getLikes;
    }

    public String getLimit() {
        return limit;
    }

    public String getStart() {
        return start;
    }
}
