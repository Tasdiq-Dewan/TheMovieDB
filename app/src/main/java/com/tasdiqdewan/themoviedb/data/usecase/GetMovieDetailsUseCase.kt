package com.tasdiqdewan.themoviedb.data.usecase

import com.tasdiqdewan.themoviedb.data.repository.MoviesRepository
import com.tasdiqdewan.themoviedb.ui.details.DetailsScreenData
import com.tasdiqdewan.utils.dto.toDomain
import dagger.hilt.android.scopes.ActivityRetainedScoped
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
        val movieDetails = repository.getMovieDetails(id).body()?.toDomain()
        val releaseDate = getLocalMovieReleaseDateUseCase.execute(id)

        return if(movieDetails != null && releaseDate != null) {
            DetailsScreenData.Success(
                movieDetails = movieDetails,
                releaseDate = releaseDate.toDomain()
            )
        } else {
            DetailsScreenData.Error
        }
    }
}