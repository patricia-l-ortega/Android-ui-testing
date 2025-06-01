package com.cognizant.ortegapatricia.brownbagsession_uitesting.jetpackcompose

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginJetpackComposeActivityByComposeTest {

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
    fun should_enabled_login_button_if_fields_has_value() {
        ActivityScenario.launch(LoginJetpackComposeActivity::class.java)

        composeTestRule.onNodeWithText("Username").performTextInput("validUsername")
        composeTestRule.onNodeWithText("Password").performTextInput("validPassword")

        composeTestRule.onNodeWithText("Login").assertIsEnabled()
    }

    @Test
    fun should_disabled_login_button_if_fields_are_empty() {
        ActivityScenario.launch(LoginJetpackComposeActivity::class.java)

        composeTestRule.onNodeWithText("Username").performTextInput("")
        composeTestRule.onNodeWithText("Password").performTextInput("")

        composeTestRule.onNodeWithText("Login").assertIsNotEnabled()
    }

    @Test
    fun should_show_alert_dialog_when_failed_login() {
        ActivityScenario.launch(LoginJetpackComposeActivity::class.java)

        composeTestRule.onNodeWithText("Username").performTextInput("validUsername")
        composeTestRule.onNodeWithText("Password").performTextInput("validPassword")

        composeTestRule.onNodeWithText("Login").assertIsEnabled().performClick()

        composeTestRule.onNodeWithText("Login Failed").assertIsDisplayed()
        composeTestRule.onNodeWithText("Username or password does not match").assertIsDisplayed()
    }

    @Test
    fun should_launch_Home_Activity_if_Login_is_successful() {
        ActivityScenario.launch(LoginJetpackComposeActivity::class.java)

        composeTestRule.onNodeWithText("Username").performTextInput("voojwan")
        composeTestRule.onNodeWithText("Password").performTextInput("Test12345")

        composeTestRule.onNodeWithText("Login").performClick()

        Intents.intended(hasComponent(HomeActivity::class.java.name))
    }
}