package com.example.gg.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductDao {
    @Insert
     fun insertProduct(product : ProductModel)

    @Delete
     fun deleteProduct(product : ProductModel)

    @Query("SELECT * FROM products")
    fun getAllProduct() : LiveData<List<ProductModel>>

    @Query("SELECT * FROM products WHERE productID = :id")
    fun isExit(id : String) : ProductModel

}