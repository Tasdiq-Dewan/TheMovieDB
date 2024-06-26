package com.tasdiqdewan.themoviedb.data.usecase

import android.util.Log
import com.tasdiqdewan.themoviedb.data.repository.MoviesRepository
import com.tasdiqdewan.utils.dto.ReleaseDateDto
import dagger.hilt.android.scopes.ActivityRetainedScoped
import retrofit2.HttpException
import java.io.IOException
import java.util.Locale
import javax.inject.Inject

interface GetLocalMovieReleaseDateUseCase {
    suspend fun execute(id: Int): ReleaseDateDto?
}

@ActivityRetainedScoped
class GetLocalMovieReleaseDateUseCaseImpl @Inject constructor(
    private val repository: MoviesRepository
) : GetLocalMovieReleaseDateUseCase {
    override suspend fun execute(id: Int): ReleaseDateDto? {
        val local = Locale.getDefault().country
        val releaseDate = try {
            repository.getMovieReleaseDates(id).takeIf { it.isSuccessful }.let {
                it?.body()?.results?.find { it.iso31661 == local }?.releaseDates?.find { date -> date.type == 3  }
            }
        }
        catch (e: HttpException) {
            Log.e("RELEASE_DATES_USECASE", e.message.toString())
            throw e
        }
        catch (e: IOException) {
            Log.e("RELEASE_DATES_USECASE", e.message.toString())
            throw e
        }

        return releaseDate
    }
}
