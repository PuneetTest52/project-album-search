package com.project.albumsearch;

import com.project.albumsearch.datainjection.DependencyInjector;

import androidx.multidex.MultiDexApplication;

public class App extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        DependencyInjector.initialize(this);
    }
}
