package com.project.albumsearch.viewmodel;

import com.project.albumsearch.repository.Repository;
import com.project.albumsearch.utils.StringUtilities;
import com.project.albumsearch.utils.Utilities;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class AlbumViewModel extends ViewModel {

    private final MutableLiveData<List<AlbumDetailsModel>> mAlbumApiResponseData;
    private final MutableLiveData<String> mErrorData;
    private final Repository mRepository;
    private final CompositeDisposable mCompositeDisposable;

    @Inject
    public AlbumViewModel(Repository repository) {
        mRepository = repository;
        mAlbumApiResponseData = new MutableLiveData<>();
        mErrorData = new MutableLiveData<>();
        mCompositeDisposable = new CompositeDisposable();
    }

    /**
     * Loads the list of album into LiveData based on the keyword searched by the user.
     *
     * @param searchKeyword User input to be searched.
     */
    public void getSearchedAlbums(@NonNull final String searchKeyword) {
        addDisposable(mRepository.getAlbumDetails(Utilities.SEARCH_METHOD, searchKeyword)
                .subscribe(albumDetailsModels -> mAlbumApiResponseData.setValue(albumDetailsModels),
                        throwable -> mErrorData
                                .setValue(StringUtilities.emptyStringIfNull(throwable.getLocalizedMessage()))));
    }

    public MutableLiveData<List<AlbumDetailsModel>> getAlbumApiResponseData() {
        return mAlbumApiResponseData;
    }

    public MutableLiveData<String> getErrorData() {
        return mErrorData;
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
