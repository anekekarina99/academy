package com.dicoding.motive.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressBack
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.dicoding.motive.R
import com.dicoding.motive.ui.favor.m.MovieFavorAdapter
import com.dicoding.motive.ui.favor.t.TvFavorAdapter
import com.dicoding.motive.utils.DataDummy
import com.dicoding.motive.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test


class HomeActivityTest {

    private val dummyMovie = DataDummy.generateDummyMovie()
    private val dummyTv = DataDummy.generateDummyTv()

    @Before
    fun setUp() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMovie() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))

    }


    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_desc)).check(matches(isDisplayed()))
        onView(withId(R.id.text_description)).check(matches(isDisplayed()))
        onView(withId(R.id.text_date)).check(matches(isDisplayed()))
        onView(isRoot()).perform(pressBack())
    }

    @Test
    fun loadTv() {
        onView(withText("Tv")).perform(click())
        onView(withId(R.id.rv_bookmark)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_bookmark)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTv.size))

    }





    @Test
    fun loadDetailTv() {
        onView(withText("Tv")).perform(click())
        onView(withId(R.id.rv_bookmark)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_desc)).check(matches(isDisplayed()))
        onView(withId(R.id.text_description)).check(matches(isDisplayed()))
        onView(withId(R.id.text_date)).check(matches(isDisplayed()))
        onView(isRoot()).perform(pressBack())
    }

    @Test
    fun loadFavorM(){
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
        onView(withId(R.id.btnFav)).perform(click())
        onView(isRoot()).perform(pressBack())
        onView(withId(R.id.btnFavor)).perform(click())
        onView(withText("Movie Favorite")).perform(click())
        onView(withId(R.id.rv_favorM)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_favorM)).perform(RecyclerViewActions.actionOnItemAtPosition<MovieFavorAdapter.ViewHolder>(0, click()))
        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_desc)).check(matches(isDisplayed()))
        onView(withId(R.id.text_description)).check(matches(isDisplayed()))
        onView(withId(R.id.text_date)).check(matches(isDisplayed()))
        onView(withId(R.id.btnFav)).perform(click())
        onView(isRoot()).perform(pressBack())


    }

    @Test
    fun loadFavorT(){
        onView(withText("Tv")).perform(click())
        onView(withId(R.id.rv_bookmark)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
        onView(withId(R.id.btnFav)).perform(click())
        onView(isRoot()).perform(pressBack())
        onView(withId(R.id.btnFavor)).perform(click())
        onView(withText("Tv Favorite")).perform(click())
        onView(withId(R.id.rv_favorT)).check(matches(isDisplayed()))
        Thread.sleep(1000)
        onView(withId(R.id.rv_favorT)).perform(RecyclerViewActions.actionOnItemAtPosition<TvFavorAdapter.ViewHolder>(0, click()))
        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_desc)).check(matches(isDisplayed()))
        onView(withId(R.id.text_description)).check(matches(isDisplayed()))
        onView(withId(R.id.text_date)).check(matches(isDisplayed()))
        onView(withId(R.id.btnFav)).perform(click())
        onView(isRoot()).perform(pressBack())
    }




}