package com.example.kkirikkiri.module.dto.todo

import java.time.LocalDate

data class TodoResponse(var color : String, var completed : Boolean, var content : String, var endDate: LocalDate, var id : Int, var startDate: LocalDate, var writerId : Int)
