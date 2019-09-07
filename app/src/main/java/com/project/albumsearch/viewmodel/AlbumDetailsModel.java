package com.project.albumsearch.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.project.albumsearch.dto.Album;
import com.project.albumsearch.dto.AlbumApiResponse;
import com.project.albumsearch.dto.AlbumImage;
import com.project.albumsearch.dto.AlbumMatches;
import com.project.albumsearch.dto.AlbumResults;
import com.project.albumsearch.utils.StringUtilities;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public class AlbumDetailsModel implements Parcelable {

    private static final String EMPTY_STRING = "";
    private static final String IMAGE_SIZE_THUMBNAIL = "small";
    private static final String IMAGE_SIZE_LARGE = "extralarge";

    private final String mName;
    private final String mArtist;
    private final String mThumbnailImage;
    private final String mLargeImage;

    private AlbumDetailsModel(@NonNull final Album albumApiResponse) {
        mName = albumApiResponse.getName();
        mArtist = albumApiResponse.getArtist();
        mThumbnailImage = getImageUrl(albumApiResponse.getAlbumImages(), IMAGE_SIZE_THUMBNAIL);
        mLargeImage = getImageUrl(albumApiResponse.getAlbumImages(), IMAGE_SIZE_LARGE);
    }

    private AlbumDetailsModel(Parcel in) {
        mName = in.readString();
        mArtist = in.readString();
        mThumbnailImage = in.readString();
        mLargeImage = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeString(mArtist);
        dest.writeString(mThumbnailImage);
        dest.writeString(mLargeImage);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AlbumDetailsModel> CREATOR = new Creator<AlbumDetailsModel>() {
        @Override
        public AlbumDetailsModel createFromParcel(Parcel in) {
            return new AlbumDetailsModel(in);
        }

        @Override
        public AlbumDetailsModel[] newArray(int size) {
            return new AlbumDetailsModel[size];
        }
    };

    @NonNull
    public static List<AlbumDetailsModel> fromApiResponse(@NonNull final AlbumApiResponse albumApiResponse) {
        List<AlbumDetailsModel> albumDetailsModels = new ArrayList<>();
        AlbumResults albumResults = albumApiResponse.getAlbumResults();
        AlbumMatches albumMatches = albumResults.getAlbumMatches();
        List<Album> albums = albumMatches.getAlbumsList();
        for (Album album : albums) {
            albumDetailsModels.add(new AlbumDetailsModel(album));
        }
        return albumDetailsModels;
    }

    @NonNull
    public String getName() {
        return StringUtilities.emptyStringIfNull(mName);
    }

    @NonNull
    public String getArtist() {
        return StringUtilities.emptyStringIfNull(mArtist);
    }

    @NonNull
    public String getThumbnailImage() {
        return StringUtilities.emptyStringIfNull(mThumbnailImage);
    }

    @NonNull
    public String getLargeImage() {
        return StringUtilities.emptyStringIfNull(mLargeImage);
    }

    @NonNull
    private String getImageUrl(@NonNull final List<AlbumImage> albumImages,
                               @NonNull final String size) {
        for (AlbumImage albumImage : albumImages) {
            if (size.equalsIgnoreCase(albumImage.getSize())) {
                return albumImage.getImage();
            }
        }
        return EMPTY_STRING;
    }
}
