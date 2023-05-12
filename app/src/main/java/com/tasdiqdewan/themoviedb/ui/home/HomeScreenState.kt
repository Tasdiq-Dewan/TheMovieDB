package com.tasdiqdewan.themoviedb.ui.home

import com.tasdiqdewan.utils.dto.MoviesListResponse

data class HomeScreenState(
    var page: Int = 1,
    var popularMovies: HomePopularMovies = HomePopularMovies.Loading,
)

sealed interface HomePopularMovies {
    object Error: HomePopularMovies
    object Loading: HomePopularMovies
    data class Success(val popularMoviesList: List<MoviesListResponse.Result>): HomePopularMovies
}
