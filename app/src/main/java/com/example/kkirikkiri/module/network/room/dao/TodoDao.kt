package com.example.kkirikkiri.module.network.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.kkirikkiri.module.network.room.entity.TodoPercent


@Dao
interface TodoDao {
    @Query("select * from percent where projectId = :project")
    fun getAllByProject(project : Int) : List<TodoPercent>

    @Query("select * from percent where id = :id")
    fun getTodoById(id : Long) : TodoPercent

    @Insert(onConflict = REPLACE)
    fun insert(memo: TodoPercent)

    @Query("delete from percent where id = :id")
    fun delete(id : Long)
}