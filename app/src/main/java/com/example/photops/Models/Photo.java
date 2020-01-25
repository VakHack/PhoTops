package com.example.photops.Models;

import com.example.photops.Models.Networking.Urls;

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