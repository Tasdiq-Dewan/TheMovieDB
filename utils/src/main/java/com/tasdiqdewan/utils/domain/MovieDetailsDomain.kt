package com.tasdiqdewan.utils.domain

data class MovieDetails(
    val backdropPath: String?,
    val belongsToCollection: MovieCollection?,
    val budget: Int,
    val genres: List<Genre>,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String?,
    val popularity: Double,
    val posterPath: String?,
    val releaseDate: String,
    val revenue: Long,
    val runtime: Int?,
    val status: String,
    val tagline: String,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int
)

data class MovieCollection(
    val id: Int,
    val name: String,
    val backdropPath: String?,
    val posterPath: String?,
)

data class Genre(
    val id: Int,
    val name: String
)

data class ProductionCompany(
    val id: Int,
    val logoPath: String?,
    val name: String,
    val originCountry: String
)

data class Country(
    val iso31661: String?,
    val name: String?
)

data class Language(
    val englishName: String?,
    val iso6391: String?,
    val name: String?
)
