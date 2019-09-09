package com.project.albumsearch.activity;

import android.os.Bundle;

import com.project.albumsearch.R;
import com.project.albumsearch.fragment.AlbumSearchFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            loadFragment(AlbumSearchFragment.newInstance(), false);
        }
    }
}
