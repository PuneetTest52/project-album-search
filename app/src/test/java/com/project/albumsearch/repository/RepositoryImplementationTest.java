package com.project.albumsearch.repository;

import com.project.albumsearch.dto.Album;
import com.project.albumsearch.dto.AlbumApiResponse;
import com.project.albumsearch.dto.AlbumImage;
import com.project.albumsearch.dto.AlbumMatches;
import com.project.albumsearch.dto.AlbumResults;
import com.project.albumsearch.network.AlbumApi;
import com.project.albumsearch.repository.Repository;
import com.project.albumsearch.repository.RepositoryImplementation;
import com.project.albumsearch.viewmodel.AlbumDetailsModel;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.observers.TestObserver;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RepositoryImplementationTest {

    private static final String ARTIST_NAME = "ARTIST";
    private static final String ALBUM_NAME = "ALBUM";
    private static final String ALBUM_IMAGE = "IMAGE";
    private static final String ALBUM_IMAGE_SIZE = "small";

    private Repository mRepository;

    @Mock private AlbumApi mAlbumApi;
    @Mock private AlbumApiResponse mAlbumApiResponse;
    @Mock private AlbumResults mAlbumResults;
    @Mock private AlbumMatches mAlbumMatches;
    @Mock private Album mAlbum;
    @Mock private AlbumImage mAlbumImage;
    @Mock private Throwable mThrowable;

    @Before
    public void setUp() {
        mRepository = new RepositoryImplementation(mAlbumApi);
    }

    @Test
    public void getAlbumDetails_whenApiSuccess_returnsListOfAlbums() {
        mockAlbumApiResponse();
        when(mAlbumApi.getAlbumsResponse(anyString(), anyString()))
                .thenReturn(Single.just(mAlbumApiResponse));

        final TestObserver<List<AlbumDetailsModel>> test =
                mRepository.getAlbumDetails(anyString(), anyString()).test();
        test.assertComplete();
        test.assertValue(albumDetailsModels -> albumDetailsModels.size() > 0);
    }

    @Test
    public void getAlbumDetails_whenApiFails_returnsError() {
        mockAlbumApiResponse();
        when(mAlbumApi.getAlbumsResponse(anyString(), anyString()))
                .thenReturn(Single.error(mThrowable));

        final TestObserver<List<AlbumDetailsModel>> test =
                mRepository.getAlbumDetails(anyString(), anyString()).test();
        test.assertError(mThrowable);
    }

    public void mockAlbumApiResponse() {
        when(mAlbumApiResponse.getAlbumResults()).thenReturn(mAlbumResults);
        when(mAlbumResults.getAlbumMatches()).thenReturn(mAlbumMatches);

        when(mAlbum.getArtist()).thenReturn(ARTIST_NAME);
        when(mAlbum.getName()).thenReturn(ALBUM_NAME);
        when(mAlbumImage.getImage()).thenReturn(ALBUM_IMAGE);
        when(mAlbumImage.getSize()).thenReturn(ALBUM_IMAGE_SIZE);
        when(mAlbum.getAlbumImages()).thenReturn(Collections.singletonList(mAlbumImage));

        when(mAlbumMatches.getAlbumsList()).thenReturn(Collections.singletonList(mAlbum));
    }

    @BeforeClass
    public static void before() {
        RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> Schedulers.trampoline());
    }
}
