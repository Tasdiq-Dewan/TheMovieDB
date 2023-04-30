package com.tasdiqdewan.utils

object Constants {
    const val API_KEY = "50c2d1450717884bac15ef6b47db308c"
    const val BEARER_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1MGMyZDE0NTA3MTc4ODRiYWMxNWVmNmI0N2RiMzA4YyIsInN1YiI6IjYzZmQzZGFlN2E0ZWU3MDBmMGY4Y2VmZSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.hyJ7c2G4yRo44UhvrdL3k7eFq10ahxgYIhnTKmq7lLs"
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/"
    const val SIMPLE_DATE_FORMAT = "yyyy-MM-dd"
    const val SIMPLE_DATE_FORMAT_UK = "dd/MM/yyyy"
    const val SIMPLE_DATE_FORMAT_US = "yyyy-dd-MM"
    const val DAY_SHORT_MONTH_YEAR = "dd MMM yyyy"
    const val DAY_FULL_MONTH_YEAR = "dd MMMM yyyy"
    const val YEAR = "yyyy"
}

enum class BackdropSize(val size: String) {
    W300("w300"),
    W780("w780"),
    W1280("w1280"),
    ORIGINAL("original")
}

enum class LogoSize(val size: String) {
    W45("w45"),
    W92("w92"),
    W154("w154"),
    W185("w185"),
    W300("w300"),
    W500("w500"),
    ORIGINAL("original")
}

enum class PosterSize(val size: String) {
    W92("w92"),
    W154("w154"),
    W185("w185"),
    W342("w342"),
    W500("w500"),
    W780("w780"),
    ORIGINAL("original")
}

enum class ProfileSize(val size: String) {
    W45("w45"),
    W185("w185"),
    H632("h632"),
    ORIGINAL("original")
}

enum class StillSize(val size: String) {
    W92("w92"),
    W185("w185"),
    W300("w300"),
    ORIGINAL("original")
}