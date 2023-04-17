package com.tasdiqdewan.compose_library.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MovieResult(
    id: Int,
    title: String,
    releaseDate: String,
    posterPath: String,
    backdropPath: String,
    voteAverage: Double,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = title)
            Text(text = releaseDate)
        }
    }
}

@Preview
@Composable
fun MovieResultPreview() {
    MovieResult(
        id = 1,
        title = "The Super Mario Bros. Movie",
        releaseDate = "2023-04-05",
        posterPath = "",
        backdropPath = "",
        voteAverage = 5.0
    )
}