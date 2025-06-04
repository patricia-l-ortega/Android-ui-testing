package com.cognizant.ortegapatricia.brownbagsession_uitesting.xmllayout

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.cognizant.ortegapatricia.brownbagsession_uitesting.R
import org.junit.After
import org.junit.Before
import org.junit.Test

class HomeXMLActivityTest {

    @Before
    fun setUp() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun should_display_home_screen() {
        // Launch the HomeXMLActivity
        ActivityScenario.launch(HomeXMLActivity::class.java)

        // Check if the RecyclerView is displayed
         onView(withText("My Notes")).check(matches(isDisplayed()))

        // Check if the Add button is displayed
         onView(withId(R.id.addButton)).check(matches(isDisplayed()))
    }

    @Test
    fun should_be_able_to_add_and_display_all_notes_in_home_screen() {

        ActivityScenario.launch(HomeXMLActivity::class.java)

        onView(withId(R.id.addButton)).perform(click())

        onView(withId(R.id.titleEditText)).perform(click()).perform(typeText("Test Title"))
        onView(withId(R.id.contentEditText)).perform(click()).perform(typeText("Test Content"))

        onView(withId(R.id.action_save)).perform(click())

        onView(withText("Test Title")).check(matches(isDisplayed()))
        onView(withText("Test Content")).check(matches(isDisplayed()))
    }

    @Test
    fun should_be_able_to_add_and_delete_a_note_in_home_screen() {
        ActivityScenario.launch(HomeXMLActivity::class.java)

        onView(withId(R.id.addButton)).perform(click())

        onView(withId(R.id.titleEditText)).perform(click()).perform(typeText("Test Title"))
        onView(withId(R.id.contentEditText)).perform(click()).perform(typeText("Test Content"))

        onView(withId(R.id.action_save)).perform(click())

        // Check if the note is displayed
        onView(withText("Test Title")).check(matches(isDisplayed()))
        onView(withText("Test Content")).check(matches(isDisplayed()))

        // Perform click on the delete button
        onView(withId(R.id.deleteButton)).perform(click())
        // Check if the note is no longer displayed
        onView(withText("Test Title")).check((doesNotExist()))
    }
}