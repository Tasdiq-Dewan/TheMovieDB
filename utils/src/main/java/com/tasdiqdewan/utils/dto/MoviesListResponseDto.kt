package com.tasdiqdewan.utils.dto

import com.squareup.moshi.Json
import com.tasdiqdewan.utils.domain.MovieResult
import com.tasdiqdewan.utils.domain.MoviesList

data class MoviesListResponseDto(
    val page: Int,
    val results: List<ResultDto>,
    @Json(name = "total_pages") val totalPages: Int,
    @Json(name = "total_results") val totalResults: Int
)

data class ResultDto(
    val adult: Boolean,
    @Json(name = "backdrop_path") val backdropPath: String?,
    @Json(name = "genre_ids") val genreIds: List<Int>,
    val id: Int,
    @Json(name = "original_language") val originalLanguage: String,
    @Json(name = "original_title") val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @Json(name = "poster_path") val posterPath: String?,
    @Json(name = "release_date") val releaseDate: String,
    val title: String,
    val video: Boolean,
    @Json(name = "vote_average") val voteAverage: Double,
    @Json(name = "vote_count") val voteCount: Int
)

fun MoviesListResponseDto.toDomain() = MoviesList(
    page = this.page,
    results = this.results.map { it.toDomain() }
)

fun ResultDto.toDomain() = MovieResult(
    adult = this.adult,
    backdropPath = this.backdropPath,
    genreIds = this.genreIds,
    id = this.id,
    originalLanguage = this.originalLanguage,
    originalTitle = this.originalTitle,
    overview = this.overview,
    popularity = this.popularity,
    posterPath = this.posterPath,
    releaseDate = this.releaseDate,
    title = this.title,
    voteAverage = this.voteAverage,
    voteCount = this.voteCount
)
