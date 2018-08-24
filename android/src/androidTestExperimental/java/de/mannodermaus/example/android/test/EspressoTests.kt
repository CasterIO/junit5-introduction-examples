package de.mannodermaus.example.android.test

import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.closeSoftKeyboard
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import de.mannodermaus.example.android.BlackjackActivity
import de.mannodermaus.example.android.PlayerEntryActivity
import de.mannodermaus.example.android.R
import de.mannodermaus.junit5.ActivityTest
import de.mannodermaus.junit5.Tested
import org.hamcrest.Matchers.allOf
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ActivityTest(
        value = PlayerEntryActivity::class,
        launchFlags = FLAG_ACTIVITY_NEW_TASK,
        launchActivity = false)
class EspressoTests {

    @BeforeEach
    fun beforeEach() {
        Intents.init()
    }

    @AfterEach
    fun afterEach() {
        Intents.release()
    }

    @Test
    fun uiTestWithJUnitJupiter(tested: Tested<PlayerEntryActivity>) {
        tested.launchActivity()

        onView(withId(R.id.etPlayer1)).check(matches(isDisplayed()))

        tested.finishActivity()
    }

    @Test
    fun ifNoNamesAreEnteredThenDefaultIsChosen(tested: Tested<PlayerEntryActivity>) {
        tested.launchActivity()

        onView(withId(R.id.buttonPlay)).perform(click())

        intended(
                allOf(
                        hasComponent(BlackjackActivity::class.java.name),
                        hasExtra(BlackjackActivity.EXTRA_PLAYER1_NAME, "Player 1"),
                        hasExtra(BlackjackActivity.EXTRA_PLAYER2_NAME, "Player 2")
                )
        )
    }

    @Test
    fun enteredNamesAreGivenToTheNextScreen(tested: Tested<PlayerEntryActivity>) {
        tested.launchActivity()

        onView(withId(R.id.etPlayer1)).perform(typeText("Taro"))
        onView(withId(R.id.etPlayer2)).perform(typeText("Hoge"))
        closeSoftKeyboard()
        onView(withId(R.id.buttonPlay)).perform(click())

        intended(
                allOf(
                        hasComponent(BlackjackActivity::class.java.name),
                        hasExtra(BlackjackActivity.EXTRA_PLAYER1_NAME, "Taro"),
                        hasExtra(BlackjackActivity.EXTRA_PLAYER2_NAME, "Hoge")
                )
        )
    }
}
