package com.example.kkirikkiri.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kkirikkiri.module.network.RetrofitImpl
import com.example.kkirikkiri.module.network.dto.ContentRequest
import com.example.kkirikkiri.module.network.dto.MessageResponse
import com.example.kkirikkiri.module.network.dto.todo.TodoResponse
import com.example.kkirikkiri.module.network.dto.todo.request.ModifyTodoRequest
import com.example.kkirikkiri.module.network.dto.todo.response.FindAllTodosResponse
import com.example.kkirikkiri.module.network.dto.todo.response.FindDeadLineResponse
import com.example.kkirikkiri.module.info.UserInfo
import com.example.kkirikkiri.module.network.dto.todo.Manager
import com.example.kkirikkiri.module.network.dto.todo.Percent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate

class TodoModel : ViewModel() {

    val service = RetrofitImpl.todoService

    val deadLineList = MutableLiveData<FindDeadLineResponse>()

    fun findDeadLines(projectId : Int) {
        val year = LocalDate.now().year
        val month = LocalDate.now().month.value

        CoroutineScope(Dispatchers.IO).launch {
            service.findDeadlines(UserInfo.access_token, projectId, year, month).enqueue(object : Callback<FindDeadLineResponse>{
                override fun onResponse(
                    call: Call<FindDeadLineResponse>,
                    response: Response<FindDeadLineResponse>
                ) {
                    if (response.isSuccessful) {
                        Log.e("성공", response.body().toString())
                        deadLineList.value = response.body()
                    }
                    else Log.e("실패", "접속은 했음, ${response.code()}, ${response.message()}, ${response.raw()}")
                }

                override fun onFailure(call: Call<FindDeadLineResponse>, t: Throwable) {
                    Log.e("실패", t.message.toString())
                }
            })
        }
    }

    fun createTodo(projectId: Int, request: ContentRequest) {
        CoroutineScope(Dispatchers.IO).launch {
            service.createTodo(UserInfo.access_token, projectId, request).enqueue(object : Callback<TodoResponse>{
                override fun onResponse(
                    call: Call<TodoResponse>,
                    response: Response<TodoResponse>
                ) {
                    if (response.isSuccessful) Log.e("성공", response.body().toString())
                    else Log.e("실패", "접속은 했음, ${response.code()}, ${response.message()}")
                }

                override fun onFailure(call: Call<TodoResponse>, t: Throwable) {
                    Log.e("실패", t.message.toString())
                }

            })
        }
    }

    fun modifyColor(projectId: Int, todoId : Int, request: ContentRequest) {
        CoroutineScope(Dispatchers.IO).launch {
            service.modifyColor(UserInfo.access_token, projectId, todoId, request).enqueue(object : Callback<MessageResponse>{
                override fun onResponse(
                    call: Call<MessageResponse>,
                    response: Response<MessageResponse>
                ) {
                    if (response.isSuccessful) Log.e("성공", response.body().toString())
                    else Log.e("실패", "접속은 했음")
                }

                override fun onFailure(call: Call<MessageResponse>, t: Throwable) {
                    Log.e("실패", t.message.toString())
                }
            })
        }
    }

    val allTodos = MutableLiveData<FindAllTodosResponse>()

    fun findAllTodos(projectId: Int, page : Int) {
        CoroutineScope(Dispatchers.IO).launch {
            service.findAllTodos(UserInfo.access_token, projectId, page).enqueue(object : Callback<FindAllTodosResponse>{
                override fun onResponse(
                    call: Call<FindAllTodosResponse>,
                    response: Response<FindAllTodosResponse>
                ) {
                    if (response.isSuccessful) {
                        Log.e("성공", response.body().toString())
                        allTodos.value = response.body()
                    }
                    else Log.e("실패", "접속은 했음")
                }

                override fun onFailure(call: Call<FindAllTodosResponse>, t: Throwable) {
                    Log.e("실패", t.message.toString())
                }

            })
        }
    }

    fun findTodo(todoId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            service.findTodo(UserInfo.access_token, todoId).enqueue(object : Callback<TodoResponse>{
                override fun onResponse(
                    call: Call<TodoResponse>,
                    response: Response<TodoResponse>
                ) {
                    if (response.isSuccessful) Log.e("성공", response.body().toString())
                    else Log.e("실패", "접속은 했음")
                }

                override fun onFailure(call: Call<TodoResponse>, t: Throwable) {
                    Log.e("실패", t.message.toString())
                }

            })
        }
    }

    fun modifyTodo(todoId: Int, request: ModifyTodoRequest) {
        CoroutineScope(Dispatchers.IO).launch {
            service.modifyTodo(UserInfo.access_token, todoId, request).enqueue(object : Callback<MessageResponse>{
                override fun onResponse(
                    call: Call<MessageResponse>,
                    response: Response<MessageResponse>
                ) {
                    if (response.isSuccessful) Log.e("성공", response.body().toString())
                    else Log.e("실패", "접속은 했음, ${response.raw()}")
                }

                override fun onFailure(call: Call<MessageResponse>, t: Throwable) {
                    Log.e("실패", t.message.toString())
                }

            })
        }
    }

    fun deleteTodo(todoId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            service.deleteTodo(UserInfo.access_token, todoId).enqueue(object : Callback<Void>{
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Log.e("code", "${response.code()}")
                }
                override fun onFailure(call: Call<Void>, t: Throwable) {

                }

            })
        }
    }

    fun completeTodo(todoId : Int) {
        CoroutineScope(Dispatchers.IO).launch {
            service.completeTodo(UserInfo.access_token, todoId).enqueue(object : Callback<MessageResponse>{
                override fun onResponse(
                    call: Call<MessageResponse>,
                    response: Response<MessageResponse>
                ) {
                    if (response.isSuccessful) Log.e("성공", response.body().toString())
                    else Log.e("실패", "접속은 했음")
                }

                override fun onFailure(call: Call<MessageResponse>, t: Throwable) {
                    Log.e("실패", t.message.toString())
                }

            })
        }
    }

    fun modifyPercent(todoId: Int, percent : Int) {
        CoroutineScope(Dispatchers.IO).launch {
            service.modifyPercent(UserInfo.access_token, todoId, Percent(percent)).enqueue(object : Callback<MessageResponse>{
                override fun onResponse(
                    call: Call<MessageResponse>,
                    response: Response<MessageResponse>
                ) {
                    if (!response.isSuccessful) Log.e("Fail", response.raw().toString())
                }

                override fun onFailure(call: Call<MessageResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }
    }

    fun modifyManager(todoId: Int, manager : Int) {
        CoroutineScope(Dispatchers.IO).launch {
            service.modifyManager(UserInfo.access_token, todoId, Manager(manager)).enqueue(object : Callback<MessageResponse>{
                override fun onResponse(
                    call: Call<MessageResponse>,
                    response: Response<MessageResponse>
                ) {
                    if (!response.isSuccessful) Log.e("Fail", response.raw().toString())
                }

                override fun onFailure(call: Call<MessageResponse>, t: Throwable) {
                }

            })
        }
    }
}