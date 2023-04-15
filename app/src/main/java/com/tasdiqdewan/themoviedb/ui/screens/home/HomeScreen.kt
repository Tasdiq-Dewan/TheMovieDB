package com.tasdiqdewan.themoviedb.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.tasdiqdewan.themoviedb.ui.theme.TheMovieDBTheme

@Composable
fun HomeScreen(
    state: HomeScreenState
) {
    
}

@Preview
@Composable
fun HomeScreenPreview() {
    TheMovieDBTheme {
        HomeScreen(state = HomeScreenState())
    }
}