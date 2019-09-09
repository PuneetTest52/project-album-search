package com.project.albumsearch.dto;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AlbumResults {

    @SerializedName("albummatches") private AlbumMatches mAlbumMatches;

    @NonNull
    public AlbumMatches getAlbumMatches() {
        return mAlbumMatches;
    }
}
