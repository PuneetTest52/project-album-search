package com.project.albumsearch.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.albumsearch.viewmodel.AlbumDetailsModel;
import com.project.albumsearch.R;
import com.project.albumsearch.utils.Utilities;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AlbumDetailsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.iv_album_Image) ImageView mAlbumImage;
    @BindView(R.id.tv_album_name) TextView mAlbumName;
    @BindView(R.id.tv_artist_name) TextView mArtistName;

    private final AlbumsAdapter.OnItemSelectListener mItemSelectListener;
    private AlbumDetailsModel mAlbumDetailsModel;

    public AlbumDetailsViewHolder(@NonNull ViewGroup parent,
                                  @NonNull AlbumsAdapter.OnItemSelectListener listener) {
        super(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_album_list_item, parent, false));
        ButterKnife.bind(this, itemView);
        mItemSelectListener = listener;
    }

    @OnClick(R.id.list_item_view)
    public void onItemClick() {
        mItemSelectListener.onItemSelected(mAlbumDetailsModel);
    }

    public void bind(@NonNull final AlbumDetailsModel albumDetailsModel) {
        mAlbumDetailsModel = albumDetailsModel;
        Utilities.loadImage(itemView.getContext(), mAlbumImage, mAlbumDetailsModel.getThumbnailImage());
        mAlbumName.setText(mAlbumDetailsModel.getName());
        mArtistName.setText(mAlbumDetailsModel.getArtist());
    }
}
