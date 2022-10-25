package com.example.kkirikkiri.module.dto.todo.request

import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class ModifyTodoRequest(var content : String, @SerializedName("end_date") var endDate : String,@SerializedName("start_date") var startDate: String)
