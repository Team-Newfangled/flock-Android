package com.example.kkirikkiri.module.network.service

import com.example.kkirikkiri.module.network.dto.ApproveRequest
import com.example.kkirikkiri.module.network.dto.NameRequest
import com.example.kkirikkiri.module.network.dto.team.response.*
import retrofit2.Call
import retrofit2.http.*

interface TeamService {
    @POST("/teams")
    fun createTeam(@Body request : String,
                    @Header("authorization") token : String) : Call<CreateTeamResponse>

    @DELETE("/teams/{id}/expulsion/{user-id}")
    fun expulsionMember(@Header("authorization") token : String, @Path("id") id : Int, @Path("user-id") userId : Int) : Call<Void>

    @GET("/teams/{id}/members?")
    fun findMembers(@Header("authorization") token : String, @Path("id") id : Int, @Query("page") page : Int) : Call<FindMembersResponse>

    @GET("/teams/{id}/projects?")
    fun findProjects(@Header("authorization") token : String, @Path("id") id : Int, @Query("page") page: Int) : Call<FindProjectResponse>

    @POST("/teams/{id}/projects")
    fun addProject(@Header("authorization") token : String, @Path("id") id : Int, @Body request : NameRequest) : Call<AddProjectResponse>

    @PATCH("/teams/{id}/join")
    fun approveJoinMember(@Header("authorization") token : String, @Path("id") id : Int, @Body request : ApproveRequest) : Call<Void>

    @GET("/teams/{id}/waiting")
    fun findWaitingMember(@Header("authorization") token : String, @Path("id") id : Int, @Query("page") page : Int) : Call<FindMembersResponse>

    @GET("/teams/{id}")
    fun findRoleById(@Header("authorization") token : String, @Path("id") id : Int) : Call<RoleResponse>

    @DELETE("/teams/{id}")
    fun deleteTeam(@Header("authorization") token : String, @Path("id") id: Int) : Call<Void>

}