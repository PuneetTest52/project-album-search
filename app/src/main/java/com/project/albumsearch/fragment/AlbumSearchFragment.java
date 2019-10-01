package com.project.albumsearch.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.project.albumsearch.R;
import com.project.albumsearch.adapter.AlbumsAdapter;
import com.project.albumsearch.datainjection.DependencyInjector;
import com.project.albumsearch.handlers.NavigationalHandler;
import com.project.albumsearch.utils.Utilities;
import com.project.albumsearch.viewmodel.AlbumDetailsModel;
import com.project.albumsearch.viewmodel.AlbumViewModel;
import com.project.albumsearch.viewmodel.CustomViewModelFactory;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class AlbumSearchFragment extends BaseFragment implements AlbumsAdapter.OnItemSelectListener {

    @BindView(R.id.rv_album_list) RecyclerView mRecyclerView;
    @BindView(R.id.sv_userSearch) SearchView mSearchView;

    private NavigationalHandler mNavigationalHandler;
    private AlbumViewModel mAlbumViewModel;
    private ProgressDialog mProgressDialog;

    @Inject CustomViewModelFactory mViewModelFactory;

    public static AlbumSearchFragment newInstance() {
        return new AlbumSearchFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mNavigationalHandler = (NavigationalHandler) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context + " must implement NavigationalHandler");
        }
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DependencyInjector
                .applicationComponent()
                .inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_album_search_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        configureToolBarDetails();
        setUpView();

        mAlbumViewModel = ViewModelProviders.of(this, mViewModelFactory).get(AlbumViewModel.class);
        mAlbumViewModel.getAlbumApiResponseData().observe(getViewLifecycleOwner(), albumDetailsModels -> {
            setAlbumAdapter(albumDetailsModels);
            mProgressDialog.hide();
        });

        mAlbumViewModel.getErrorData().observe(getViewLifecycleOwner(), errorMessage -> {
            Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
            mProgressDialog.hide();
        });

        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
    }

    private void getSearchResults(@NonNull final String userSearch) {
        mProgressDialog.show();
        mAlbumViewModel.getSearchedAlbums(userSearch);
    }

    private void setAlbumAdapter(@NonNull final List<AlbumDetailsModel> albumDetailsModels) {
        mRecyclerView.setAdapter(new AlbumsAdapter(albumDetailsModels, this));
    }

    @Override
    public void onItemSelected(@NonNull final AlbumDetailsModel album) {
        mNavigationalHandler.loadFragment(AlbumDetailsFragment.newInstance(album), true);
    }

    private void setUpView() {
        mProgressDialog = new ProgressDialog(requireContext());
        mProgressDialog.setTitle(getString(R.string.progress_dialog_title));
        mProgressDialog.setCancelable(false);

        mSearchView.setIconified(false);
        mSearchView.clearFocus();
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String userSearch) {
                getResults(userSearch);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String userSearch) {
                return false;
            }
        });
    }

    private void getResults(@NonNull final String userSearch) {
        final Context context = requireContext();
        if (Utilities.isNetworkAvailable(context)) {
            getSearchResults(userSearch);
        } else {
            Toast.makeText(context, getString(R.string.no_network), Toast.LENGTH_LONG).show();
        }
        mSearchView.clearFocus();
    }

    private void configureToolBarDetails() {
        mNavigationalHandler.shouldShowBackNavigation(false);
        mNavigationalHandler.setScreenTitle(getString(R.string.screen_title_album_search));
    }
}
