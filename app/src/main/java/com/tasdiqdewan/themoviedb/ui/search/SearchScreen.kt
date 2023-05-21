package com.tasdiqdewan.themoviedb.ui.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tasdiqdewan.compose_library.composables.movie_details.Search
import com.tasdiqdewan.themoviedb.ui.theme.TheMovieDBTheme

@Composable
fun SearchScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Search(
            onSearch = {},
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .padding(horizontal = 6.dp)
        )
    }
}

@Preview(showBackground = true, device = "id:pixel_5")
@Composable
fun SearchScreenPreview() {
    TheMovieDBTheme {
        SearchScreen()
    }
}