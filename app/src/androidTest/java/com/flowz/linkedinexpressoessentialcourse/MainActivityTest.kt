package com.flowz.linkedinexpressoessentialcourse

import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.sqisland.android.espresso.recyclerview.TextViewHolder
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.core.IsNot.not
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

//    @Rule @JvmField
//    var activityRule = ActivityTestRule <MainActivity>(MainActivity::class.java)

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun greet() {
        onView(withId(R.id.greeting))
            .check(matches(withText("")))

        onView(withId(R.id.greet_button))
                .perform(click())
                .check(matches(not(isEnabled())))

        onView(withId(R.id.greeting))
                .check(matches(withText(R.string.hello_2021)))
    }


    @Test
    fun toolbarTitle(){
//        onView(
//            allOf(
//                isAssignableFrom(TextView::class.java),
//                withParent(isAssignableFrom(Toolbar::class.java))))
//            .check(matches(withText(R.string.title)))
        val title = InstrumentationRegistry.getInstrumentation().targetContext.getString(R.string.title)
        onView(isAssignableFrom(Toolbar::class.java))
            .check(matches(withToolbarTitle(title)))
    }

    @Test
    fun clickRecyclerViewItem(){

        onView(withId(R.id.footer))
            .check(matches(not(isDisplayed())))

        onView(withId(R.id.recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition<TextViewHolder>(27, click()))

        onView(withId(R.id.footer))
                .check(matches(withText("27")))
                .check(matches(isDisplayed()))
    }


//    @Test
//    fun clickItem(){
//        onView(withId(R.id.footer))
//            .check(matches(not(isDisplayed())))
//
//        onData(withValue(27))
//            .inAdapterView(withId(R.id.list))
//            .perform(click())
//            .check(matches(isDisplayed()))
//
//        onView(withId(R.id.footer))
//            .check(matches(withText("27")))
//    }

    private fun withValue(value: Int): Matcher<Any>{

        return object: BoundedMatcher<Any, MainActivity.Item>(MainActivity.Item::class.java){
            override fun describeTo(description: Description?) {
                description?.appendText("has value" + value)

            }

            override fun matchesSafely(item: MainActivity.Item?): Boolean {
               return item.toString() == value.toString()
            }

        }
    }

    private fun withToolbarTitle(expectedTitle:CharSequence): Matcher<View> {

        return object: BoundedMatcher<View, Toolbar>(Toolbar::class.java){
            override fun describeTo(description: Description?) {
                description?.appendText("with toolbar title:" + expectedTitle)
            }

            override fun matchesSafely(toolbar: Toolbar?): Boolean {
                return expectedTitle == toolbar?.title
            }

        }

    }
}