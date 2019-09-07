package com.project.albumsearch.datainjestion;

import com.project.albumsearch.repository.Repository;
import com.project.albumsearch.viewmodel.AlbumViewModel;
import com.project.albumsearch.viewmodel.CustomViewModelFactory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

import javax.inject.Provider;

import androidx.lifecycle.ViewModel;
import dagger.MapKey;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

@Module
public class ViewModelModule {

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @MapKey
    @interface ViewModelKey {
        Class<? extends ViewModel> value();
    }

    @Provides
    @IntoMap
    @ViewModelKey(AlbumViewModel.class)
    ViewModel albumViewModel(Repository repository) {
        return new AlbumViewModel(repository);
    }

    @Provides
    CustomViewModelFactory viewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> providerMap) {
        return new CustomViewModelFactory(providerMap);
    }
}
