package com.tasdiqdewan.compose_library.composables.movie_details

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
import com.tasdiqdewan.compose_library.composables.AsyncImageWithIndicator
import com.tasdiqdewan.compose_library.composables.VoteAverage
import com.tasdiqdewan.utils.Constants
import com.tasdiqdewan.utils.Constants.DATE_FORMAT_YEAR
import com.tasdiqdewan.utils.Constants.SIMPLE_DATE_FORMAT_UK
import com.tasdiqdewan.utils.Constants.SIMPLE_DATE_FORMAT_US
import com.tasdiqdewan.utils.PosterSize
import com.tasdiqdewan.utils.convertToDateFormat
import com.tasdiqdewan.utils.domain.Genre
import java.util.Locale

@Composable
fun MovieDetails(
    title: String,
    releaseDate: String,
    posterPath: String?,
    voteAverage: Double,
    certification: String? = null,
    tagline: String?,
    overview: String?,
    runtime: Int?,
    genres: List<Genre> = listOf(),
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
        MovieDetailsTitle(
            title = title,
            certification = certification,
            releaseDate = releaseDate
        )
        Spacer(modifier = Modifier.height(16.dp))
        posterPath?.let {
            AsyncImageWithIndicator(
                posterPath = posterPath,
                posterSize = PosterSize.W780.size,
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        MovieDetailsSummary(
            voteAverage = voteAverage,
            tagline = tagline,
            overview = overview,
            genres = genres,
            runtime = runtime
        )
    }
}


@Preview
@Composable
fun MovieDetailsPreview() {
    MaterialTheme{
        MovieDetails(
            title = "Spider-Man: Into the Spider-Verse",
            releaseDate = "2018-12-14",
            posterPath = "/iiZZdoQBEYBv6id8su7ImL0oCbD.jpg",
            voteAverage = 8.407,
            certification = "PG",
            tagline = "More than one wears the mask.",
            overview = "Miles Morales is juggling his life between being a high school student and being a spider-man. When Wilson \\\"Kingpin\\\" Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension.",
            runtime = 117,
            genres = listOf(
                Genre(id = 28, name = "Action"),
                Genre(id = 12, name = "Adventure"),
                Genre(id = 16, name = "Animation"),
                Genre(id = 878, name = "Science Fiction")
            )
        )
    }
}