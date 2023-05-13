package com.tasdiqdewan.compose_library.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LoadingScreen(
    color: Color = MaterialTheme.colors.secondary,
    modifier: Modifier = Modifier
        .fillMaxSize()
){
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(
            color = color,
            modifier = Modifier
                .align(Alignment.Center)
                .size(48.dp)
        )
    }
}

@Preview
@Composable
fun LoadingScreenPreview() {
    LoadingScreen()
}