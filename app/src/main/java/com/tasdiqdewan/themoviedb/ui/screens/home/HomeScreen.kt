package com.tasdiqdewan.themoviedb.ui.screens.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
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