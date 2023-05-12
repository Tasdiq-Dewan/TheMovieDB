package com.tasdiqdewan.themoviedb.ui.details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tasdiqdewan.compose_library.composables.ErrorScreen
import com.tasdiqdewan.compose_library.composables.LoadingScreen
import com.tasdiqdewan.compose_library.composables.movie_details.MovieDetails

@Composable
fun DetailsScreen(
    state: DetailsScreenData,
    modifier: Modifier = Modifier
) {
    when(state) {
        is DetailsScreenData.Loading -> LoadingScreen()
        is DetailsScreenData.Error -> ErrorScreen()
        is DetailsScreenData.Success -> MovieDetails(
            title = state.movieDetails.title,
            releaseDate = state.movieDetails.releaseDate,
            posterPath = state.movieDetails.posterPath,
            voteAverage = state.movieDetails.voteAverage,
            certification = state.releaseDate.certification,
            tagline = state.movieDetails.tagline,
            overview = state.movieDetails.overview,
            runtime = state.movieDetails.runtime,
            genres = state.movieDetails.genres,
            modifier = modifier
                .fillMaxSize()
        )
    }
}