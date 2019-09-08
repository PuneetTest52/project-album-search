package com.project.albumsearch.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import androidx.annotation.NonNull;

public class Utilities {

    public static final String SEARCH_METHOD = "album.search";

    /**
     * Method to load the image into the imageView passed.
     * Using {@link Glide} for the same.
     *
     * @param context   Context
     * @param imageView ImageView where the image needs to be loaded.
     * @param url       the image url.
     */
    public static void loadImage(@NonNull Context context,
                                 @NonNull ImageView imageView,
                                 @NonNull final String url) {

        final ColorDrawable colorDrawable = new ColorDrawable(Color.DKGRAY);
        final RequestOptions requestOptions = RequestOptions
                .placeholderOf(colorDrawable)
                .fitCenter()
                .optionalCenterCrop();
        Glide.with(context)
                .load(url)
                .apply(requestOptions)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
    }

    /**
     * Method to check whether the network is available or not
     *
     * @param context Context
     * @return true of network is available.
     */
    public static boolean isNetworkAvailable(@NonNull final Context context) {
        final ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return connectivityManager.getActiveNetworkInfo() != null
                && (activeNetworkInfo.isConnected() || activeNetworkInfo.isConnectedOrConnecting());
    }
}
