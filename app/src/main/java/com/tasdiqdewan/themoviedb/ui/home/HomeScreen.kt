package com.tasdiqdewan.themoviedb.ui.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tasdiqdewan.compose_library.composables.MovieResult
import com.tasdiqdewan.themoviedb.models.MoviesListResponse
import com.tasdiqdewan.themoviedb.ui.theme.TheMovieDBTheme

@Composable
fun HomeScreen(
    state: HomeScreenState
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        when(state.popularMovies) {
            is HomePopularMovies.Loading -> Text("Loading")
            is HomePopularMovies.Success -> {
                PopularMoviesGrid(movieList = (state.popularMovies as HomePopularMovies.Success).popularMoviesList)
            }
            is HomePopularMovies.Error -> Text("Error")
        }
    }
}

@Composable
fun PopularMoviesGrid(
    movieList: List<MoviesListResponse.Result>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        contentPadding = PaddingValues(8.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        items(items = movieList, key = { movie -> movie.id }) {
            movie -> MovieResult(
            id = movie.id,
            title = movie.title,
            releaseDate = movie.releaseDate,
            posterPath = movie.posterPath,
            backdropPath = movie.backdropPath,
            voteAverage = movie.voteAverage
        )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true, device = "id:pixel_5")
@Composable
fun HomeScreenPreview() {
    TheMovieDBTheme {
        HomeScreen(state = HomeScreenState())
    }
}

@Preview(showSystemUi = true, showBackground = true, device = "id:pixel_5", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenPreviewDark() {
    TheMovieDBTheme {
        HomeScreen(state = HomeScreenState())
    }
}