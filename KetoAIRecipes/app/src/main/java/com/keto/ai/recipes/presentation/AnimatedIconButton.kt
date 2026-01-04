package com.keto.ai.recipes.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.material.IconButton
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun AnimatedIconButton(
    visible: Boolean,
    icon: ImageVector,
    contentDescription: String?,
    onClick: () -> Unit
) {
    AnimatedVisibility(visible = visible, enter = androidx.compose.animation.fadeIn(tween(300)), exit = androidx.compose.animation.fadeOut(tween(300))) {
        IconButton(onClick = onClick) {
            Icon(icon, contentDescription = contentDescription)
        }
    }
}
