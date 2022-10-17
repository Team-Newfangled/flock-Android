package com.example.kkirikkiri.module.dto.todo

import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class TodoResponse(var color : String,
                        var completed : Boolean,
                        var content : String,
                        @SerializedName("end-date") var endDate: String,
                        var id : Int,
                        @SerializedName("start-date") var startDate: String,
                        @SerializedName("writer_id") var writerId : Int)
