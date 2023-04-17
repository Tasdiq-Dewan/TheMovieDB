package com.tasdiqdewan.compose_library.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tasdiqdewan.compose_library.R
import com.tasdiqdewan.utils.Constants.POSTER_BASE_URL

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
            .wrapContentSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(16.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(POSTER_BASE_URL+"w200"+posterPath)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(id = R.drawable.loading_img),
                contentDescription = ""
            )
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