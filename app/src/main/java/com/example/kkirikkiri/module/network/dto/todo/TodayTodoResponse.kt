package com.example.kkirikkiri.module.network.dto.todo

data class TodayTodoResponse(var results : List<TodoContentResponse>){
    data class TodoContentResponse(var todo_content : String)
}
