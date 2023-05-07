package com.tasdiqdewan.compose_library.composables

import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tasdiqdewan.compose_library.R
import com.tasdiqdewan.utils.Constants
import com.tasdiqdewan.utils.Constants.SIMPLE_DATE_FORMAT_UK
import com.tasdiqdewan.utils.Constants.YEAR
import com.tasdiqdewan.utils.PosterSize
import com.tasdiqdewan.utils.convertToDateFormat
import java.util.Locale

@Composable
fun MovieDetails(
    id: Int,
    title: String,
    releaseDate: String,
    posterPath: String?,
    voteAverage: Double,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(horizontal = 8.dp)
            .verticalScroll(
                enabled = true,
                state = rememberScrollState()
            )
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = MaterialTheme.typography.titleLarge.toSpanStyle()
                ) {
                    append(title)
                }
                withStyle(
                    style = MaterialTheme.typography.labelLarge.toSpanStyle()
                ) {
                    append(" (${releaseDate.convertToDateFormat(YEAR)})")
                }
            }
        )
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = MaterialTheme.typography.bodyMedium.toSpanStyle()
                ) {
                    append("${releaseDate.convertToDateFormat(SIMPLE_DATE_FORMAT_UK)} (${Locale.getDefault().country})")
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        posterPath?.let {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(Constants.POSTER_BASE_URL + PosterSize.W780.size+posterPath)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(id = R.drawable.loading_img),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
            )
        }
    }
}

@Preview
@Composable
fun MovieDetailsPreview() {
    MaterialTheme{
        MovieDetails(
            id = 1,
            title = "Spider-Man: Into the Spider-Verse",
            releaseDate = "2018-12-14",
            posterPath = "/iiZZdoQBEYBv6id8su7ImL0oCbD.jpg",
            voteAverage = 8.407
        )
    }
}