package com.tasdiqdewan.compose_library.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tasdiqdewan.compose_library.R
import com.tasdiqdewan.utils.Constants
import com.tasdiqdewan.utils.Constants.YEAR
import com.tasdiqdewan.utils.PosterSize
import com.tasdiqdewan.utils.convertToDateFormat

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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 16.dp)
        ) {
            val localDensity = LocalDensity.current
            var imageHeight by remember { mutableStateOf(0.dp) }
            posterPath?.let {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(Constants.POSTER_BASE_URL + PosterSize.W500.size+posterPath)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(id = R.drawable.loading_img),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .onGloballyPositioned {
                            imageHeight = with(localDensity) { it.size.height.toDp() }
                        },
                )
            }
            Card(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .fillMaxWidth()
                    .height(imageHeight)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                ) {
                    CircularProgressIndicator(
                        progress = voteAverage.toFloat() / 10,
                        color = MaterialTheme.colorScheme.tertiary,
                    )
                    Text(text = (voteAverage*10).toInt().toString())
                }
            }
        }
    }
}