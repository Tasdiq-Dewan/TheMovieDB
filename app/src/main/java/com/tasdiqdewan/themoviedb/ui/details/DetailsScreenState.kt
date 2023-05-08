package com.tasdiqdewan.themoviedb.ui.details

import com.tasdiqdewan.utils.domain.MovieDetails
import com.tasdiqdewan.utils.domain.MovieReleaseDate
import com.tasdiqdewan.utils.domain.ReleaseDate

data class DetailsScreenState(
    val data: DetailsScreenData
)

sealed interface DetailsScreenData {
    object Error : DetailsScreenData
    object Loading: DetailsScreenData
    data class Success(
        val movieDetails: MovieDetails,
        val releaseDate: ReleaseDate
    ): DetailsScreenData
}
