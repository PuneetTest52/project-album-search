package com.project.albumsearch.datainjection;

import com.project.albumsearch.BuildConfig;
import com.project.albumsearch.network.AlbumApi;
import com.project.albumsearch.network.ApiCallInterceptor;
import com.project.albumsearch.repository.Repository;
import com.project.albumsearch.repository.RepositoryImplementation;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This is a module with all the dependencies needed for network connections
 */
@Module
class NetworkModule {

    private static final String BASE_URL = "http://ws.audioscrobbler.com/";
    private static final long CONNECTION_TIMEOUT_SEC = 10;

    @Provides
    @AppScope
    AlbumApi getAlbumApi(Retrofit retrofit) {
        return retrofit.create(AlbumApi.class);
    }

    @Provides
    @AppScope
    Retrofit getRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    @AppScope
    OkHttpClient providesOkHttpClient() {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(httpLoggingInterceptor);
        }
        builder.connectTimeout(CONNECTION_TIMEOUT_SEC, TimeUnit.SECONDS)
                .readTimeout(CONNECTION_TIMEOUT_SEC, TimeUnit.SECONDS)
                .writeTimeout(CONNECTION_TIMEOUT_SEC, TimeUnit.SECONDS);
        return builder
                .addInterceptor(new ApiCallInterceptor())
                .build();
    }

    @Provides
    @AppScope
    Repository getRepository(AlbumApi albumApi) {
        return new RepositoryImplementation(albumApi);
    }
}
