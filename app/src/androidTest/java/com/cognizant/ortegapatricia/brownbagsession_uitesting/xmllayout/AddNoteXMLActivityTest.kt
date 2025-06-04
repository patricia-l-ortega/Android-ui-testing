package com.cognizant.ortegapatricia.brownbagsession_uitesting.xmllayout

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.cognizant.ortegapatricia.brownbagsession_uitesting.R
import org.junit.After
import org.junit.Before
import org.junit.Test

class AddNoteXMLActivityTest {

    @Before
    fun setUp() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun should_display_add_note_screen() {
        // Launch the AddNoteXMLActivity
        ActivityScenario.launch(AddNoteXMLActivity::class.java)

        // Check if the title EditText is displayed
        onView(withId(R.id.titleEditText)).check(matches(isDisplayed()))

        // Check if the content EditText is displayed
        onView(withId(R.id.contentEditText)).check(matches(isDisplayed()))

        // Check if the save button is displayed
        onView(withId(R.id.action_save)).check(matches(isDisplayed()))
    }

    @Test
    fun should_display_error_message_if_fields_are_empty() {
        // Launch the AddNoteXMLActivity
        ActivityScenario.launch(AddNoteXMLActivity::class.java)

        // Attempt to save without entering any data
        onView(withId(R.id.action_save)).perform(click())

        // Check if the error message is displayed
        onView(withText("Title and Content cannot be empty")).inRoot(RootMatchers.isDialog())
            .check(matches(isDisplayed()))
    }

    @Test
    fun should_save_note_and_finish_activity() {
        // Launch the AddNoteXMLActivity
        ActivityScenario.launch(AddNoteXMLActivity::class.java)

        // Enter title and content
        onView(withId(R.id.titleEditText)).perform(click()).perform(typeText("Test Title"))
        onView(withId(R.id.contentEditText)).perform(click()).perform(typeText("Test Content"))

        // Click the save button
        onView(withId(R.id.action_save)).perform(click())

        ActivityScenario.launch(HomeXMLActivity::class.java)
        onView(withId(R.id.titleTextView)).check(matches(withText("Test Title")))
        onView(withId(R.id.contentTextView)).check(matches(withText("Test Content")))
        onView(withId(R.id.deleteButton)).check(matches(isDisplayed()))
    }
}