package com.umby.hello.uas15111115.databases

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.umby.hello.uas15111115.daos.ProductDao
import com.umby.hello.uas15111115.models.ProductModel

@Database(entities = [ProductModel::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    public abstract fun getProductDao(): ProductDao
}