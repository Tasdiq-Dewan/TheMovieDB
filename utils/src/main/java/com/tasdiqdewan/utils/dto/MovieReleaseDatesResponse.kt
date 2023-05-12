package com.tasdiqdewan.utils.dto

import com.squareup.moshi.Json
import com.tasdiqdewan.utils.domain.MovieReleaseDate
import com.tasdiqdewan.utils.domain.ReleaseDate
import com.tasdiqdewan.utils.domain.ReleaseDateResult

data class MovieReleaseDatesResponseDto(
    val id: Int,
    val results: List<ReleaseDateResultDto>
)

fun MovieReleaseDatesResponseDto.toDomain() = MovieReleaseDate(
    result = this.results.map { it.toDomain() }
)

data class ReleaseDateResultDto(
    @Json(name = "iso_3166_1") val iso31661: String,
    @Json(name = "release_dates") val releaseDates: List<ReleaseDateDto>
)

fun ReleaseDateResultDto.toDomain() = ReleaseDateResult(
    iso31661 = this.iso31661,
    releaseDates = this.releaseDates.map { it.toDomain() }
)

data class ReleaseDateDto(
    val certification: String,
    @Json(name = "iso_639_1") val iso6391: String,
    @Json(name = "release_date") val releaseDate: String,
    val type: Int,
    val note: String
)

fun ReleaseDateDto.toDomain() = ReleaseDate(
    certification = this.certification,
    iso6391 = this.iso6391,
    releaseDate = this.releaseDate,
    note = this.note
)
