package id.ac.ui.cs.mobileprogramming.razrinn.helloworld

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {

    private lateinit var helloWorld: String
    private lateinit var zero: String
    private lateinit var one: String

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity>
            = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun initValidString() {
        // Specify a valid string.
        helloWorld = "Hello World!"
        zero = "0"
        one = "1"
    }
    @Test
    fun helloWorld_text_isExist() {
        // Check that the text was changed.
        onView(withId(R.id.text1))
            .check(matches(withText(helloWorld)))
    }
    @Test
    fun counter_text_isExist(){
        onView(withId(R.id.counter))
            .check(matches(withText(zero)))
    }
    @Test
    fun counter_isIncremented(){
        onView(withId(R.id.counter))
            .check(matches(withText(zero)))

        onView(withId(R.id.button2)).perform(click())

        onView(withId(R.id.counter))
            .check(matches(withText(one)))

    }
}