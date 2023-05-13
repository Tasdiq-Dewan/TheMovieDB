package com.tasdiqdewan.themoviedb.data.usecase

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.tasdiqdewan.themoviedb.data.repository.MoviesRepository
import com.tasdiqdewan.utils.dto.MovieDetailsResponseDto
import com.tasdiqdewan.utils.fake.FakeDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import kotlin.test.assertFailsWith

@ExperimentalCoroutinesApi
class GetPopularMoviesUseCaseTest {
    private lateinit var getPopularMoviesUseCase: GetPopularMoviesUseCase

    private val repository = mockk<MoviesRepository>() {
        coEvery { getPopularMoviesList() } returns Response.success(FakeDataSource.FAKE_POPULAR_MOVIES_RESPONSE_DTO)
    }

    @get:Rule
    var instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        getPopularMoviesUseCase = GetPopularMoviesUseCaseImpl(repository)
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
    fun getPopularMoviesUseCase_shouldReturnPopularMoviesList() = runTest {
        //when
        val result = getPopularMoviesUseCase.execute()

        //then
        assertEquals(FakeDataSource.FAKE_POPULAR_MOVIES_RESPONSE.results, result)
        coVerify { repository.getPopularMoviesList(any()) }
    }

    @Test
    fun getPopularMoviesUseCase_shouldThrowIOException() = runTest {
        //given
        coEvery { repository.getPopularMoviesList(any()) } throws IOException()

        //then
        assertFailsWith<IOException> {
            getPopularMoviesUseCase.execute()
        }
        coVerify { repository.getPopularMoviesList(any()) }
    }

    @Test
    fun getPopularMoviesUseCase_shouldThrowHttpException() = runTest {
        //given
        val response: Response<MovieDetailsResponseDto> = Response.error(
            400,
            "{\"key\":[\"somestuff\"]}"
                .toResponseBody("application/json".toMediaTypeOrNull())
        )
        val exception = HttpException(response)
        coEvery { repository.getPopularMoviesList(any()) } throws exception

        //then
        assertFailsWith<HttpException> {
            getPopularMoviesUseCase.execute()
        }
        coVerify { repository.getPopularMoviesList(any()) }
    }
}