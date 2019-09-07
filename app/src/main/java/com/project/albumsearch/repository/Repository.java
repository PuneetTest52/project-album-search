package com.project.albumsearch.repository;

import com.project.albumsearch.viewmodel.AlbumDetailsModel;

import java.util.List;

import androidx.annotation.NonNull;
import io.reactivex.Single;

public interface Repository {

    Single<List<AlbumDetailsModel>> getAlbumDetails(@NonNull final String searchMethod,
                                                    @NonNull final String searchKeyword);
}
