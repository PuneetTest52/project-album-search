package com.project.albumsearch.datainjection;

import com.project.albumsearch.App;

import androidx.annotation.NonNull;

public class DependencyInjector {
    private static AppComponent applicationComponent;

    /**
     * Builds the app Component.
     *
     */
    public static void initialize() {
        applicationComponent = DaggerAppComponent.builder()
                .build();
    }

    @NonNull
    public static AppComponent applicationComponent() {
        return applicationComponent;
    }

    private DependencyInjector() {
    }
}
