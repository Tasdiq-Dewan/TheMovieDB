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
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

@ExperimentalCoroutinesApi
class GetLocalMovieReleaseDateUseCaseTest {
    private lateinit var getLocalMovieReleaseDateUseCase: GetLocalMovieReleaseDateUseCase

    private val repository = mockk<MoviesRepository>() {
        coEvery { getMovieReleaseDates(any()) } returns Response.success(FakeDataSource.FAKE_SPIDER_VERSE_RELEASE_DATE_DTO)
    }

    @get:Rule
    var instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        getLocalMovieReleaseDateUseCase = GetLocalMovieReleaseDateUseCaseImpl(repository)
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
    fun getLocalMovieReleaseDate_shouldReturnBritishReleaseDate() = runTest {
        //when
        val result = getLocalMovieReleaseDateUseCase.execute(324857)

        //then
        assertNotNull(result)
        assertEquals(
            FakeDataSource.FAKE_SPIDER_VERSE_RELEASE_DATE_DTO.results[0].releaseDates[0],
            result
        )
        coVerify { repository.getMovieReleaseDates(any()) }
    }

    @Test
    fun getLocalMovieReleaseDate_shouldReturnNull() = runTest {
        //given
        coEvery { repository.getMovieReleaseDates(any()) } returns Response.success(null)

        //when
        val result = getLocalMovieReleaseDateUseCase.execute(324857)

        //then
        assertNull(result)
        coVerify { repository.getMovieReleaseDates(any()) }
    }

    @Test
    fun getLocalMovieReleaseDate_shouldThrowIOException() = runTest {
        //given
        coEvery { repository.getMovieReleaseDates(any()) } throws IOException()

        assertFailsWith<IOException> {
            val result = getLocalMovieReleaseDateUseCase.execute(324857)
        }
        coVerify { repository.getMovieReleaseDates(any()) }
    }

    @Test
    fun getLocalMovieReleaseDate_shouldThrowHttpException() = runTest {
        //given
        val response: Response<MovieDetailsResponseDto> = Response.error(
            400,
            "{\"key\":[\"somestuff\"]}"
                .toResponseBody("application/json".toMediaTypeOrNull())
        )
        val exception = HttpException(response)
        coEvery { repository.getMovieReleaseDates(any()) } throws exception

        assertFailsWith<HttpException> {
            val result = getLocalMovieReleaseDateUseCase.execute(324857)
        }
        coVerify { repository.getMovieReleaseDates(any()) }
    }
}