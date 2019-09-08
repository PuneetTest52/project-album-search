package com.project.albumsearch.datainjection;

import com.project.albumsearch.App;

public class DependencyInjector {
    private static AppComponent applicationComponent;

    public static void initialize(App application) {
        applicationComponent = DaggerAppComponent.builder()
                .application(application)
                .build();
    }

    public static AppComponent applicationComponent() {
        return applicationComponent;
    }

    private DependencyInjector() {
    }
}
