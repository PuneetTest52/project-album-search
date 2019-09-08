package com.project.albumsearch.network;

import com.project.albumsearch.dto.AlbumApiResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AlbumApi {

    @GET("/2.0/")
    Single<AlbumApiResponse> getAlbumsResponse(@Query("method") final String searchMethod,
                                               @Query("album") final String albumName);
}
