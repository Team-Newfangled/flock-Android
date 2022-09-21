package com.example.kkirikkiri.module.service

import com.example.kkirikkiri.module.dto.NameRequest
import com.example.kkirikkiri.module.dto.team.response.AddProjectResponse
import com.example.kkirikkiri.module.dto.team.response.CreateTeamResponse
import com.example.kkirikkiri.module.dto.team.response.FindMembersResponse
import com.example.kkirikkiri.module.dto.team.response.FindProjectResponse
import retrofit2.Call
import retrofit2.http.*

interface TeamService {
    @POST("/teams")
    fun createTeam(@Body request : NameRequest) : Call<CreateTeamResponse>

    @DELETE("/teams/{id}/expulsion/{user-id}")
    fun expulsionMember(@Path("id") id : Int, @Path("user-id") userId : Int)

    @GET("/teams/{id}/members?")
    fun findMembers(@Path("id") id : Int, @Query("page") page : Int) : Call<FindMembersResponse>

    @GET("/teams/{id}/projects?")
    fun findProjects(@Path("id") id : Int, @Query("page") page: Int) : Call<FindProjectResponse>

    @POST("/teams/{id}/projects")
    fun addProject(@Path("id") id : Int, @Body request : NameRequest) : Call<AddProjectResponse>


}