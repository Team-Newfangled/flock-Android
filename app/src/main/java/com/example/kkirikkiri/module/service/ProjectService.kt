package com.example.kkirikkiri.module.service

import com.example.kkirikkiri.module.dto.ContentRequest
import com.example.kkirikkiri.module.dto.MessageResponse
import com.example.kkirikkiri.module.dto.NameRequest
import com.example.kkirikkiri.module.dto.project.response.FindProjectResponse
import retrofit2.Call
import retrofit2.http.*

interface ProjectService {
    @GET("/projects/{id}")
    fun findProject(@Path("id") id : Int) : Call<FindProjectResponse>

    @DELETE("/projects/{id}")
    fun deleteProject(@Path("id") id : Int)

    @PATCH("/projects/{id}")
    fun modifyProjectName(@Path("id") id : Int, @Body request : NameRequest) : Call<MessageResponse>

    @PATCH("/projects/{id}/image")
    fun modifyProjectCoverImage(@Path("id") id : Int, @Body request : ContentRequest) : Call<MessageResponse>
}