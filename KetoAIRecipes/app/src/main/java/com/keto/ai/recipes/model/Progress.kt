package com.keto.ai.recipes.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "progress")
data class Progress(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val userId: Long,
    val date: String,
    val weight: Double,
    val ketoneLevel: Double,
    val photoUrl: String?
)
