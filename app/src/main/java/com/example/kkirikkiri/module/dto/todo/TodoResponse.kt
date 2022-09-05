package com.example.kkirikkiri.module.dto.todo

import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class TodoResponse(var color : String,
                        var completed : Boolean,
                        var content : String,
                        @SerializedName("end-date") var endDate: LocalDate,
                        var id : Int,
                        @SerializedName("start-date") var startDate: LocalDate,
                        @SerializedName("writer-id") var writerId : Int)
