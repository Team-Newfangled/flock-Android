package com.example.kkirikkiri.module.network.service

import com.example.kkirikkiri.module.network.dto.ContentRequest
import com.example.kkirikkiri.module.network.dto.MessageResponse
import com.example.kkirikkiri.module.network.dto.todo.TodoResponse
import com.example.kkirikkiri.module.network.dto.todo.request.ModifyTodoRequest
import com.example.kkirikkiri.module.network.dto.todo.response.FindAllTodosResponse
import com.example.kkirikkiri.module.network.dto.todo.response.FindDeadLineResponse
import retrofit2.Call
import retrofit2.http.*

interface TodoService {
    @GET("/projects/{id}/deadline?")
    fun findDeadlines(@Header("authorization") token : String, @Path("id") id : Int,@Query("year") year : Int, @Query("month") month : Int) : Call<FindDeadLineResponse>

    @POST("/projects/{id}/todo")
    fun createTodo(@Header("authorization") token : String, @Path("id") id : Int,@Body request : ContentRequest) : Call<TodoResponse>

    @PATCH("/projects/{project-id}/deadline/{todo-id}")
    fun modifyColor(@Header("authorization") token : String, @Path("project-id") projectId : Int, @Path("todo-id") todoId : Int,@Body request: ContentRequest) : Call<MessageResponse>

    @GET("/projects/{project-id}/team-member/{user-id}/todo?")
    fun findAllTodos(@Header("authorization") token : String, @Path("project-id") projectId: Int, @Path("user-id") userId: Int, @Query("page") page: Int) : Call<FindAllTodosResponse>

    @GET("/todo/{id}")
    fun findTodo(@Header("authorization") token : String, @Path("id") id : Int) : Call<TodoResponse>

    @PATCH("/todo/{id}")
    fun modifyTodo(@Header("authorization") token : String, @Path("id") id : Int,@Body request: ModifyTodoRequest) : Call<MessageResponse>

    @DELETE("/todo/{id}")
    fun deleteTodo(@Header("authorization") token : String, @Path("id") id : Int) : Call<Void>

    @PATCH("/todo/{id}")
    fun completeTodo(@Header("authorization") token : String, @Path("id") id : Int) : Call<MessageResponse>


}