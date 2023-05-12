package com.tasdiqdewan.compose_library.composables.movie_details

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tasdiqdewan.utils.Constants
import com.tasdiqdewan.utils.convertToDateFormat
import java.util.Locale

@Composable
fun MovieDetailsTitle(
    title: String,
    certification: String? = null,
    releaseDate: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
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
                    append(" (${releaseDate.convertToDateFormat(Constants.DATE_FORMAT_YEAR)})")
                }
            },
            modifier = Modifier
                .padding(8.dp)
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            certification?.let {
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
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = MaterialTheme.typography.bodyMedium.toSpanStyle()
                    ) {
                        val local = Locale.getDefault().country
                        append("${releaseDate.convertToDateFormat(if(local == "US") Constants.SIMPLE_DATE_FORMAT_US else Constants.SIMPLE_DATE_FORMAT_UK)} ($local)")
                    }
                },
                modifier = Modifier
                    .padding(2.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieDetailsTitlePreview() {
    MaterialTheme {
        MovieDetailsTitle(
            title = "Spider-Man: Into the Spider-Verse",
            releaseDate = "2018-12-14",
            certification = "PG"
        )
    }
}