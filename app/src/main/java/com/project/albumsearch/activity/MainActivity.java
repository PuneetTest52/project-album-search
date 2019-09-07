package com.project.albumsearch.activity;

import android.os.Bundle;

import com.project.albumsearch.R;
import com.project.albumsearch.fragment.AlbumSearchFragment;
import com.project.albumsearch.handlers.NavigationalHandler;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class MainActivity extends BaseActivity implements NavigationalHandler {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            loadFragment(AlbumSearchFragment.newInstance(), false);
        }
    }

    @Override
    public void loadFragment(@NonNull Fragment fragment,
                             final boolean isAddToBackStack) {
        loadNewFragment(fragment, isAddToBackStack);
    }
}
