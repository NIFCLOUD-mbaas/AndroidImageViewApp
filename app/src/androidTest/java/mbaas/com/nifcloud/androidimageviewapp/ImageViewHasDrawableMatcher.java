package mbaas.com.nifcloud.androidimageviewapp;

import android.view.View;
import android.widget.ImageView;
import androidx.test.espresso.matcher.BoundedMatcher;
import org.hamcrest.Description;

public class ImageViewHasDrawableMatcher {
    public static BoundedMatcher<View, ImageView> hasDrawable() {
        return new BoundedMatcher<View, ImageView>(ImageView.class) {
            @Override
            protected boolean matchesSafely(ImageView imageView) {
                return imageView.getDrawable() != null;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("has drawable");
            }
        };
    }
}
