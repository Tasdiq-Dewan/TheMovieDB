package com.tasdiqdewan.themoviedb.ui.screens.home

import com.tasdiqdewan.themoviedb.models.MoviesListResponse

data class HomeScreenState(
    var page: Int = 1,
    var popularMovies: HomePopularMovies = HomePopularMovies.Loading,
    var selectedId: Int = 0
)

sealed interface HomePopularMovies {
    object Error: HomePopularMovies
    object Loading: HomePopularMovies
    data class Success(val popularMoviesList: List<MoviesListResponse.Result>): HomePopularMovies
}
