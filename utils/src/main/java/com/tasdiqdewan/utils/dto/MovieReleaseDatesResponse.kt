package com.tasdiqdewan.utils.dto

import com.squareup.moshi.Json

data class MovieReleaseDatesResponse(
    val id: Int,
    val results: List<Result>
) {
    data class Result(
        @Json(name = "iso_3166_1") val iso31661: String,
        @Json(name = "release_dates") val releaseDates: List<ReleaseDate>
    )

    data class ReleaseDate(
        val certification: String,
        @Json(name = "iso_639_1") val iso6391: String,
        @Json(name = "release_date") val releaseDate: String,
        val type: Int,
        val note: String
    )
}
