package com.project.albumsearch.repository;

import com.project.albumsearch.network.AlbumApi;
import com.project.albumsearch.utils.Utilities;
import com.project.albumsearch.viewmodel.AlbumDetailsModel;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
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
                .compose(applySingleSchedulers())
                .map(albumApiResponse -> AlbumDetailsModel.fromApiResponse(albumApiResponse));
    }

    /**
     * @param <S> Type of object that Single emits.
     * @return a Single that subscribed on IO and observes on Main schedulers.
     */
    public static <S> SingleTransformer<S, S> applySingleSchedulers() {
        return new SingleTransformer<S, S>() {
            @Override
            public SingleSource<S> apply(Single<S> single) {
                return single.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
