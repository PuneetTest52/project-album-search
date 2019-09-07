package com.project.albumsearch.utils;

import androidx.annotation.Nullable;
import io.reactivex.annotations.NonNull;

public class StringUtilities {

    private static final String EMPTY_STRING = "";

    @NonNull
    public static String emptyStringIfNull(@Nullable final String string) {
        return isEmpty(string) ? EMPTY_STRING : string;
    }

    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }
}
