package com.tasdiqdewan.themoviedb.data.usecase

import com.tasdiqdewan.themoviedb.data.repository.MoviesRepository
import com.tasdiqdewan.themoviedb.ui.details.DetailsScreenData
import com.tasdiqdewan.themoviedb.ui.details.DetailsScreenState
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

interface GetMovieDetailsUsecase {
    suspend fun execute(id: Int): DetailsScreenData
}

@ActivityRetainedScoped
class GetMovieDetailsUsecaseImpl @Inject constructor(
    private val repository: MoviesRepository,
    private val getLocalMovieReleaseDateUsecase: GetLocalMovieReleaseDateUsecase
) : GetMovieDetailsUsecase {
    override suspend fun execute(id: Int): DetailsScreenData {
        val movieDetails = repository.getMovieDetails(id).body()
        val releaseDate = getLocalMovieReleaseDateUsecase.execute(id)

        if(movieDetails != null && releaseDate != null) {
            return DetailsScreenData.Success(
                movieDetails = movieDetails,
                releaseDate = releaseDate
            )
        }
        else {
            return DetailsScreenData.Error
        }
    }
}

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class MovieDetailsModule {
    @Binds
    abstract fun bindGetMovieDetailsUsecase(
        getMovieDetailsUsecaseImpl: GetMovieDetailsUsecaseImpl
    ): GetMovieDetailsUsecase
}