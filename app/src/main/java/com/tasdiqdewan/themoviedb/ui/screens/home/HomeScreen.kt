package com.tasdiqdewan.themoviedb.ui.screens.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
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
            is HomePopularMovies.Success -> Text((state.popularMovies as HomePopularMovies.Success).popularMoviesList[0].title)
            is HomePopularMovies.Error -> Text("Error")
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