package com.example.vinilosapp.ui

import android.view.KeyEvent
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.pressKey
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.vinilosapp.R
import com.example.vinilosapp.ui.adapter.AlbumViewHolder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchListInstrumentedTest {


    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun check_search_box() {
        //this test will fail until the exact item count of the list is known and can be asserted
        Espresso.onView(withId(R.id.search_repo)).check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun test_search() {
        Thread.sleep(5000)
        Espresso.onView(withId(R.id.search_repo))
            .perform(typeText("Opera"), pressKey(KeyEvent.KEYCODE_ENTER))
        Thread.sleep(5000)
        Espresso.onView(withId(R.id.list)).check(ViewAssertions.matches(isDisplayed()))
    }


    @Test
    fun test_search_and_list_click() {
        Thread.sleep(3000)
        Espresso.onView(withId(R.id.search_repo))
            .perform(typeText("Opera"), pressKey(KeyEvent.KEYCODE_ENTER))
        Thread.sleep(5000)
        Espresso.onView(withId(R.id.list)).check(ViewAssertions.matches(isDisplayed()))
        Thread.sleep(3000)
        Espresso.onView(withId(R.id.list))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<AlbumViewHolder>(
                    0,
                    ViewActions.click()
                )
            )
    }


    @Test
    fun test_details_screen() {
        Thread.sleep(2000)
        Espresso.onView(withId(R.id.search_repo))
            .perform(typeText("Opera"), pressKey(KeyEvent.KEYCODE_ENTER))
        Thread.sleep(3000)
        Espresso.onView(withId(R.id.list)).check(ViewAssertions.matches(isDisplayed()))

        Thread.sleep(3000)
        Espresso.onView(withId(R.id.list))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<AlbumViewHolder>(
                    0,
                    ViewActions.click()
                )
            )

        Espresso.onView(withId(R.id.artwork)).check(
            ViewAssertions.matches(
                isDisplayed()
            )
        )

        Thread.sleep(3000)

        Espresso.onView(withId(R.id.tracks_title)).check(ViewAssertions.matches(withText("Rock")))
    }
}