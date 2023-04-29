package com.tasdiqdewan.compose_library.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
        .fillMaxSize()
){
    SpinningProgressIndicator()
}