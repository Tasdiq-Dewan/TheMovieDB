package com.tasdiqdewan.compose_library.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
        .fillMaxSize()
){
    SpinningProgressIndicator()
}