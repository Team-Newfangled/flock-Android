package com.example.kkirikkiri.module.dto.todo.response

import com.example.kkirikkiri.module.dto.todo.TodoResponse

data class FindAllTodosResponse(var page : Int, var pageCount : Int, var results : List<TodoResponse>)
