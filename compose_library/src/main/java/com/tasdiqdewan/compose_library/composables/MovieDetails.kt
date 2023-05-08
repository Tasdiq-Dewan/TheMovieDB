package com.tasdiqdewan.compose_library.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tasdiqdewan.compose_library.R
import com.tasdiqdewan.utils.Constants
import com.tasdiqdewan.utils.Constants.DATE_FORMAT_YEAR
import com.tasdiqdewan.utils.Constants.SIMPLE_DATE_FORMAT_UK
import com.tasdiqdewan.utils.Constants.SIMPLE_DATE_FORMAT_US
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
    certification: String,
    tagline: String?,
    overview: String?,
    runtime: Int?,
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
                    style = MaterialTheme.typography.titleLarge
                        .copy(fontWeight = FontWeight.Bold).toSpanStyle()
                ) {
                    append(title)
                }
                withStyle(
                    style = MaterialTheme.typography.labelLarge.toSpanStyle()
                ) {
                    append(" (${releaseDate.convertToDateFormat(DATE_FORMAT_YEAR)})")
                }
            },
            modifier = Modifier
                .padding(8.dp)
        )
        Row {
            Box(
                modifier = Modifier
                    .border(1.dp, MaterialTheme.colorScheme.onSurface)
            ) {
                Text(
                    text = certification,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Light),
                    modifier = Modifier
                        .padding(2.dp)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = MaterialTheme.typography.bodyMedium.toSpanStyle()
                    ) {
                        val local = Locale.getDefault().country
                        append("${releaseDate.convertToDateFormat(if(local == "US") SIMPLE_DATE_FORMAT_US else SIMPLE_DATE_FORMAT_UK)} ($local)")
                    }
                },
                modifier = Modifier
                    .padding(2.dp)
            )
        }
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
        Spacer(modifier = Modifier.height(4.dp))
        Card(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(24.dp)
                        .wrapContentWidth()
                ) {
                    VoteAverage(
                        voteAverage = voteAverage.toFloat(),
                        modifier = Modifier
                    )
                    Text(
                        text = "User Score",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier
                            .padding(start = 8.dp)
                    )
                }
                Text(
                    text = tagline ?: "",
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontStyle = FontStyle.Italic
                    ),
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                )
                Text(
                    text = overview ?: "",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                )
            }
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
            voteAverage = 8.407,
            certification = "PG",
            tagline = "More than one wears the mask.",
            overview = "Miles Morales is juggling his life between being a high school student and being a spider-man. When Wilson \\\"Kingpin\\\" Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension.",
            runtime = 117
        )
    }
}