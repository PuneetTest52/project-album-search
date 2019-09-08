package com.project.albumsearch.repository;

import com.project.albumsearch.dto.AlbumApiResponse;
import com.project.albumsearch.network.AlbumApi;
import com.project.albumsearch.utils.Utilities;
import com.project.albumsearch.viewmodel.AlbumDetailsModel;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RepositoryImplementation implements Repository {

    private final AlbumApi mAlbumApi;

    @Inject
    public RepositoryImplementation(@NonNull AlbumApi albumApi) {
        mAlbumApi = albumApi;
    }

    /**
     * Returns the list of AlbumDetailsModel after transforming the response fetched.
     *
     * @param searchMethod  Method name to be called , e.g. {@link Utilities#SEARCH_METHOD}
     * @param searchKeyword The user searched input.
     * @return list of AlbumDetailsModel.
     */
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
