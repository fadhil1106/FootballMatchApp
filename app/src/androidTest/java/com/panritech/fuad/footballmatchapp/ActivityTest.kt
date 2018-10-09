package com.panritech.fuad.footballmatchapp

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.panritech.fuad.footballmatchapp.R.id.*
import com.panritech.fuad.footballmatchapp.activity.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ActivityTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)
    private val delayTime = 2000L

    @Test
    fun testAppBehavior() {
        //test Match Fragment Behavior
        testMatchListBehavior()
        testDetailBehavior()
        //test Next Match Fragment Behavior
        onView(withId(navigation_next_match)).perform(click())
        testMatchListBehavior()
        testDetailBehavior()
    }

    private fun testMatchListBehavior() {
        Thread.sleep(delayTime)
        onView(withId(listMatch)).check(matches(isDisplayed()))
        onView(withId(listMatch)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(14))
        onView(withId(listMatch)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(14, click()))
    }

    private fun testDetailBehavior() {
        Thread.sleep(delayTime)
        onView(withId(detailTable)).check(matches(isDisplayed()))
        onView(withId(homeBadge)).check(matches(isDisplayed()))
        onView(withId(awayBadge)).check(matches(isDisplayed()))

        onView(withId(add_to_favorite)).perform(click())
        pressBack()
    }
}