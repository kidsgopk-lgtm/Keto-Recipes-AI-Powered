package com.keto.ai.recipes.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

@Composable
fun ProfileAvatar(avatarUrl: String?, onAvatarClick: () -> Unit) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = Color.LightGray,
        modifier = Modifier.size(80.dp).clickable { onAvatarClick() }
    ) {
        Box(contentAlignment = Alignment.Center) {
            if (avatarUrl != null && avatarUrl.isNotBlank()) {
                Image(
                    painter = rememberImagePainter(avatarUrl),
                    contentDescription = "Profile Avatar",
                    modifier = Modifier.size(72.dp)
                )
            } else {
                Icon(Icons.Filled.AccountCircle, contentDescription = "Default Avatar", modifier = Modifier.size(72.dp))
            }
        }
    }
}
