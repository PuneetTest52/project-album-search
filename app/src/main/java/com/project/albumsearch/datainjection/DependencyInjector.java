package com.project.albumsearch.datainjection;

import com.project.albumsearch.App;

import androidx.annotation.NonNull;

public class DependencyInjector {
    private static AppComponent applicationComponent;

    /**
     * Builds the app Component.
     *
     * @param application App instance
     */
    public static void initialize(@NonNull final App application) {
        applicationComponent = DaggerAppComponent.builder()
                .application(application)
                .build();
    }

    @NonNull
    public static AppComponent applicationComponent() {
        return applicationComponent;
    }

    private DependencyInjector() {
    }
}
