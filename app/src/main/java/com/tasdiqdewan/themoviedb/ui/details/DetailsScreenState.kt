package com.tasdiqdewan.themoviedb.ui.details

import com.tasdiqdewan.themoviedb.models.MovieDetailsResponse
import com.tasdiqdewan.themoviedb.models.MovieReleaseDatesResponse

data class DetailsScreenState(
    val data: DetailsScreenData
)

sealed interface DetailsScreenData {
    object Error : DetailsScreenData
    object Loading: DetailsScreenData
    data class Success(
        val movieDetails: MovieDetailsResponse,
        val releaseDate: MovieReleaseDatesResponse.ReleaseDate
    ): DetailsScreenData
}
