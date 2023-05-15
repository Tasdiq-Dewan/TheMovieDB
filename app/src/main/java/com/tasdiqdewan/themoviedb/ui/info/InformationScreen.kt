package com.tasdiqdewan.themoviedb.ui.info

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tasdiqdewan.compose_library.composables.HyperlinkText
import com.tasdiqdewan.themoviedb.R
import com.tasdiqdewan.themoviedb.ui.theme.TheMovieDBTheme

@Composable
fun InformationScreen() {
    var offset = remember { mutableStateOf(0f) }
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .wrapContentSize()
        ) {
            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.tmdb_attribution),
                    style = MaterialTheme.typography.headlineLarge,
                )
                Spacer(Modifier.height(12.dp))

                HyperlinkText(
                    fullText = stringResource(R.string.tmdb_attribution_body).trimMargin(),
                    linkText = listOf(
                        "https://developers.themoviedb.org/3/getting-started/introduction",
                        "https://www.themoviedb.org/"
                    ),
                    hyperlinks = listOf(
                        "https://developers.themoviedb.org/3/getting-started/introduction",
                        "https://www.themoviedb.org/"
                    ),
                    textStyle = MaterialTheme.typography.titleLarge,
                    linkStyle = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.secondary)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InformationScreenPreview() {
    TheMovieDBTheme {
        InformationScreen()
    }
}