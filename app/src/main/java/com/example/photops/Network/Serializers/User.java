package com.example.photops.Network.Serializers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("total_likes")
    @Expose
    private String likes;
    @SerializedName("instagram_username")
    @Expose
    private String username;

    public String getLikes() {
        return likes;
    }
    public void setLikes(String likes){
        this.likes = likes;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}