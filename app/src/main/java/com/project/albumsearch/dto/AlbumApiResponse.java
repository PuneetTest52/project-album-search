package com.project.albumsearch.dto;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;

public class AlbumApiResponse {

    @SerializedName("results") private AlbumResults mAlbumResults;

    @NonNull
    public AlbumResults getAlbumResults() {
        return mAlbumResults;
    }
}
