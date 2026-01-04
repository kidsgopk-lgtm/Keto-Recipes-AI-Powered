package com.keto.ai.recipes.data

import androidx.room.*
import com.keto.ai.recipes.model.User

@Dao
interface UserDao {
    @Insert suspend fun insert(user: User): Long
    @Update suspend fun update(user: User)
    @Delete suspend fun delete(user: User)
    @Query("SELECT * FROM users WHERE id = :id") suspend fun getById(id: Long): User?
}
