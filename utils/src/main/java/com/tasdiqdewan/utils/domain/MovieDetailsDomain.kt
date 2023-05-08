package com.tasdiqdewan.utils.domain

data class MovieDetails(
    val adult: Boolean,
    val backdropPath: String?,
    val belongsToCollection: MovieCollection?,
    val budget: Int,
    val genres: List<Genre>,
    val homepage: String?,
    val id: Int,
    val imdbId: String?,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String?,
    val popularity: Double,
    val posterPath: String?,
    val productionCompanies: List<ProductionCompany>,
    val productionCountries: List<Country>,
    val releaseDate: String,
    val revenue: Long,
    val runtime: Int?,
    val spokenLanguages: List<Language>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
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
