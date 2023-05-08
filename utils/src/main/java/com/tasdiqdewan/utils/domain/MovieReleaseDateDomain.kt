package com.tasdiqdewan.utils.domain

import com.squareup.moshi.Json

data class MovieReleaseDate(
    val result: List<ReleaseDateResult>
)

data class ReleaseDateResult(
    val iso31661: String,
    val releaseDates: List<ReleaseDate>
)

data class ReleaseDate(
    val certification: String,
    val iso6391: String,
    val releaseDate: String,
    val note: String
)