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

    public static boolean isNetworkAvailable(@NonNull final Context context) {
        final ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return connectivityManager.getActiveNetworkInfo() != null
                && (activeNetworkInfo.isConnected() || activeNetworkInfo.isConnectedOrConnecting());
    }
}
