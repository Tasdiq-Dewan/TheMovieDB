package com.tasdiqdewan.themoviedb.ui.home

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tasdiqdewan.compose_library.composables.ErrorScreen
import com.tasdiqdewan.compose_library.composables.LoadingScreen
import com.tasdiqdewan.compose_library.composables.MovieResult
import com.tasdiqdewan.themoviedb.R
import com.tasdiqdewan.utils.dto.MoviesListResponseDto
import com.tasdiqdewan.themoviedb.ui.theme.TheMovieDBTheme
import com.tasdiqdewan.utils.domain.MovieResult

@Composable
fun HomeScreen(
    state: HomeScreenState,
    navigateToDetails: (Int) -> Unit,
    onDismissError: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        when(state.popularMovies) {
            is HomePopularMovies.Loading -> LoadingScreen()
            is HomePopularMovies.Success -> {
                PopularMoviesGrid(
                    movieList = (state.popularMovies as HomePopularMovies.Success).popularMoviesList,
                    navigateToDetails = navigateToDetails,
                )
            }
            is HomePopularMovies.Error -> ErrorScreen(
                (state.popularMovies as HomePopularMovies.Error).exception,
                onDismiss = onDismissError
            )
        }
    }
}

@Composable
fun PopularMoviesGrid(
    movieList: List<MovieResult>,
    navigateToDetails: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = stringResource(R.string.popular_today),
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.onSurface
        )
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
                posterPath = movie.posterPath ?: "",
                backdropPath = movie.backdropPath ?: "",
                voteAverage = movie.voteAverage,
                modifier = Modifier
                    .clickable(
                        enabled = true,
                        onClickLabel = stringResource(id = R.string.movie_click_label),
                    ) {
                        navigateToDetails(movie.id)
                    }
            )
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true, device = "id:pixel_5")
@Composable
fun HomeScreenPreview() {
    TheMovieDBTheme {
        HomeScreen(state = HomeScreenState(), {}, {})
    }
}

@Preview(showSystemUi = true, showBackground = true, device = "id:pixel_5", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenPreviewDark() {
    TheMovieDBTheme {
        HomeScreen(state = HomeScreenState(), {}, {})
    }
}