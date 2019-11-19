package com.example.glasgowaccidents;

import android.app.Activity;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)

public class List_accidentsTest {

    private static final int FINDONLIST = 1;

    @Rule
    public ActivityTestRule<List_accidents> List_accidentsTestRule =
            new ActivityTestRule<>(List_accidents.class);

    @Test
    public void recycleTest(){
        onView(withId(R.id.accidents_recyclerView)).perform(
                RecyclerViewActions.actionOnItemAtPosition(1,click()));
    }

    @Test
    public void recycleScrollTest(){

        RecyclerView recyclerView = List_accidentsTestRule.getActivity().findViewById(R.id.accidents_recyclerView);

        int itemCount = recyclerView.getAdapter().getItemCount();

        onView(withId(R.id.accidents_recyclerView)).perform(RecyclerViewActions.scrollToPosition(itemCount-1));

    }

    @Test
    public void recyclerFindTest(){

        onView(withId(R.id.accidents_recyclerView)).perform
                (RecyclerViewActions.actionOnItemAtPosition(FINDONLIST, click()));

        String itemVal = "Index : 201897GA00102";

        onView(withText(itemVal)).check(matches(isDisplayed()));
    }

    @Test
    public void filterTest(){
        onView(withId(R.id.icon_filter)).perform(click());
        onView(withText("Filter")).check(matches(isDisplayed()));
    }

}