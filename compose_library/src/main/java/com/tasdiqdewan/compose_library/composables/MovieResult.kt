package com.tasdiqdewan.compose_library.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
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
            Box(
                modifier = Modifier
                    .height(160.dp)
                    .width(112.dp)
            ) {
                AsyncImageWithIndicator(
                    posterPath = posterPath,
                    posterSize = PosterSize.W342.size,
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .align(Alignment.Center)
                )
                VoteAverage(
                    voteAverage = voteAverage.toFloat(),
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                )
            }
            Spacer(modifier = Modifier.height(4.dp))

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