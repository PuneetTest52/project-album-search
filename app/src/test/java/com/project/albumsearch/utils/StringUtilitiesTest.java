package com.project.albumsearch.utils;

import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.annotation.NonNull;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.converters.Nullable;
import junitparams.naming.TestCaseName;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class StringUtilitiesTest {

    @Test
    @Parameters({"Sample|Sample", "|", "null|"})
    @TestCaseName("emptyStringIfNull input:{0} expected:{1}")
    public void emptyStringIfNull_whenDifferentInputs_returnExpected(@Nullable String input,
                                                                     @NonNull String expected) {
        assertThat(StringUtilities.emptyStringIfNull(input)).isEqualTo(expected);
    }

    @Test
    @Parameters({"Sample|false", "|true", "null|true"})
    @TestCaseName("isEmpty input:{0} expected:{1}")
    public void isEmpty_whenDifferentInputs_returnExpected(@Nullable String input,
                                                           boolean expected) {
        assertThat(StringUtilities.isEmpty(input)).isEqualTo(expected);
    }
}
