package com.tasdiqdewan.compose_library.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tasdiqdewan.compose_library.R
import com.tasdiqdewan.utils.Constants.DAY_SHORT_MONTH_YEAR
import com.tasdiqdewan.utils.Constants.POSTER_BASE_URL
import com.tasdiqdewan.utils.PosterSize
import com.tasdiqdewan.utils.convertToDateFormat

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
            .heightIn(min = 240.dp)
            .width(400.dp)
            .padding(8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(POSTER_BASE_URL+PosterSize.W342.size+posterPath)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(id = R.drawable.loading_img),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp)),
            )
            Spacer(modifier = Modifier.height(4.dp))
            Box(contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    progress = voteAverage.toFloat() / 10,
                    color = MaterialTheme.colorScheme.tertiary
                )
                Text(text = voteAverage.toString())
            }
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
            )
            Text(
                text = releaseDate.convertToDateFormat(DAY_SHORT_MONTH_YEAR),
                style = MaterialTheme.typography.labelLarge
            )
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