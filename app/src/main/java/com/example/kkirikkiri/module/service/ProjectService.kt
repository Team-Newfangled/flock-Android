package com.example.kkirikkiri.module.service

import com.example.kkirikkiri.module.dto.ContentRequest
import com.example.kkirikkiri.module.dto.MessageResponse
import com.example.kkirikkiri.module.dto.project.request.NameRequest
import com.example.kkirikkiri.module.dto.project.response.FindProjectResponse
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface ProjectService {
    @GET("/projects/{id}")
    fun findProject(@Path("id") id : Int) : Call<FindProjectResponse>

    @DELETE("/projects/{id}")
    fun deleteProject(@Path("id") id : Int)

    @PATCH("/projects/{id}")
    fun modifyProjectName(@Path("id") id : Int, request : NameRequest) : Call<MessageResponse>

    @PATCH("/projects/{id}/image")
    fun modifyProjectCoverImage(@Path("id") id : Int, request : ContentRequest) : Call<MessageResponse>
}