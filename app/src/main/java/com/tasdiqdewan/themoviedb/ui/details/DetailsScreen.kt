package com.tasdiqdewan.themoviedb.ui.details

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun DetailsScreen(
    state: DetailsScreenData
) {
    when(state) {
        is DetailsScreenData.Loading -> Text("Loading")
        is DetailsScreenData.Error -> Text("Error")
        is DetailsScreenData.Success -> Text(state.movieDetails.title ?: "")
    }
}