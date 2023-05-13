package com.tasdiqdewan.themoviedb.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.tasdiqdewan.themoviedb.data.usecase.GetPopularMoviesUseCase
import com.tasdiqdewan.themoviedb.ui.home.HomePopularMovies
import com.tasdiqdewan.themoviedb.ui.home.HomeScreenState
import com.tasdiqdewan.themoviedb.ui.home.HomeViewModel
import com.tasdiqdewan.utils.dto.MovieDetailsResponseDto
import com.tasdiqdewan.utils.fake.FakeDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class HomeViewModelTest {
    private lateinit var viewModel: HomeViewModel

    private var getPopularMoviesUseCase = mockk<GetPopularMoviesUseCase>() {
        coEvery { execute(any()) } returns FakeDataSource.FAKE_POPULAR_MOVIES_RESPONSE.results
    }

    @get:Rule
    var instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel = HomeViewModel(getPopularMoviesUseCase)
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
        unmockkAll()
    }

    @Test
    fun getPopularMovies_shouldReturnPopularMoviesList() = runTest {
        //when
        viewModel.getPopularMovies()
        val result = viewModel.state.value.popularMovies

        //then
        assertTrue(result is HomePopularMovies.Success)
        assertEquals(FakeDataSource.FAKE_POPULAR_MOVIES_RESPONSE.results, result.popularMoviesList)
        coVerify { getPopularMoviesUseCase.execute(any()) }
    }

    @Test
    fun getPopularMovies_shouldThrowIOException() = runTest {
        //given
        coEvery { getPopularMoviesUseCase.execute(any()) } throws IOException()

        //when
        viewModel.getPopularMovies()
        val result = viewModel.state.value.popularMovies

        //then
        assertTrue(result is HomePopularMovies.Error)
        assertTrue(result.exception is IOException)
        coVerify { getPopularMoviesUseCase.execute(any()) }
    }

    fun getPopularMovies_shouldThrowHTTPException() = runTest {
        //given
        val response: Response<MovieDetailsResponseDto> = Response.error(
            400,
            "{\"key\":[\"somestuff\"]}"
                .toResponseBody("application/json".toMediaTypeOrNull())
        )
        val exception = HttpException(response)
        coEvery { getPopularMoviesUseCase.execute(any()) } throws exception

        //when
        viewModel.getPopularMovies()
        val result = viewModel.state.value.popularMovies

        //then
        assertTrue(result is HomePopularMovies.Error)
        assertTrue(result.exception is HttpException)
        coVerify { getPopularMoviesUseCase.execute(any()) }
    }
}