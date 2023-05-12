package com.tasdiqdewan.utils.dto

import com.squareup.moshi.Json
import com.tasdiqdewan.utils.domain.Country
import com.tasdiqdewan.utils.domain.Genre
import com.tasdiqdewan.utils.domain.Language
import com.tasdiqdewan.utils.domain.MovieCollection
import com.tasdiqdewan.utils.domain.MovieDetails
import com.tasdiqdewan.utils.domain.ProductionCompany

data class MovieDetailsResponseDto(
    val adult: Boolean,
    @Json(name = "backdrop_path") val backdropPath: String?,
    @Json(name = "belongs_to_collection") val belongsToCollection: MovieCollectionDto?,
    val budget: Int,
    val genres: List<GenreDto>,
    val homepage: String?,
    val id: Int,
    @Json(name = "imdb_id") val imdbId: String?,
    @Json(name = "original_language") val originalLanguage: String,
    @Json(name = "original_title") val originalTitle: String,
    val overview: String?,
    val popularity: Double,
    @Json(name = "poster_path") val posterPath: String?,
    @Json(name = "production_companies") val productionCompanies: List<ProductionCompanyDto>,
    @Json(name = "production_countries") val productionCountries: List<CountryDto>,
    @Json(name = "release_date") val releaseDate: String,
    val revenue: Long,
    val runtime: Int?,
    @Json(name = "spoken_languages") val spokenLanguages: List<LanguageDto>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    @Json(name = "vote_average") val voteAverage: Double,
    @Json(name = "vote_count") val voteCount: Int
)

fun MovieDetailsResponseDto.toDomain() = MovieDetails(
    backdropPath = this.backdropPath,
    belongsToCollection =  this.belongsToCollection?.toDomain(),
    budget = this.budget,
    genres = this.genres.map { it.toDomain() },
    originalLanguage = this.originalLanguage,
    originalTitle = this.originalTitle,
    overview = this.overview,
    popularity = this.popularity,
    posterPath = this.posterPath,
    releaseDate = this.releaseDate,
    revenue = this.revenue,
    runtime = this.runtime,
    status = this.status,
    tagline = this.tagline,
    title = this.title,
    voteAverage = this.voteAverage,
    voteCount = this.voteCount
)

data class MovieCollectionDto(
    val id: Int,
    val name: String,
    @Json(name = "backdrop_path") val backdropPath: String?,
    @Json(name = "poster_path") val posterPath: String?,
)

fun MovieCollectionDto.toDomain() = MovieCollection(
    id = this.id,
    name = this.name,
    backdropPath = this.backdropPath,
    posterPath = this.posterPath
)

data class GenreDto(
    val id: Int,
    val name: String
)

fun GenreDto.toDomain() = Genre(
    id = this.id,
    name = this.name
)

data class ProductionCompanyDto(
    val id: Int,
    @Json(name = "logo_path") val logoPath: String?,
    val name: String,
    @Json(name = "origin_country") val originCountry: String
)

fun ProductionCompanyDto.toDomain() = ProductionCompany(
    id = this.id,
    logoPath = this.logoPath,
    name = this.name,
    originCountry = this.originCountry
)

data class CountryDto(
    @Json(name = "iso_3166_1") val iso31661: String?,
    val name: String?
)

fun CountryDto.toDomain() = Country(
    iso31661 = this.iso31661,
    name = this.name
)

data class LanguageDto(
    @Json(name = "english_name") val englishName: String?,
    @Json(name =  "iso_639_1") val iso6391: String?,
    val name: String?
)

fun LanguageDto.toDomain() = Language(
    englishName = this.englishName,
    iso6391 = this.iso6391,
    name = this.name
)
