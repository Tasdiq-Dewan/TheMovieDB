package com.tasdiqdewan.utils.domain

data class MoviesList(
    val page: Int,
    val results: List<MovieResult>,
)

data class MovieResult(
    val adult: Boolean,
    val backdropPath: String?,
    val genreIds: List<Int>,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String?,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int
)