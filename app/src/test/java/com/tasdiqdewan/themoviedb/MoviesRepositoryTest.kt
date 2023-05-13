package com.tasdiqdewan.themoviedb

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.tasdiqdewan.themoviedb.data.ApiServices
import com.tasdiqdewan.themoviedb.data.repository.MoviesRepository
import com.tasdiqdewan.themoviedb.ui.details.DetailsViewModel
import com.tasdiqdewan.utils.fake.FakeDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import retrofit2.Response
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
class MoviesRepositoryTest {
    private lateinit var repository: MoviesRepository

    private val apiServices = mockk<ApiServices>(relaxed = true) {
        coEvery { getPopularMoviesList(any()) } returns Response.success(FakeDataSource.FAKE_POPULAR_MOVIES_RESPONSE)
        coEvery { getMovieDetails(any()) } returns Response.success(FakeDataSource.FAKE_SPIDER_VERSE_MOVIE_DETAILS_DTO)
        coEvery { getMovieReleaseDates(any()) } returns Response.success(FakeDataSource.FAKE_SPIDER_VERSE_RELEASE_DATE_DTO)
    }

    @get:Rule
    var instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        repository = MoviesRepository(apiServices)
        mockkStatic(Log::class) {
            every { Log.d(any(), any()) } returns 0
            every { Log.e(any(), any()) } returns 0
        }
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
    }

    @Test
    fun getPopularMoviesList_shouldReturnMoviesList() = runTest {
        //when
        val result = repository.getPopularMoviesList()

        //then
        assertEquals(FakeDataSource.FAKE_POPULAR_MOVIES_RESPONSE, result.body())
        assertTrue(result.isSuccessful)
        coVerify {
            apiServices.getPopularMoviesList(any())
        }
    }

    @Test
    fun getMovieDetails_shouldReturnMovieDetails() = runTest {
        //when
        val result = repository.getMovieDetails(324857)

        //then
        assertEquals(FakeDataSource.FAKE_SPIDER_VERSE_MOVIE_DETAILS_DTO, result.body())
        assertTrue(result.isSuccessful)
        coVerify {
            apiServices.getMovieDetails(any())
        }
    }

    @Test
    fun getMovieReleaseDates_shouldReturnReleaseDates() = runTest {
        //when
        val result = repository.getMovieReleaseDates(324857)

        //then
        assertEquals(FakeDataSource.FAKE_SPIDER_VERSE_RELEASE_DATE_DTO, result.body())
        assertTrue(result.isSuccessful)
        coVerify {
            apiServices.getMovieReleaseDates(any())
        }
    }
}