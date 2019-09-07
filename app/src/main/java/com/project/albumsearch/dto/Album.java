package com.project.albumsearch.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import androidx.annotation.NonNull;

public class Album {

    @SerializedName("name") private String mName;
    @SerializedName("artist") private String mArtist;
    @SerializedName("image") private List<AlbumImage> mAlbumImages;

    @NonNull
    public String getName() {
        return mName;
    }

    @NonNull
    public String getArtist() {
        return mArtist;
    }

    @NonNull
    public List<AlbumImage> getAlbumImages() {
        return mAlbumImages;
    }
}
