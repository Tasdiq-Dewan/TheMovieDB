package com.tasdiqdewan.compose_library.composables

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.progressSemantics
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

//adapted from https://github.com/SmartToolFactory/Compose-ProgressIndicator
@Composable
fun SpinningProgressIndicator(
    modifier: Modifier = Modifier,
    staticItemCount: Int = 12,
    dynamicItemCount: Int = staticItemCount / 2,
    durationMillis: Int = 1000,
    spinnerShape: SpinnerShape = SpinnerShape.RoundedRect,
    spinnerSize: Dp = 16.0.dp,
    staticItemColor: Color = Color(0xff757575),
    dynamicItemColor: Color = Color(0xffEEEEEE),
) {
    val animatedItemCount = dynamicItemCount.coerceIn(1, staticItemCount)

    val coefficient = 360f / staticItemCount

    val infiniteTransition = rememberInfiniteTransition()
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = staticItemCount.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = durationMillis,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        )
    )

    Canvas(modifier = modifier
        .progressSemantics()
        .size(spinnerSize)
    ) {

        var canvasWidth = size.width
        var canvasHeight = size.height

        if (canvasHeight < canvasWidth) {
            canvasWidth = canvasHeight
        } else {
            canvasHeight = canvasWidth
        }

        val itemWidth = canvasWidth * .3f
        val itemHeight = canvasHeight / staticItemCount

        val cornerRadius = itemWidth.coerceAtMost(itemHeight) / 2

        val horizontalOffset = (size.width - size.height).coerceAtLeast(0f) / 2
        val verticalOffset = (size.height - size.width).coerceAtLeast(0f) / 2

        val topLeftOffset = Offset(
            x = horizontalOffset + canvasWidth - itemWidth,
            y = verticalOffset + (canvasHeight - itemHeight) / 2
        )

        val size = Size(itemWidth, itemHeight)

        // Stationary items
        for (i in 0..360 step 360 / staticItemCount) {
            rotate(i.toFloat()) {
                if (spinnerShape == SpinnerShape.RoundedRect) {
                    drawRoundRect(
                        color = staticItemColor,
                        topLeft = topLeftOffset,
                        size = size,
                        cornerRadius = CornerRadius(cornerRadius, cornerRadius)
                    )
                } else {
                    drawRect(
                        color = staticItemColor,
                        topLeft = topLeftOffset,
                        size = size,
                    )
                }
            }
        }

        // Dynamic items
        for (i in 0..animatedItemCount) {
            // angle is cast to into move in intervals of static items
            rotate((angle.toInt() + i) * coefficient) {
                if (spinnerShape == SpinnerShape.RoundedRect) {
                    drawRoundRect(
                        color = dynamicItemColor.copy(
                            alpha = (1f / dynamicItemCount * i).coerceIn(0f, 1f)
                        ),
                        topLeft = topLeftOffset,
                        size = size,
                        cornerRadius = CornerRadius(cornerRadius, cornerRadius)
                    )
                } else {
                    drawRect(
                        color = dynamicItemColor.copy(
                            alpha = (0.2f + 0.15f * i).coerceIn(
                                0f, 1f
                            )
                        ),
                        topLeft = topLeftOffset,
                        size = size,
                    )
                }
            }
        }
    }
}

enum class SpinnerShape {
    Rect, RoundedRect
}

@Preview
@Composable
fun PreviewSpinningIndicator() {
    SpinningProgressIndicator()
}