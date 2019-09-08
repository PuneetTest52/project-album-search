package com.project.albumsearch.activity;

import android.os.Bundle;

import com.project.albumsearch.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity {

    public FragmentManager mFragmentManager;

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
    protected void loadNewFragment(@NonNull Fragment fragment, final boolean isAddToBackStack) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.baseContainer, fragment);
        if (isAddToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }
}
