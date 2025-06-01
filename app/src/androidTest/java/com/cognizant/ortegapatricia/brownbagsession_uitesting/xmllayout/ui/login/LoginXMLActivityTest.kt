package com.cognizant.ortegapatricia.brownbagsession_uitesting.xmllayout.ui.login

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cognizant.ortegapatricia.brownbagsession_uitesting.jetpackcompose.HomeActivity
import com.cognizant.ortegapatricia.brownbagsession_uitesting.R
import com.cognizant.ortegapatricia.brownbagsession_uitesting.xmllayout.LoginXMLActivity
import org.hamcrest.CoreMatchers.not
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginXMLActivityTest {

    @Before
    fun setUp() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun should_enabled_login_button_if_fields_has_value() {
        ActivityScenario.launch(LoginXMLActivity::class.java)

        onView(withId(R.id.username))
            .perform(typeText("validUsername"), closeSoftKeyboard())
        onView(withId(R.id.password))
            .perform(typeText("validPassword"), closeSoftKeyboard())

        onView(withId(R.id.login))
            .check(matches(isEnabled()))
    }

    @Test
    fun should_disabled_login_button_if_fields_are_empty() {
        ActivityScenario.launch(LoginXMLActivity::class.java)

        onView(withId(R.id.username))
            .perform(typeText(""), closeSoftKeyboard())
        onView(withId(R.id.password))
            .perform(typeText(""), closeSoftKeyboard())

        onView(withId(R.id.login))
            .check(matches(not(isEnabled())))
    }

    @Test
    fun should_show_alert_dialog_when_failed_login() {
        ActivityScenario.launch(LoginXMLActivity::class.java)

        onView(withId(R.id.username))
            .perform(typeText("validUsername"), closeSoftKeyboard())
        onView(withId(R.id.password))
            .perform(typeText("validPassword"), closeSoftKeyboard())

        onView(withId(R.id.login))
            .check(matches(isEnabled())).perform(click())

        onView(withText("Login Failed")).check(matches(isDisplayed()))
        onView(withText("Username or password does not match")).check(matches(isDisplayed()))
    }

    @Test
    fun should_launch_Home_Activity_if_Login_is_successful() {
        ActivityScenario.launch(LoginXMLActivity::class.java)

        onView(withId(R.id.username))
            .perform(typeText("voojwan"), closeSoftKeyboard())
        onView(withId(R.id.password))
            .perform(typeText("Test12345"), closeSoftKeyboard())

        onView(withId(R.id.login)).perform(click())

        Intents.intended(hasComponent(HomeActivity::class.java.name))
    }
}