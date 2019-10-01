package com.project.albumsearch.datainjection;

import com.project.albumsearch.App;
import com.project.albumsearch.fragment.AlbumSearchFragment;

import dagger.Component;

@Component(modules = {
        ViewModelModule.class,
        NetworkModule.class
})
@AppScope
public interface AppComponent {

    void inject(AlbumSearchFragment fragment);

    void inject(App app);

    @Component.Builder
    interface Builder {

        AppComponent build();
    }
}
