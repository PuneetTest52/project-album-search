package com.project.albumsearch.adapter;

import android.view.ViewGroup;

import com.project.albumsearch.viewmodel.AlbumDetailsModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AlbumsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<AlbumDetailsModel> mAlbumDetailsModelList;
    private final AlbumsAdapter.OnItemSelectListener mItemSelectListener;

    public AlbumsAdapter(@NonNull final List<AlbumDetailsModel> albums,
                         @NonNull final AlbumsAdapter.OnItemSelectListener itemSelectListener) {
        mAlbumDetailsModelList = albums;
        mItemSelectListener = itemSelectListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AlbumDetailsViewHolder(parent, mItemSelectListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof AlbumDetailsViewHolder) {
            ((AlbumDetailsViewHolder) holder).bind(mAlbumDetailsModelList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mAlbumDetailsModelList.size();
    }

    public interface OnItemSelectListener {
        void onItemSelected(@NonNull AlbumDetailsModel item);
    }
}
