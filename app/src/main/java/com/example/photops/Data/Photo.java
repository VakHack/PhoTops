package com.example.photops.Data;

import com.example.photops.Network.Urls;

import java.io.Serializable;

public class Photo implements Serializable {
    private Urls urls;

    public Photo(Urls urls) {
        this.urls = urls;
    }

    public Urls getUrls() {
        return urls;
    }
}