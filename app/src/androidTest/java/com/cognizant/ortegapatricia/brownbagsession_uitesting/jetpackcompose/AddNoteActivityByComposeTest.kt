package com.cognizant.ortegapatricia.brownbagsession_uitesting.jetpackcompose

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.intent.Intents
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AddNoteActivityByComposeTest {

    @Before
    fun setUp() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun should_display_add_note_screen() {
        ActivityScenario.launch(AddNoteActivity::class.java)

        composeTestRule.onNodeWithText("Title").assertIsDisplayed()
        composeTestRule.onNodeWithText("Content").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Right Button").assertIsDisplayed()
    }

    @Test
    fun should_display_error_message_if_fields_are_empty() {
        ActivityScenario.launch(AddNoteActivity::class.java)

        composeTestRule.onNodeWithContentDescription("Right Button").performClick()
        composeTestRule.onNodeWithText("Title and Content cannot be empty").assertIsDisplayed()
    }

    @Test
    fun should_save_note_and_finish_activity() {
        ActivityScenario.launch(AddNoteActivity::class.java)

        composeTestRule.onNodeWithText("Title").performClick().performTextInput("Test Title")
        composeTestRule.onNodeWithText("Content").performClick().performTextInput("Test Content")

        composeTestRule.onNodeWithContentDescription("Right Button").performClick()

        ActivityScenario.launch(HomeActivity::class.java)
        composeTestRule.onAllNodesWithTag("NoteItem").assertCountEquals(1)
        composeTestRule.onNodeWithText("Test Title").assertIsDisplayed()
        composeTestRule.onNodeWithText("Test Content").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Delete Note").performClick()

        //        composeTestRule.onAllNodesWithText("Test Title")[0].assertIsDisplayed()
//        composeTestRule.onAllNodesWithText("Test Content")[0].assertIsDisplayed()
    }

}