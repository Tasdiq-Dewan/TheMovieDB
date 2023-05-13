package com.tasdiqdewan.compose_library.composables

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.tasdiqdewan.compose_library.R
import java.io.IOException

@Composable
fun ErrorScreen(
    exception: Exception,
    onDismiss: () -> Unit = {  },
    retry: () -> Unit = {  },
    modifier: Modifier = Modifier
){
    var openDialog by remember { mutableStateOf(true) }

    if(openDialog) {
        AlertDialog(
            onDismissRequest = {
                openDialog = false
                onDismiss()
            },
            title = {
                    Text(
                        text = stringResource(R.string.error_screen_title),
                        style = MaterialTheme.typography.titleMedium
                    )
            },
            text = { ErrorText(exception = exception) },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog = false
                        retry()
                    }
                ) {
                    Text(
                        text = stringResource(R.string.retry),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog = false
                        onDismiss()
                    }
                ) {
                    Text(
                        text = stringResource(R.string.error_dismiss),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            },
            modifier = modifier
        )
    }
}

@Composable
fun ErrorText(
    exception: Exception,
    modifier: Modifier = Modifier
) {
    val text = when(exception) {
        is IOException -> stringResource(R.string.io_exception_dialog)
        else -> stringResource(R.string.other_exception_dialog)
    }
    Text(
        text = text,
        style = MaterialTheme.typography.bodyLarge
    )
}

@Preview
@Composable
fun ErrorScreenIoExceptionPreview() {
    MaterialTheme {
        ErrorScreen(exception = IOException())
    }
}

@Preview
@Composable
fun ErrorScreenExceptionPreview() {
    MaterialTheme {
        ErrorScreen(exception = Exception())
    }
}