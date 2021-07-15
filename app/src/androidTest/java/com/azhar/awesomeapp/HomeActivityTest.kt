package com.azhar.awesomeapp

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.azhar.awesomeapp.core.util.EspressoIdlingResource
import com.azhar.awesomeapp.ui.home.HomeActivity
import com.azhar.awesomeapp.util.PhotographDummy
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {

    private val photographData = PhotographDummy.dummyFotograph()

    @get:Rule
    var activityRule = ActivityTestRule(HomeActivity::class.java)

    @Before
    fun setUp(){
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @Test
    fun loadPhotographData(){
        Espresso.onView(withId(R.id.rvPhotograph)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rvPhotograph)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                photographData.size
            )
        )
    }

    @Test
    fun loadDetailPhotograph(){
        Espresso.onView(withId(R.id.rvPhotograph)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )

        Espresso.onView(withId(R.id.tvPhotographNameDetail)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tvPhotoUrl)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }

    @Test
    fun loadDataPhotographError(){
        if (photographData.size == 0) {
            Espresso.onView(withId(R.id.tvErrorNetwork)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            Espresso.onView(withId(R.id.tvErrorNetwork))
                .check(ViewAssertions.matches(ViewMatchers.withText("Opps!! Tidak bisa terhubung ke server")))
        }
    }
}