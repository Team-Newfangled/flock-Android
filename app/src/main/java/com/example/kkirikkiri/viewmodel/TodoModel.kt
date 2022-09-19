package com.example.kkirikkiri.viewmodel

import androidx.lifecycle.ViewModel
import com.example.kkirikkiri.module.RetrofitImpl
import com.example.kkirikkiri.module.dto.ContentRequest
import com.example.kkirikkiri.module.dto.MessageResponse
import com.example.kkirikkiri.module.dto.todo.TodoResponse
import com.example.kkirikkiri.module.dto.todo.request.ModifyTodoRequest
import com.example.kkirikkiri.module.dto.todo.response.FindAllTodosResponse
import com.example.kkirikkiri.module.dto.todo.response.FindDeadLineResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.coroutineContext

class TodoModel : ViewModel() {

    val service = RetrofitImpl.todoService

    fun findDeadLines(projectId : Int, year : Int, month : Int) {
        CoroutineScope(Dispatchers.IO).launch {
            service.findDeadlines(projectId, year, month).enqueue(object : Callback<FindDeadLineResponse>{
                override fun onResponse(
                    call: Call<FindDeadLineResponse>,
                    response: Response<FindDeadLineResponse>
                ) {

                }

                override fun onFailure(call: Call<FindDeadLineResponse>, t: Throwable) {

                }
            })
        }
    }

    fun createTodo(projectId: Int, request: ContentRequest) {
        CoroutineScope(Dispatchers.IO).launch {
            service.createTodo(projectId, request).enqueue(object : Callback<TodoResponse>{
                override fun onResponse(
                    call: Call<TodoResponse>,
                    response: Response<TodoResponse>
                ) {

                }

                override fun onFailure(call: Call<TodoResponse>, t: Throwable) {

                }

            })
        }
    }

    fun modifyColor(projectId: Int, todoId : Int, request: ContentRequest) {
        CoroutineScope(Dispatchers.IO).launch {
            service.modifyColor(projectId, todoId, request).enqueue(object : Callback<MessageResponse>{
                override fun onResponse(
                    call: Call<MessageResponse>,
                    response: Response<MessageResponse>
                ) {

                }

                override fun onFailure(call: Call<MessageResponse>, t: Throwable) {

                }
            })
        }
    }

    fun findAllTodos(projectId: Int, userId : Int, page : Int) {
        CoroutineScope(Dispatchers.IO).launch {
            service.findAllTodos(projectId, userId, page).enqueue(object : Callback<FindAllTodosResponse>{
                override fun onResponse(
                    call: Call<FindAllTodosResponse>,
                    response: Response<FindAllTodosResponse>
                ) {

                }

                override fun onFailure(call: Call<FindAllTodosResponse>, t: Throwable) {

                }

            })
        }
    }

    fun findTodo(todoId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            service.findTodo(todoId).enqueue(object : Callback<TodoResponse>{
                override fun onResponse(
                    call: Call<TodoResponse>,
                    response: Response<TodoResponse>
                ) {

                }

                override fun onFailure(call: Call<TodoResponse>, t: Throwable) {

                }

            })
        }
    }

    fun modifyTodo(todoId: Int, request: ModifyTodoRequest) {
        CoroutineScope(Dispatchers.IO).launch {
            service.modifyTodo(todoId, request).enqueue(object : Callback<MessageResponse>{
                override fun onResponse(
                    call: Call<MessageResponse>,
                    response: Response<MessageResponse>
                ) {

                }

                override fun onFailure(call: Call<MessageResponse>, t: Throwable) {

                }

            })
        }
    }

    fun deleteTodo(todoId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            service.deleteTodo(todoId)
        }
    }

    fun completeTodo(todoId : Int) {
        CoroutineScope(Dispatchers.IO).launch {
            service.completeTodo(todoId).enqueue(object : Callback<MessageResponse>{
                override fun onResponse(
                    call: Call<MessageResponse>,
                    response: Response<MessageResponse>
                ) {

                }

                override fun onFailure(call: Call<MessageResponse>, t: Throwable) {

                }

            })
        }
    }
}