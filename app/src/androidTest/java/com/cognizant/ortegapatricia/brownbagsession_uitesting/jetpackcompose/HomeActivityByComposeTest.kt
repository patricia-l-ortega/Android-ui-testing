package com.cognizant.ortegapatricia.brownbagsession_uitesting.jetpackcompose

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
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

class HomeActivityByComposeTest {

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
    fun should_display_home_screen() {
        ActivityScenario.launch(HomeActivity::class.java)

        composeTestRule.onNodeWithText("My Notes").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Add Note").assertIsDisplayed()
    }

    @Test
    fun should_be_able_to_add_and_display_all_notes_in_home_screen() {
        ActivityScenario.launch(HomeActivity::class.java)

        composeTestRule.onNodeWithContentDescription("Add Note").performClick()
        composeTestRule.onNodeWithText("Title").performClick().performTextInput("Test Title")
        composeTestRule.onNodeWithText("Content").performClick().performTextInput("Test Content")

        composeTestRule.onNodeWithContentDescription("Right Button").performClick()

        composeTestRule.onNodeWithText("Test Title").assertIsDisplayed()
        composeTestRule.onNodeWithText("Test Content").assertIsDisplayed()
    }

    @Test
    fun should_be_able_to_add_and_delete_a_note_in_home_screen() {
        ActivityScenario.launch(HomeActivity::class.java)

        composeTestRule.onNodeWithContentDescription("Add Note").performClick()
        composeTestRule.onNodeWithText("Title").performClick().performTextInput("Test Title")
        composeTestRule.onNodeWithText("Content").performClick().performTextInput("Test Content")

        composeTestRule.onNodeWithContentDescription("Right Button").performClick()

        composeTestRule.onNodeWithText("Test Title").assertIsDisplayed()
        composeTestRule.onNodeWithText("Test Content").assertIsDisplayed()

        composeTestRule.onNodeWithContentDescription("Delete Note").performClick()
        composeTestRule.onNodeWithText("Test Title").assertDoesNotExist()
    }

}