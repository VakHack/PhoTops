package com.example.photops.Data.Photo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemsList
{
    @SerializedName("items")
    @Expose
    private List<Item> items = null;
    @SerializedName("success")
    @Expose
    private Boolean success;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}

