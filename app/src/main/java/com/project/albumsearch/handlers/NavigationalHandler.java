package com.project.albumsearch.handlers;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public interface NavigationalHandler {

    void loadFragment(@NonNull Fragment fragment, final boolean isAddToBackStack);

    void shouldShowBackNavigation(boolean shouldShowBackNavigation);

    void setScreenTitle(@NonNull final String screenTitle);
}
