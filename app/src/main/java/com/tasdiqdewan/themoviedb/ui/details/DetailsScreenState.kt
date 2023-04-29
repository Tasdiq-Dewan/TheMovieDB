package com.tasdiqdewan.themoviedb.ui.details

import com.tasdiqdewan.themoviedb.models.MovieDetailsResponse

data class DetailsScreenState(
    val data: DetailsScreenData
)

sealed interface DetailsScreenData {
    object Error : DetailsScreenData
    object Loading: DetailsScreenData
    data class Success(val movieDetails: MovieDetailsResponse): DetailsScreenData
}
