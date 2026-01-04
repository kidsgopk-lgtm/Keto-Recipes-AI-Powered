package com.keto.ai.recipes.data

import androidx.room.*
import com.keto.ai.recipes.model.Product

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProduct(product: Product)

    @Query("SELECT * FROM products WHERE barcode = :barcode")
    suspend fun getProduct(barcode: String): Product?

    @Query("SELECT * FROM products")
    suspend fun getAllProducts(): List<Product>
}
