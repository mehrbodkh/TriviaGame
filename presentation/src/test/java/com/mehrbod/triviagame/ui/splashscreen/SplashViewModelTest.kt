package com.mehrbod.triviagame.ui.splashscreen

import com.mehrbod.triviagame.ui.splashscreen.state.SplashUIEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class SplashViewModelTest {

    private lateinit var splashViewModel: SplashViewModel

    private val coroutineDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(coroutineDispatcher)
        splashViewModel = SplashViewModel()
    }

    @Test
    fun `test splash ui event`() = coroutineDispatcher.runBlockingTest {
        val result = splashViewModel.uiEvents.first()

        assertEquals(SplashUIEvent.NavigateToStartScreen, result)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}