package com.project.albumsearch.dto;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;

public class Query {
    @SerializedName("query") private String mQuery;

    public Query(@NonNull String query) {
        mQuery = query;
    }
}
