package com.tasdiqdewan.themoviedb.ui.details

import com.tasdiqdewan.utils.fake.FakeDataSource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import retrofit2.Response
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.tasdiqdewan.themoviedb.data.usecase.GetMovieDetailsUseCaseTest
import com.tasdiqdewan.themoviedb.data.usecase.GetMovieDetailsUseCaseImpl
import com.tasdiqdewan.utils.dto.MovieDetailsResponseDto
import com.tasdiqdewan.utils.exceptions.MovieDetailsNullException
import io.mockk.coVerify
import io.mockk.unmockkAll
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import retrofit2.HttpException
import java.io.IOException

@ExperimentalCoroutinesApi
class DetailsViewModelTest {
    private lateinit var viewModel: DetailsViewModel

    private val fullSpiderVerseMovieDetails = DetailsScreenData.Success(
        movieDetails = FakeDataSource.FAKE_SPIDER_VERSE_MOVIE_DETAILS,
        releaseDate = FakeDataSource.FAKE_SPIDER_VERSE_RELEASE_DATE
    )

    private val getMovieDetailsUseCase = mockk<GetMovieDetailsUseCaseImpl> {
        coEvery { execute(any()) } returns fullSpiderVerseMovieDetails
    }

    @get:Rule
    var instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel = DetailsViewModel(getMovieDetailsUseCase)
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
        unmockkAll()
    }

    @Test
    fun getMovieDetails_shouldReturnFullMovieDetails() = runTest {
        // when
        viewModel.getMovieDetails(324857)
        val result = viewModel.state.value.data

        //then
        assertTrue(result is DetailsScreenData.Success)
        assertEquals(fullSpiderVerseMovieDetails, result)
        coVerify { getMovieDetailsUseCase.execute(any()) }
    }

    @Test
    fun getMovieDetails_shouldReturnDetailsWithoutReleaseDateInfo() = runTest {
        // given
        val detailsWithoutReleaseDate = fullSpiderVerseMovieDetails.copy(
            releaseDate = null
        )
        coEvery { getMovieDetailsUseCase.execute(any()) } returns detailsWithoutReleaseDate

        // when
        viewModel.getMovieDetails(324857)
        val result = viewModel.state.value.data

        //then
        assertTrue(result is DetailsScreenData.Success)
        assertEquals(detailsWithoutReleaseDate, result)
        coVerify { getMovieDetailsUseCase.execute(any()) }
    }

    @Test
    fun getMovieDetails_shouldThrowIOException() = runTest {
        //given
        coEvery { getMovieDetailsUseCase.execute(any()) } throws IOException()

        //when
        viewModel.getMovieDetails(324857)
        val result = viewModel.state.value.data

        //then
        assertTrue(result is DetailsScreenData.Error)
        assertTrue((result as DetailsScreenData.Error).exception is IOException)
        coVerify { getMovieDetailsUseCase.execute(any()) }
    }

    @Test
    fun getMovieDetails_shouldThrowHTTPException() = runTest {
        //given
        val response: Response<MovieDetailsResponseDto> = Response.error(
            400,
            "{\"key\":[\"somestuff\"]}"
                .toResponseBody("application/json".toMediaTypeOrNull())
        )
        val exception = HttpException(response)
        coEvery { getMovieDetailsUseCase.execute(any()) } throws exception

        //when
        viewModel.getMovieDetails(324857)
        val result = viewModel.state.value.data

        //then
        assertTrue(result is DetailsScreenData.Error)
        assertTrue((result as DetailsScreenData.Error).exception is HttpException)
        coVerify { getMovieDetailsUseCase.execute(any()) }
    }

    @Test
    fun getMovieDetails_shouldThrowMovieDetailsNullException() = runTest {
        //given
        coEvery { getMovieDetailsUseCase.execute(any()) } throws MovieDetailsNullException()

        //when
        viewModel.getMovieDetails(324857)
        val result = viewModel.state.value.data

        //then
        assertTrue(result is DetailsScreenData.Error)
        assertTrue((result as DetailsScreenData.Error).exception is MovieDetailsNullException)
        coVerify { getMovieDetailsUseCase.execute(any()) }
    }
}