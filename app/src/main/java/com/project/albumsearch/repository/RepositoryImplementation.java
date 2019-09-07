package com.project.albumsearch.repository;

import com.project.albumsearch.dto.AlbumApiResponse;
import com.project.albumsearch.network.AlbumApi;
import com.project.albumsearch.viewmodel.AlbumDetailsModel;

import java.util.List;

import androidx.annotation.NonNull;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RepositoryImplementation implements Repository {

    private final AlbumApi mAlbumApi;

    public RepositoryImplementation(AlbumApi albumApi) {
        mAlbumApi = albumApi;
    }

    @Override
    public Single<List<AlbumDetailsModel>> getAlbumDetails(@NonNull final String searchMethod,
                                                           @NonNull final String searchKeyword) {
        return mAlbumApi.getAlbumsResponse(searchMethod, searchKeyword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<AlbumApiResponse, List<AlbumDetailsModel>>() {
                    @Override
                    public List<AlbumDetailsModel> apply(AlbumApiResponse albumApiResponse) {
                        return AlbumDetailsModel.fromApiResponse(albumApiResponse);
                    }
                });
    }
}
