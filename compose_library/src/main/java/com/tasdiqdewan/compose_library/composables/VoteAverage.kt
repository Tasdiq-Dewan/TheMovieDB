package com.tasdiqdewan.compose_library.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun VoteAverage(
    voteAverage: Float,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        val circleColor = MaterialTheme.colorScheme.primary
        CircularProgressIndicator(
            progress = voteAverage / 10,
            color = MaterialTheme.colorScheme.tertiary,
            backgroundColor = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.45f) ,
            modifier = Modifier
                .drawBehind {
                    drawCircle(
                        color = circleColor,
                        radius = this.size.maxDimension / 2.0f + 8
                    )
                }
        )
        Text(
            text = (voteAverage*10).toInt().toString(),
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Preview
@Composable
fun VoteAveragePreview100() {
    VoteAverage(voteAverage = 10f)
}

@Preview
@Composable
fun VoteAveragePreview50() {
    VoteAverage(voteAverage = 5f)
}