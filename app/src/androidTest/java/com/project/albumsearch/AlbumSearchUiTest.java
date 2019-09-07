package com.project.albumsearch;

import android.widget.EditText;

import com.project.albumsearch.activity.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class AlbumSearchUiTest {

    private static final long WAIT_TIME = 2000;
    private static final int POSITION = 0;
    private static final String SEARCH_TEXT = "Rock";
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void noListDisplayed() {
        onView(withId(R.id.rv_album_list)).check(matches(not(isDisplayed())));
    }

    @Test
    public void enterSearchTextAndFetchResults() throws InterruptedException {
        onView(withId(R.id.sv_userSearch)).perform(click());
        onView(isAssignableFrom(EditText.class)).perform(typeText(SEARCH_TEXT), pressImeActionButton());
        Thread.sleep(WAIT_TIME);
        onView(withId(R.id.rv_album_list)).check(matches(isDisplayed()));
    }

    @Test
    public void selectItemAndProceedToNextScreen() throws InterruptedException {
        enterSearchTextAndFetchResults();
        onView(withId(R.id.rv_album_list)).perform(
                RecyclerViewActions.actionOnItemAtPosition(POSITION, click()));
        onView(withId(R.id.tv_artistName)).check(matches(isDisplayed()));
    }
}
