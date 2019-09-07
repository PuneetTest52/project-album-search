package com.project.albumsearch.viewmodel;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class CustomViewModelFactory implements ViewModelProvider.Factory {

    private final Map<Class<? extends ViewModel>, Provider<ViewModel>> mProviderMap;

    @Inject
    public CustomViewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> providerMap) {
        mProviderMap = providerMap;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) mProviderMap.get(modelClass).get();
    }
}

