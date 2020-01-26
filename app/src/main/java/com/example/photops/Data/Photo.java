package com.example.photops.Data;

import com.example.photops.Network.Serializers.User;
import com.example.photops.Network.Serializers.Urls;

import java.io.Serializable;

public class Photo implements Serializable {
    private Urls urls;
    private User user;

    public Photo(Urls urls, User user) {
        this.urls = urls;
        this.user = user;
    }

    public Urls getUrls() {
        return urls;
    }
    public void setUrls(Urls urls) {
        this.urls = urls;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}