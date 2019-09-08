package com.project.albumsearch.utils;

import androidx.annotation.Nullable;
import io.reactivex.annotations.NonNull;

public class StringUtilities {

    public static final String EMPTY_STRING = "";

    private StringUtilities() throws IllegalAccessException {
        throw new IllegalAccessException("Instance is not allowed for this");
    }

    /**
     * Method to return empty string if the string passed is null.
     *
     * @param string String to be checked if null
     * @return Empty string if null else return back the string passed.
     */
    @NonNull
    public static String emptyStringIfNull(@Nullable final String string) {
        return isEmpty(string) ? EMPTY_STRING : string;
    }

    /**
     * Method to check if the string is null or not.
     *
     * @param charSequence charSequence to be validated.
     * @return true if null
     */
    static boolean isEmpty(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }
}
