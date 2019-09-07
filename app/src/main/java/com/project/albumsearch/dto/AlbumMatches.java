package com.project.albumsearch.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import androidx.annotation.NonNull;

public class AlbumMatches {

    @SerializedName("album") List<Album> mAlbums;

    @NonNull
    public List<Album> getAlbumsList() {
        return mAlbums;
    }
}
