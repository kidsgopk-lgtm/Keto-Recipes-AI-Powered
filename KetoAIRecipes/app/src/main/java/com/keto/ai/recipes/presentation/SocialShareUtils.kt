package com.keto.ai.recipes.presentation

import android.content.Context
import android.content.Intent

object SocialShareUtils {
    fun shareText(context: Context, text: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, text)
        context.startActivity(Intent.createChooser(intent, "Share via"))
    }
}
