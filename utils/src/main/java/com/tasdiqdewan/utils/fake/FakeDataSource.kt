package com.tasdiqdewan.utils.fake

import com.tasdiqdewan.utils.domain.Genre
import com.tasdiqdewan.utils.domain.MovieCollection
import com.tasdiqdewan.utils.domain.MovieDetails
import com.tasdiqdewan.utils.domain.MovieResult
import com.tasdiqdewan.utils.domain.MoviesList
import com.tasdiqdewan.utils.domain.ReleaseDate
import com.tasdiqdewan.utils.dto.CountryDto
import com.tasdiqdewan.utils.dto.GenreDto
import com.tasdiqdewan.utils.dto.LanguageDto
import com.tasdiqdewan.utils.dto.MovieCollectionDto
import com.tasdiqdewan.utils.dto.MovieDetailsResponseDto
import com.tasdiqdewan.utils.dto.MovieReleaseDatesResponseDto
import com.tasdiqdewan.utils.dto.MoviesListResponseDto
import com.tasdiqdewan.utils.dto.ProductionCompanyDto
import com.tasdiqdewan.utils.dto.ReleaseDateDto
import com.tasdiqdewan.utils.dto.ReleaseDateResultDto
import com.tasdiqdewan.utils.dto.ResultDto

object FakeDataSource {
    val FAKE_SPIDER_VERSE_MOVIE_DETAILS_DTO = MovieDetailsResponseDto(
        adult = false,
        backdropPath = "/qGQf2OHIkoh89K8XeKQzhxczf96.jpg",
        belongsToCollection = MovieCollectionDto(
            id = 573436,
            name = "Spider-Man: Spider-Verse Collection",
            posterPath = "/eD4bGQNfmqExIAzKdvX5gDHhI2.jpg",
            backdropPath = "/14F6gMaRjzgsN6EEpiwH87R1I00.jpg"
        ),
        budget = 90000000,
        genres = listOf(
            GenreDto(id = 28, name = "Action"),
            GenreDto(id = 12, name = "Adventure"),
            GenreDto(id = 16, "Animation"),
            GenreDto(id = 878, "Science Fiction")
        ),
        homepage = "https://www.sonypictures.com/movies/spidermanintothespiderverse",
        id = 324857,
        imdbId = "tt4633694",
        originalLanguage = "en",
        originalTitle = "Spider-Man: Into the Spider-Verse",
        overview = "Miles Morales is juggling his life between being a high school student and being a spider-man. When Wilson \"Kingpin\" Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension.",
        popularity =  87.968,
        posterPath = "/iiZZdoQBEYBv6id8su7ImL0oCbD.jpg",
        productionCompanies = listOf(
            ProductionCompanyDto(
                id = 5,
                logoPath = "/71BqEFAF4V3qjjMPCpLuyJFB9A.png",
                name = "Columbia Pictures",
                originCountry = "US"
            )
        ),
        productionCountries = listOf(
            CountryDto(
                iso31661 = "US",
                name = "United States of America"
            )
        ),
        releaseDate = "2018-12-14",
        revenue = 375464627,
        runtime = 117,
        spokenLanguages = listOf(
            LanguageDto(
                englishName = "English",
                iso6391 = "en",
                name = "English"
            )
        ),
        status = "Released",
        tagline = "More than one wears the mask.",
        title = "Spider-Man: Into the Spider-Verse",
        video = false,
        voteAverage = 8.406,
        voteCount = 12695
    )

    val FAKE_SPIDER_VERSE_MOVIE_DETAILS = MovieDetails(
        backdropPath = "/qGQf2OHIkoh89K8XeKQzhxczf96.jpg",
        belongsToCollection = MovieCollection(
            id = 573436,
            name = "Spider-Man: Spider-Verse Collection",
            posterPath = "/eD4bGQNfmqExIAzKdvX5gDHhI2.jpg",
            backdropPath = "/14F6gMaRjzgsN6EEpiwH87R1I00.jpg"
        ),
        budget = 90000000,
        genres = listOf(
            Genre(id = 28, name = "Action"),
            Genre(id = 12, name = "Adventure"),
            Genre(id = 16, "Animation"),
            Genre(id = 878, "Science Fiction")
        ),
        originalLanguage = "en",
        originalTitle = "Spider-Man: Into the Spider-Verse",
        overview = "Miles Morales is juggling his life between being a high school student and being a spider-man. When Wilson \"Kingpin\" Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension.",
        popularity =  87.968,
        posterPath = "/iiZZdoQBEYBv6id8su7ImL0oCbD.jpg",
        releaseDate = "2018-12-14",
        revenue = 375464627,
        runtime = 117,
        status = "Released",
        tagline = "More than one wears the mask.",
        title = "Spider-Man: Into the Spider-Verse",
        voteAverage = 8.406,
        voteCount = 12695
    )

    val FAKE_SPIDER_VERSE_RELEASE_DATE_DTO = MovieReleaseDatesResponseDto(
        id = 324857,
        results = listOf(
           ReleaseDateResultDto(
               iso31661 = "GB",
               releaseDates = listOf(
                   ReleaseDateDto(
                       certification = "PG",
                       iso6391 = "",
                       releaseDate = "2018-12-14T00:00:00.000Z",
                       type = 3,
                       note = ""
                   )
               )
           )
        )
    )

    val FAKE_SPIDER_VERSE_RELEASE_DATE = ReleaseDate(
        certification = "PG",
        iso6391 = "",
        releaseDate = "2018-12-14T00:00:00.000Z",
        note = ""
    )

    val FAKE_POPULAR_MOVIES_RESPONSE_DTO = MoviesListResponseDto(
        page = 1,
        results = listOf(
            ResultDto(
                adult = false,
                backdropPath = "/3CxUndGhUcZdt1Zggjdb2HkLLQX.jpg",
                genreIds = listOf(28, 12, 878),
                id = 640146,
                originalLanguage = "en",
                originalTitle = "Ant-Man and the Wasp: Quantumania",
                overview = "Super-Hero partners Scott Lang and Hope van Dyne, along with with Hope's parents Janet van Dyne and Hank Pym, and Scott's daughter Cassie Lang, find themselves exploring the Quantum Realm, interacting with strange new creatures and embarking on an adventure that will push them beyond the limits of what they thought possible.",
                popularity = 3501.526,
                posterPath = "/qnqGbB22YJ7dSs4o6M7exTpNxPz.jpg",
                releaseDate = "2023-02-15",
                title = "Ant-Man and the Wasp: Quantumania",
                video = false,
                voteAverage = 6.5,
                voteCount = 2434
            )
        ),
        totalPages = 38259,
        totalResults = 765167
    )

    val FAKE_POPULAR_MOVIES_RESPONSE = MoviesList(
        page = 1,
        results = listOf(
            MovieResult(
                adult = false,
                backdropPath = "/3CxUndGhUcZdt1Zggjdb2HkLLQX.jpg",
                genreIds = listOf(28, 12, 878),
                id = 640146,
                originalLanguage = "en",
                originalTitle = "Ant-Man and the Wasp: Quantumania",
                overview = "Super-Hero partners Scott Lang and Hope van Dyne, along with with Hope's parents Janet van Dyne and Hank Pym, and Scott's daughter Cassie Lang, find themselves exploring the Quantum Realm, interacting with strange new creatures and embarking on an adventure that will push them beyond the limits of what they thought possible.",
                popularity = 3501.526,
                posterPath = "/qnqGbB22YJ7dSs4o6M7exTpNxPz.jpg",
                releaseDate = "2023-02-15",
                title = "Ant-Man and the Wasp: Quantumania",
                voteAverage = 6.5,
                voteCount = 2434
            )
        ),
    )
}