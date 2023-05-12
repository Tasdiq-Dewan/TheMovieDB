package com.tasdiqdewan.themoviedb.ui.details

import com.tasdiqdewan.utils.domain.MovieDetails
import com.tasdiqdewan.utils.domain.MovieReleaseDate
import com.tasdiqdewan.utils.domain.ReleaseDate
import java.lang.Exception

data class DetailsScreenState(
    val data: DetailsScreenData
)

sealed interface DetailsScreenData {
    data class Error(
       val exception: Exception
    ) : DetailsScreenData
    object Loading: DetailsScreenData
    data class Success(
        val movieDetails: MovieDetails,
        val releaseDate: ReleaseDate? = null
    ): DetailsScreenData
}
