package mbaas.com.nifcloud.androidimageviewapp;

import android.content.Context;
import android.view.View;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static mbaas.com.nifcloud.androidimageviewapp.ImageViewHasDrawableMatcher.hasDrawable;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4ClassRunner.class)
public class ApplicationTest {
    @Rule
    public final ActivityTestRule<MainActivity> main = new ActivityTestRule<>(MainActivity.class, true);

    @Before
    public void init() {
        // Specify a valid string.
    }

    @Test
    public void useAppContext() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Assert.assertEquals("mbaas.com.nifcloud.androidimageviewapp", appContext.getPackageName());
    }

    @Test
    public void validateDisplayImageSuccess() {
        onView(withText("ImageViewSample")).check(matches(isDisplayed()));
        onView(withId(R.id.imgShow)).check(matches(not(hasDrawable())));
        onView(withId(R.id.btnShow)).check(matches(isDisplayed())).perform(click());
        onView(isRoot()).perform(waitFor(800));
        onView(withId(R.id.imgShow)).check(matches(hasDrawable()));
    }

    @Test
    public void validateDisplayImageFail() {
        onView(withText("ImageViewSample")).check(matches(isDisplayed()));
        onView(withId(R.id.imgShow)).check(matches(not(hasDrawable())));
        onView(withId(R.id.btnShow)).check(matches(isDisplayed())).perform(click());
        onView(isRoot()).perform(waitFor(800));
        onView(withText("Notification from NifCloud")).inRoot(isDialog()).check(matches(isDisplayed()));
    }

    /**
     * Perform action of waiting for request API.
     * @param millis The timeout of until when to wait for.
     */
    public static ViewAction waitFor(final long millis) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override
            public String getDescription() {
                return "wait for " + millis + " millis.";
            }

            @Override
            public void perform(final UiController uiController, final View view) {
                uiController.loopMainThreadForAtLeast(millis);
            }
        };
    }
}