package com.keto.ai.recipes.data

import androidx.room.*
import com.keto.ai.recipes.model.Progress

@Dao
interface ProgressDao {
    @Insert suspend fun insert(progress: Progress): Long
    @Update suspend fun update(progress: Progress)
    @Delete suspend fun delete(progress: Progress)
    @Query("SELECT * FROM progress WHERE userId = :userId") suspend fun getByUserId(userId: Long): List<Progress>
}
