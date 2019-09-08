package com.project.albumsearch;

import com.project.albumsearch.handlers.OnErrorHandler;
import com.project.albumsearch.repository.Repository;
import com.project.albumsearch.viewmodel.AlbumDetailsModel;
import com.project.albumsearch.viewmodel.AlbumViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import io.reactivex.Single;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AlbumViewModelTest {

    @Rule public InstantTaskExecutorRule mInstantTaskExecutorRule = new InstantTaskExecutorRule();

    private static final String ARTIST_NAME = "ARTIST";
    private static final String ERROR = "ERROR";

    @Mock Repository mRepository;
    @Mock private AlbumDetailsModel mAlbumDetailsModel;
    @Mock private Throwable mThrowable;
    @Mock OnErrorHandler mOnErrorHandler;

    private AlbumViewModel mAlbumViewModel;

    @Before
    public void setUp() {
        mAlbumViewModel = new AlbumViewModel(mRepository);
    }

    @Test
    public void getSearchedAlbums_whenApiSuccess_returnsListOfAlbums() {
        when(mRepository.getAlbumDetails(anyString(), anyString()))
                .thenReturn(Single.just(Collections.singletonList(mAlbumDetailsModel)));

        mAlbumViewModel.getSearchedAlbums(ARTIST_NAME, mOnErrorHandler);

        assertThat(mAlbumViewModel.getAlbumApiResponseData().getValue()).isNotNull();
    }

    @Test
    public void getSearchedAlbums_whenApiFails_returnsErrorMessage() {
        when(mThrowable.getLocalizedMessage()).thenReturn(ERROR);
        when(mRepository.getAlbumDetails(anyString(), anyString()))
                .thenReturn(Single.error(mThrowable));

        mAlbumViewModel.getSearchedAlbums(ARTIST_NAME, mOnErrorHandler);

        verify(mOnErrorHandler).onError(ERROR);
    }
}
