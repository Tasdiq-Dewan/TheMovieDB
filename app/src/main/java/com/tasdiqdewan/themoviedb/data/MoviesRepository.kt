package com.tasdiqdewan.themoviedb.data

import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class MoviesRepository @Inject constructor(
    private val apiServices: ApiServices
) {
    suspend fun getPopularMoviesList(page: Int = 1) = apiServices.getPopularMoviesList(page)

    suspend fun getMovieDetails(id: Int) = apiServices.getMovieDetails(id)
}