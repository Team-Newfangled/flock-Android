package com.example.kkirikkiri.module.dto.todo.request

import java.time.LocalDate

data class ModifyTodoRequest(var content : String, var endDate : LocalDate, var startDate: LocalDate)
