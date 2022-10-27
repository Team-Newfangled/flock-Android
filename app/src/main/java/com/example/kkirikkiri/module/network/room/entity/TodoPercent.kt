package com.example.kkirikkiri.module.network.room.entity

import androidx.room.Entity
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

@Entity(tableName = "percent")
class TodoPercent (@ColumnInfo var teamId : Int,
                   @ColumnInfo var projectId : Int,
                   @ColumnInfo var todoName : String,
                   @ColumnInfo var writerName : String,
                   @ColumnInfo var percent: Int) {

    @PrimaryKey
    @ColumnInfo
    var id : Long? = null
}