package com.example.kkirikkiri.module.dto.todo.request

import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class ModifyTodoRequest(var content : String, @SerializedName("end-date") var endDate : LocalDate,@SerializedName("start-date") var startDate: LocalDate)
