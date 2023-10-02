package com.example.gg.roomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.Nonnull


@Entity(tableName = "products")
data class ProductModel(
    @PrimaryKey

    val productID : String,
    @ColumnInfo(name = "productName")
    val productName : String? = "",
    @ColumnInfo(name = "productImage")
    val productImage : String? = "",
    @ColumnInfo(name = "productSp")
    val productSp : String? = "",

)


