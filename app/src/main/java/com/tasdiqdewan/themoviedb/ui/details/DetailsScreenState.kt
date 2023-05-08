package com.tasdiqdewan.themoviedb.ui.details

import com.tasdiqdewan.utils.dto.MovieDetailsResponseDto
import com.tasdiqdewan.utils.dto.MovieReleaseDatesResponse

data class DetailsScreenState(
    val data: DetailsScreenData
)

sealed interface DetailsScreenData {
    object Error : DetailsScreenData
    object Loading: DetailsScreenData
    data class Success(
        val movieDetails: MovieDetailsResponseDto,
        val releaseDate: MovieReleaseDatesResponse.ReleaseDate
    ): DetailsScreenData
}
