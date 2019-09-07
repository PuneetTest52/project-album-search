package com.project.albumsearch.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.albumsearch.viewmodel.AlbumDetailsModel;
import com.project.albumsearch.R;
import com.project.albumsearch.utils.Utilities;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;

public class AlbumDetailsFragment extends BaseFragment {

    private static final String ARG_ALBUM_DETAILS = "arg_album_details";
    @BindView(R.id.iv_album) ImageView mAlbumImage;
    @BindView(R.id.tv_albumName) TextView mAlbumName;
    @BindView(R.id.tv_artistName) TextView mArtistName;

    public static AlbumDetailsFragment newInstance(@NonNull final AlbumDetailsModel albumDetailsModels) {
        AlbumDetailsFragment albumDetailsFragment = new AlbumDetailsFragment();
        final Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_ALBUM_DETAILS, albumDetailsModels);
        albumDetailsFragment.setArguments(bundle);
        return albumDetailsFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_album_details_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Bundle bundle = getArguments();
        if (bundle != null) {
            final AlbumDetailsModel albumDetailsModels = bundle.getParcelable(ARG_ALBUM_DETAILS);
            Utilities.loadImage(requireContext(), mAlbumImage, albumDetailsModels.getLargeImage());
            mAlbumName.setText(albumDetailsModels.getName());
            mArtistName.setText(albumDetailsModels.getArtist());
        }
    }
}
