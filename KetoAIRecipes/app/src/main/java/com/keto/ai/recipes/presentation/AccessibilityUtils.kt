package com.keto.ai.recipes.presentation

import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics

object AccessibilityUtils {
    fun Modifier.accessibleLabel(label: String): Modifier =
        this.semantics { contentDescription = label }
}
