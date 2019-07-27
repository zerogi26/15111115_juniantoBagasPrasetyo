package com.umby.hello.uas15111115.daos

import android.arch.persistence.room.*
import com.umby.hello.uas15111115.models.ProductModel

@Dao
public interface ProductDao {
    @Insert
    public fun insert(vararg products: ProductModel)

    @Update
    public fun update(vararg products: ProductModel)

    @Delete
    public fun delete(product: ProductModel)

    @Query("SELECT * FROM products")
    public fun getProducts(): List<ProductModel>

    @Query("SELECT * FROM products WHERE id = :id")
    public fun getProduct(id: Int): ProductModel
}