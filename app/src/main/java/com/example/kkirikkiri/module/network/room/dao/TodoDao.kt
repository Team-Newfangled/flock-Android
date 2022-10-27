package com.example.kkirikkiri.module.network.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.kkirikkiri.module.network.room.entity.TodoPercent


@Dao
interface TodoDao {
    @Query("select * from percent")
    fun getAll() : List<TodoPercent>

    @Insert(onConflict = REPLACE)
    fun insert(memo: TodoPercent)

    @Delete
    fun delete(memo: TodoPercent)
}