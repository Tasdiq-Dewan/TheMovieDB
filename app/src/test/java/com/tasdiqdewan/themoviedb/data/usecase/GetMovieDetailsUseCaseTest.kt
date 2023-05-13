package com.tasdiqdewan.themoviedb.data.usecase

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.tasdiqdewan.themoviedb.data.repository.MoviesRepository
import com.tasdiqdewan.themoviedb.ui.details.DetailsScreenData
import com.tasdiqdewan.utils.dto.MovieDetailsResponseDto
import com.tasdiqdewan.utils.exceptions.MovieDetailsNullException
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
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
class GetMovieDetailsUseCaseTest {
    private lateinit var getMovieDetailsUseCase: GetMovieDetailsUseCase

    private val getLocalMovieReleaseDateUseCase = mockk<GetLocalMovieReleaseDateUseCase>() {
        coEvery { execute(any()) } returns FakeDataSource.FAKE_SPIDER_VERSE_RELEASE_DATE_DTO.results[0].releaseDates[0]
    }

    private val repository = mockk<MoviesRepository>() {
        coEvery { getMovieDetails(any()) } returns Response.success(FakeDataSource.FAKE_SPIDER_VERSE_MOVIE_DETAILS_DTO)
    }

    @get:Rule
    var instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        getMovieDetailsUseCase = GetMovieDetailsUseCaseImpl(repository, getLocalMovieReleaseDateUseCase)
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
    fun getMovieDetails_shouldReturnFullMovieDetails() = runTest {
        //when
        val result = getMovieDetailsUseCase.execute(324857)

        //then
        assertTrue(result is DetailsScreenData.Success)
        assertEquals(FakeDataSource.FAKE_SPIDER_VERSE_MOVIE_DETAILS, result.movieDetails)
        assertEquals(FakeDataSource.FAKE_SPIDER_VERSE_RELEASE_DATE, result.releaseDate)
        coVerify { getLocalMovieReleaseDateUseCase.execute(any()) }
        coVerify { repository.getMovieDetails(any()) }
    }

    @Test
    fun getMovieDetails_shouldReturnMovieDetailsWithNullReleaseDateResult() = runTest {
        //given
        coEvery { getLocalMovieReleaseDateUseCase.execute(any()) } returns null

        //when
        val result = getMovieDetailsUseCase.execute(324857)

        //then
        assertTrue(result is DetailsScreenData.Success)
        assertEquals(FakeDataSource.FAKE_SPIDER_VERSE_MOVIE_DETAILS, result.movieDetails)
        assertNull(result.releaseDate)
        coVerify { getLocalMovieReleaseDateUseCase.execute(any()) }
        coVerify { repository.getMovieDetails(any()) }
    }

    @Test
    fun getMovieDetails_shouldThrowMovieDetailsNullException() = runTest {
        //given
        coEvery { repository.getMovieDetails(any()) } returns Response.success(null)

        //then
        assertFailsWith<MovieDetailsNullException> {
            getMovieDetailsUseCase.execute(324857)
        }
        coVerify { getLocalMovieReleaseDateUseCase.execute(any()) }
        coVerify { repository.getMovieDetails(any()) }
    }

    @Test
    fun getMovieDetails_repositoryShouldThrowIoException() = runTest {
        //given
        coEvery { repository.getMovieDetails(any()) } throws IOException()

        //then
        assertFailsWith<IOException> {
            getMovieDetailsUseCase.execute(324857)
        }
        coVerify { repository.getMovieDetails(any()) }
    }

    @Test
    fun getMovieDetails_repositoryShouldThrowHttpException() = runTest {
        //given
        val response: Response<MovieDetailsResponseDto> = Response.error(
            400,
            "{\"key\":[\"somestuff\"]}"
                .toResponseBody("application/json".toMediaTypeOrNull())
        )
        val exception = HttpException(response)
        coEvery { repository.getMovieDetails(any()) } throws exception

        //then
        assertFailsWith<HttpException> {
            getMovieDetailsUseCase.execute(324857)
        }
        coVerify { repository.getMovieDetails(any()) }
    }

    @Test
    fun getMovieDetails_localReleaseDateShouldThrowIoException() = runTest {
        //given
        coEvery { getLocalMovieReleaseDateUseCase.execute(any()) } throws IOException()

        //then
        assertFailsWith<IOException> {
            getMovieDetailsUseCase.execute(324857)
        }
        coVerify { repository.getMovieDetails(any()) }
        coVerify { getLocalMovieReleaseDateUseCase.execute(any()) }
    }

    @Test
    fun getMovieDetails_localReleaseDateShouldThrowHttpException() = runTest {
        //given
        val response: Response<MovieDetailsResponseDto> = Response.error(
            400,
            "{\"key\":[\"somestuff\"]}"
                .toResponseBody("application/json".toMediaTypeOrNull())
        )
        val exception = HttpException(response)
        coEvery { getLocalMovieReleaseDateUseCase.execute(any()) } throws exception

        //then
        assertFailsWith<HttpException> {
            getMovieDetailsUseCase.execute(324857)
        }
        coVerify { repository.getMovieDetails(any()) }
        coVerify { getLocalMovieReleaseDateUseCase.execute(any()) }
    }
}