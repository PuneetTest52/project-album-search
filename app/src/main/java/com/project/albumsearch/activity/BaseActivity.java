package com.project.albumsearch.activity;

import android.os.Bundle;

import com.project.albumsearch.R;
import com.project.albumsearch.handlers.NavigationalHandler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity implements NavigationalHandler {

    @Nullable public FragmentManager mFragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mFragmentManager = getSupportFragmentManager();
    }

    /**
     * Adds the new fragment to the activity.
     *
     * @param fragment         Fragment to be added.
     * @param isAddToBackStack true of fragment to be added to the backStack.
     */
    @Override
    public void loadFragment(@NonNull Fragment fragment,
                             final boolean isAddToBackStack) {
        if (mFragmentManager == null) {
            mFragmentManager = getSupportFragmentManager();
        }
        final FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.baseContainer, fragment);
        if (isAddToBackStack) {
            fragmentTransaction.addToBackStack(fragment.getTag());
        }
        fragmentTransaction.commit();
    }

    /**
     * Enables back navigation.
     *
     * @param shouldShowBackNavigation true if should show back navigation.
     */
    @Override
    public void shouldShowBackNavigation(boolean shouldShowBackNavigation) {
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(shouldShowBackNavigation);
        }
    }

    /**
     * Set the screen title for the visible fragment.
     *
     * @param screenTitle screen title.
     */
    @Override
    public void setScreenTitle(@NonNull final String screenTitle) {
        setTitle(screenTitle);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
