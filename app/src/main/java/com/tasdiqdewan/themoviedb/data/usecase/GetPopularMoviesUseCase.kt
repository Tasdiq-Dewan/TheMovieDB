package com.tasdiqdewan.themoviedb.data.usecase

import android.util.Log
import com.tasdiqdewan.themoviedb.data.repository.MoviesRepository
import com.tasdiqdewan.utils.domain.MovieResult
import com.tasdiqdewan.utils.dto.toDomain
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

interface GetPopularMoviesUseCase {
    suspend fun execute(page: Int = 1): List<MovieResult>
}

class GetPopularMoviesUseCaseImpl @Inject constructor(
    private val repository: MoviesRepository
) : GetPopularMoviesUseCase {
    override suspend fun execute(page: Int): List<MovieResult> {
        return try {
            repository.getPopularMoviesList(page).takeIf { it.isSuccessful }.let {
                it?.body()?.results?.map { it.toDomain() } ?: listOf()
            }
        }
        catch (e: HttpException) {
            Log.e("POPULAR_MOVIES_USECASE", e.message.toString())
            throw e
        }
        catch (e: IOException) {
            Log.e("POPULAR_MOVIES_USECASE", e.message.toString())
            throw e
        }
    }
}