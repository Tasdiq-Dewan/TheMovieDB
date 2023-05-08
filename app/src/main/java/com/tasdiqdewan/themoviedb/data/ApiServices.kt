package com.tasdiqdewan.themoviedb.data

import com.tasdiqdewan.utils.dto.MovieDetailsResponseDto
import com.tasdiqdewan.utils.dto.MovieReleaseDatesResponse
import com.tasdiqdewan.utils.dto.MoviesListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {
    @GET("movie/popular")
    suspend fun getPopularMoviesList(@Query("page") page: Int): Response<MoviesListResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") id: Int): Response<MovieDetailsResponseDto>

    @GET("movie/{movie_id}/release_dates")
    suspend fun getMovieReleaseDates(@Path("movie_id") id: Int): Response<MovieReleaseDatesResponse>
}