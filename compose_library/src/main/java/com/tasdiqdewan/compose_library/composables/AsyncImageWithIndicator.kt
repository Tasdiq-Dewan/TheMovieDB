package com.tasdiqdewan.compose_library.composables

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.tasdiqdewan.utils.Constants

@Composable
fun AsyncImageWithIndicator(
    posterPath: String,
    posterSize: String,
    indicatorColor: Color = MaterialTheme.colorScheme.secondary,
    description: String = String(),
    contentScale: ContentScale = ContentScale.Crop,
    modifier: Modifier = Modifier
        .clip(RoundedCornerShape(12.dp))
) {
    val imagePainter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(Constants.POSTER_BASE_URL + posterSize + posterPath)
            .size(coil.size.Size.ORIGINAL)
            .crossfade(true)
            .build(),
    )
    var showImage = remember { mutableStateOf(true) }
    if(showImage.value) {
        Image(
            painter = imagePainter,
            contentDescription = description,
            contentScale = contentScale,
            modifier = modifier
        )
    }

    if(imagePainter.state is AsyncImagePainter.State.Loading) {
        showImage.value = false
        CircularProgressIndicator(
            color = indicatorColor,
            modifier = Modifier
                .size(48.dp)
        )
    } else if(imagePainter.state is AsyncImagePainter.State.Error) {
        showImage.value = false
        Icon(
            imageVector = Icons.Default.BrokenImage,
            contentDescription = description,
            modifier = modifier,
            tint = indicatorColor
        )
    } else {
        showImage.value = true
    }
}