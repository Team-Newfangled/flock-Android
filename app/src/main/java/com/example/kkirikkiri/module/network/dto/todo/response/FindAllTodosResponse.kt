package com.example.kkirikkiri.module.network.dto.todo.response

import com.example.kkirikkiri.module.network.dto.todo.TodoResponse
import com.google.gson.annotations.SerializedName

data class FindAllTodosResponse(var page : Int, @SerializedName("page-count") var pageCount : Int, var results : List<TodoResponse>)
