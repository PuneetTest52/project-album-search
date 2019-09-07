package com.project.albumsearch.dto;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;

public class AlbumImage {

    @SerializedName("#text") private String mImage;
    @SerializedName("size") private String mSize;

    @NonNull
    public String getImage() {
        return mImage;
    }

    @NonNull
    public String getSize() {
        return mSize;
    }
}
