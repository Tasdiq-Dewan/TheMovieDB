package com.tasdiqdewan.compose_library.composables.movie_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tasdiqdewan.compose_library.R
import com.tasdiqdewan.compose_library.composables.VoteAverage
import com.tasdiqdewan.utils.domain.Genre

@Composable
fun MovieDetailsSummary(
    voteAverage: Double,
    tagline: String?,
    overview: String?,
    runtime: Int? = null,
    genres: List<Genre> = listOf(),
    modifier: Modifier = Modifier
) {
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
                    .padding(horizontal = 24.dp, vertical = 12.dp)
                    .wrapContentWidth()
            ) {
                Text(
                    text = stringResource(R.string.user_score),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .padding(end = 16.dp)
                )
                VoteAverage(
                    voteAverage = voteAverage.toFloat(),
                    modifier = Modifier
                )
            }
            runtime?.let {runtime ->
                Text(
                    text = "Runtime: $runtime minutes",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                )
            }
            genres.takeIf { it.isNotEmpty() }.let {
                GenreList(genres = genres)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = tagline ?: String(),
                style = MaterialTheme.typography.labelMedium.copy(
                    fontStyle = FontStyle.Italic
                ),
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
            )
            Text(
                text = overview ?: String(),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
            )
        }
    }
}

@Composable
fun GenreList(
    genres: List<Genre>,
    modifier: Modifier = Modifier
) {
    Text(
        text = genres.map { it.name }.joinToString(", "),
        style = MaterialTheme.typography.titleSmall
    )
}

@Preview
@Composable
fun MovieDetailsSummaryPreview() {
    MaterialTheme {
        MovieDetailsSummary(
            voteAverage = 8.407,
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