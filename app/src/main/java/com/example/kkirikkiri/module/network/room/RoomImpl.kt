package com.example.kkirikkiri.module.network.room

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kkirikkiri.module.network.room.helper.TodoHelper

object RoomImpl {

    private fun createHelper(context: Context): TodoHelper {
        return Room.databaseBuilder(context, TodoHelper::class.java, "percent")
            .allowMainThreadQueries()
            .build()
    }

    fun getHelper(context: Context) : TodoHelper {
        return createHelper(context)
    }

}