package com.tasdiqdewan.themoviedb.ui.home

import com.tasdiqdewan.utils.domain.MovieResult
import java.lang.Exception

data class HomeScreenState(
    var page: Int = 1,
    var popularMovies: HomePopularMovies = HomePopularMovies.Loading,
)

sealed interface HomePopularMovies {
    data class Error(val exception: Exception): HomePopularMovies
    object Loading: HomePopularMovies
    data class Success(val popularMoviesList: List<MovieResult>): HomePopularMovies
}
