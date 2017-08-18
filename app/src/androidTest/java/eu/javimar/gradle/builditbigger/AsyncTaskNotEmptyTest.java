package eu.javimar.gradle.builditbigger;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import eu.javimar.gradle.display.DisplayJokeActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;


@RunWith(AndroidJUnit4.class)
public class AsyncTaskNotEmptyTest
{

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void buttonShouldDisplayJoke(){
        onView(withId(R.id.b_tell_joke)).perform(click());
    }

    @Rule
    public ActivityTestRule<DisplayJokeActivity> mActivityRuleJoke =
            new ActivityTestRule<>(DisplayJokeActivity.class);
    @Test
    public void buttonShouldDisplayJoke2() {

        //mActivityRuleJoke.getActivity().findViewById(R.id.tv_joke)

        onView(withId(R.id.tv_joke)).check(matches(not(withText(""))));
    }

}
