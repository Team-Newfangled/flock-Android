package com.example.kkirikkiri.module.network.service

import com.example.kkirikkiri.module.network.dto.ContentRequest
import com.example.kkirikkiri.module.network.dto.MessageResponse
import com.example.kkirikkiri.module.network.dto.NameRequest
import com.example.kkirikkiri.module.network.dto.project.response.FindProjectResponse
import retrofit2.Call
import retrofit2.http.*

interface ProjectService {
    @GET("/projects/{id}")
    fun findProject(@Header("authorization") token : String, @Path("id") id : Int) : Call<FindProjectResponse>

    @DELETE("/projects/{id}")
    fun deleteProject(@Header("authorization") token : String, @Path("id") id : Int) : Call<Void>

    @PATCH("/projects/{id}")
    fun modifyProjectName(@Header("authorization") token : String, @Path("id") id : Int, @Body request : NameRequest) : Call<MessageResponse>

    @PATCH("/projects/{id}/image")
    fun modifyProjectCoverImage(@Header("authorization") token : String, @Path("id") id : Int, @Body request : ContentRequest) : Call<MessageResponse>
}