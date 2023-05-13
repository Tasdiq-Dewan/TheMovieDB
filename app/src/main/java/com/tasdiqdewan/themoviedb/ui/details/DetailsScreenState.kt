package com.tasdiqdewan.themoviedb.ui.details

import com.tasdiqdewan.utils.domain.MovieDetails
import com.tasdiqdewan.utils.domain.ReleaseDate
import java.lang.Exception

data class DetailsScreenState(
    val data: DetailsScreenData = DetailsScreenData.Nothing,
    val isLoading: Boolean = false,
)

sealed interface DetailsScreenData {
    object Nothing: DetailsScreenData
    data class Error(
       val exception: Exception
    ) : DetailsScreenData
    data class Success(
        val movieDetails: MovieDetails,
        val releaseDate: ReleaseDate? = null
    ): DetailsScreenData
}
