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

@Module
public class NetworkModule {

    private static final String BASE_URL = "http://ws.audioscrobbler.com/";
    private static final long TIMEOUT_CONNECT_IN_SEC = 10;
    private static final long TIMEOUT_READ_IN_SEC = 15;
    private static final long TIMEOUT_WRITE_IN_SEC = 15;

    @Provides
    @AppScope
    AlbumApi getAlbumApi(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
                .create(AlbumApi.class);
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
        builder.connectTimeout(TIMEOUT_CONNECT_IN_SEC, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_READ_IN_SEC, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_WRITE_IN_SEC, TimeUnit.SECONDS);
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
