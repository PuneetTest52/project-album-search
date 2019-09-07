package com.project.albumsearch.network;

import java.io.IOException;

import androidx.annotation.NonNull;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ApiCallInterceptor implements Interceptor {

    private static final String API_KEY = "api_key";
    private static final String API_KEY_VALUE = "e5d90d0c5b2db90d0a83716ffcb9cfd9";
    private static final String FORMAT_TYPE = "format";
    private static final String FORMAT_TYPE_VALUE = "json";

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();

        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter(API_KEY, API_KEY_VALUE)
                .addQueryParameter(FORMAT_TYPE, FORMAT_TYPE_VALUE)
                .build();

        // Request customization: add request headers
        return chain.proceed(original.newBuilder().url(url).build());
    }
}
