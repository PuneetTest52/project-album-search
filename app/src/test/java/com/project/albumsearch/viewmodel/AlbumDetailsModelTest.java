package com.project.albumsearch.viewmodel;

import com.project.albumsearch.dto.Album;
import com.project.albumsearch.dto.AlbumApiResponse;
import com.project.albumsearch.dto.AlbumImage;
import com.project.albumsearch.dto.AlbumMatches;
import com.project.albumsearch.dto.AlbumResults;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AlbumDetailsModelTest {

    private static final String ARTIST_NAME = "ARTIST";
    private static final String ALBUM_NAME = "ALBUM";
    private static final String ALBUM_IMAGE = "IMAGE";
    private static final String ALBUM_IMAGE_SIZE = "small";
    private static final String ALBUM_IMAGE_INVALID_SIZE = "invalid_size";

    @Mock private AlbumApiResponse mAlbumApiResponse;
    @Mock private AlbumResults mAlbumResults;
    @Mock private AlbumMatches mAlbumMatches;
    @Mock private Album mAlbum;
    @Mock private AlbumImage mAlbumImage;

    private AlbumDetailsModel mAlbumDetailsModel;

    @Before
    public void setUp() {
        mAlbumDetailsModel = new AlbumDetailsModel(mAlbum);
    }

    @Test
    public void getImageUrl_whenValidSize_returnsImageUrl() {
        mockAlbumApiResponse();
        final String imageUrl = mAlbumDetailsModel.getImageUrl(Collections.singletonList(mAlbumImage),
                ALBUM_IMAGE_SIZE);
        assertThat(imageUrl).isEqualTo(ALBUM_IMAGE);
    }

    @Test
    public void getImageUrl_whenInValidSize_returnsEmptyString() {
        mockAlbumApiResponse();
        final String imageUrl = mAlbumDetailsModel.getImageUrl(Collections.singletonList(mAlbumImage),
                ALBUM_IMAGE_INVALID_SIZE);
        assertThat(imageUrl).isEmpty();
    }

    @Test
    public void fromApiResponse_whenApiResponse_returnsDetailsModel() {
        mockAlbumApiResponse();
        final List<AlbumDetailsModel> albumDetailsModels = AlbumDetailsModel.fromApiResponse(mAlbumApiResponse);
        assertThat(albumDetailsModels).isNotEmpty();
        assertThat(albumDetailsModels.get(0).getArtist()).isEqualTo(ARTIST_NAME);
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
}
