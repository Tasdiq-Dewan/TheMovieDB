package com.tasdiqdewan.themoviedb.data

import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class MoviesRepository @Inject constructor(
    private val apiServices: ApiServices
) {
    fun getPopularMoviesList(page: Int) = apiServices.getPopularMoviesList(page)

    fun getMovieDetails(id: Int) = apiServices.getMovieDetails(id)
}