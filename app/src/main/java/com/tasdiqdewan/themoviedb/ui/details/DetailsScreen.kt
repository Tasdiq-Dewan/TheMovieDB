package com.tasdiqdewan.themoviedb.ui.details

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.tasdiqdewan.compose_library.composables.ErrorScreen
import com.tasdiqdewan.compose_library.composables.LoadingScreen

@Composable
fun DetailsScreen(
    state: DetailsScreenData
) {
    when(state) {
        is DetailsScreenData.Loading -> LoadingScreen()
        is DetailsScreenData.Error -> ErrorScreen()
        is DetailsScreenData.Success -> Text(state.movieDetails.title ?: "")
    }
}