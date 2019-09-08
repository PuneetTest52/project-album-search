package com.project.albumsearch.viewmodel;

import com.project.albumsearch.handlers.OnErrorHandler;
import com.project.albumsearch.repository.Repository;
import com.project.albumsearch.utils.Utilities;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class AlbumViewModel extends ViewModel {

    private MutableLiveData<List<AlbumDetailsModel>> mAlbumApiResponseData = new MutableLiveData<>();

    private final Repository mRepository;
    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public AlbumViewModel(Repository repository) {
        mRepository = repository;
    }

    public void getSearchedAlbums(@NonNull final String searchKeyword,
                                  @NonNull final OnErrorHandler onErrorHandler) {
        addDisposable(mRepository.getAlbumDetails(Utilities.SEARCH_METHOD, searchKeyword)
                .subscribe(new Consumer<List<AlbumDetailsModel>>() {
                    @Override
                    public void accept(List<AlbumDetailsModel> albumDetailsModels) {
                        mAlbumApiResponseData.setValue(albumDetailsModels);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        onErrorHandler.onError(throwable.getLocalizedMessage());
                    }
                }));
    }

    public MutableLiveData<List<AlbumDetailsModel>> getAlbumApiResponseData() {
        return mAlbumApiResponseData;
    }

    private void addDisposable(@NonNull final Disposable disposable) {
        mCompositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        mCompositeDisposable.clear();
        super.onCleared();
    }
}
