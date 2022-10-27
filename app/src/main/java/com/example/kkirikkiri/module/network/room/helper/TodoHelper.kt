package com.example.kkirikkiri.module.network.room.helper

import androidx.room.RoomDatabase
import androidx.room.Database
import com.example.kkirikkiri.module.network.room.dao.TodoDao
import com.example.kkirikkiri.module.network.room.entity.TodoPercent

@Database(entities = [TodoPercent::class], version = 1, exportSchema = false)
abstract class TodoHelper : RoomDatabase() {
    abstract fun todoPercentDao() : TodoDao
}