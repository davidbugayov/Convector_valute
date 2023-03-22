package com.convector.david_000.convector_valute;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;


import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


/**
 * Created by davidbugayov on 23.09.16.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {


    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void changeText_sameActivity() {
        // Type text and then press the button.
        onView(withId(R.id.content_value_from))
                .perform(typeText("34"), closeSoftKeyboard());
        onView(withId(R.id.calculate)).perform(click());
        onData(withId(R.id.spinner_value_from)).
                inAdapterView(withId(android.R.layout.simple_list_item_2)).atPosition(0);
        onData(withId(R.id.spinner_value_to)).
                inAdapterView(withId(android.R.layout.simple_list_item_2)).atPosition(0);
        // Check that the text was changed.
        onView(withId(R.id.content_value_to)).check(matches(withText("34.0")));
        onView(withId(R.id.course)).check(matches(withText("1.0")));
    }
}
