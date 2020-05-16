package com.example.kotlin


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.*
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class NoteListActivityEspressoTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(NoteListActivity::class.java)

    @Test
    fun noteListActivityEspressoTest() {
        val floatingActionButton = onView(
            allOf(
                withId(R.id.fab),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        floatingActionButton.perform(click())

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        Thread.sleep(300)

        val appCompatSpinner = onView(
            allOf(
                withId(R.id.spCourses),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("androidx.coordinatorlayout.widget.CoordinatorLayout")),
                        1
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatSpinner.perform(click())

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        Thread.sleep(250)

//        val appCompatCheckedTextView = onData(anything())
//            .inAdapterView(
//                childAtPosition(
//                    withClassName(`is`("android.widget.PopupWindow.PopupBackgroundView")),
//                    0
//                )
//            )
//            .atPosition(1)
//        appCompatCheckedTextView.perform(click())

//        val appCompatEditText = onView(
//            allOf(
//                withId(R.id.etNoteTitle),
//                childAtPosition(
//                    childAtPosition(
//                        withClassName(`is`("androidx.coordinatorlayout.widget.CoordinatorLayout")),
//                        1
//                    ),
//                    3
//                ),
//                isDisplayed()
//            )
//        )
//        appCompatEditText.perform(replaceText("xxxx"), closeSoftKeyboard())
//
//        val appCompatEditText2 = onView(
//            allOf(
//                withId(R.id.etNoteText),
//                childAtPosition(
//                    childAtPosition(
//                        withClassName(`is`("androidx.coordinatorlayout.widget.CoordinatorLayout")),
//                        1
//                    ),
//                    0
//                ),
//                isDisplayed()
//            )
//        )
//        appCompatEditText2.perform(replaceText("Prueba"), closeSoftKeyboard())

        pressBack()
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
