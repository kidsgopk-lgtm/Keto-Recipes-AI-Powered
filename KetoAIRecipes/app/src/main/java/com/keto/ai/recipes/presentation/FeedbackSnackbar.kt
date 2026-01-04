package com.keto.ai.recipes.presentation

import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun FeedbackSnackbar(message: String, actionLabel: String? = null, onAction: (() -> Unit)? = null) {
    val snackbarHostState = remember { SnackbarHostState() }
    SnackbarHost(hostState = snackbarHostState, modifier = Modifier) {
        Snackbar(
            action = {
                if (actionLabel != null && onAction != null) {
                    androidx.compose.material.TextButton(onClick = onAction) {
                        androidx.compose.material.Text(actionLabel)
                    }
                }
            },
            content = { androidx.compose.material.Text(message) }
        )
    }
}
