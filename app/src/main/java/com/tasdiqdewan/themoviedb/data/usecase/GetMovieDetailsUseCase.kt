package com.tasdiqdewan.themoviedb.data.usecase

import android.util.Log
import com.tasdiqdewan.themoviedb.data.repository.MoviesRepository
import com.tasdiqdewan.themoviedb.ui.details.DetailsScreenData
import com.tasdiqdewan.utils.dto.toDomain
import com.tasdiqdewan.utils.exceptions.MovieDetailsNullException
import dagger.hilt.android.scopes.ActivityRetainedScoped
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

interface GetMovieDetailsUseCase {
    suspend fun execute(id: Int): DetailsScreenData
}

@ActivityRetainedScoped
class GetMovieDetailsUseCaseImpl @Inject constructor(
    private val repository: MoviesRepository,
    private val getLocalMovieReleaseDateUseCase: GetLocalMovieReleaseDateUseCase
) : GetMovieDetailsUseCase {
    override suspend fun execute(id: Int): DetailsScreenData {
        val movieDetails = try {
            repository.getMovieDetails(id).body()?.toDomain()
        }
        catch (e: HttpException) {
            Log.e("MOVIE_DETAILS_USECASE", e.message.toString())
            throw e
        }
        catch (e: IOException) {
            Log.e("MOVIE_DETAILS_USECASE", e.message.toString())
            throw e
        }

        val releaseDate = try {
            getLocalMovieReleaseDateUseCase.execute(id)
        }
        catch (e: HttpException) {
            throw e
        }
        catch (e: IOException) {
            throw e
        }

        return DetailsScreenData.Success(
            movieDetails = movieDetails ?: throw MovieDetailsNullException(),
            releaseDate = releaseDate?.toDomain()
        )
    }
}