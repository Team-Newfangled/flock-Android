package com.example.kkirikkiri.module.network.service

import com.example.kkirikkiri.module.network.dto.MessageResponse
import com.example.kkirikkiri.module.network.dto.account.NameResponse
import com.example.kkirikkiri.module.network.dto.account.response.FindUserPictureResponse
import com.example.kkirikkiri.module.network.dto.account.response.GoogleLoginResponse
import com.example.kkirikkiri.module.network.dto.account.response.ResultResponse
import com.example.kkirikkiri.module.network.dto.account.response.TokenResponse
import com.example.kkirikkiri.module.network.dto.todo.TodayTodoResponse
import retrofit2.Call
import retrofit2.http.*

interface AccountService {

    @POST("/auth/refresh")
    fun refreshToken(@Body refresh_token : String) : Call<TokenResponse>

    @GET("/auth/oauth?")
    fun loginWithGoogle(@Header("Accept") accept : String,
                        @Header("Accept-Encoding") encoding : String,
                        @Query("code") code : String) : Call<GoogleLoginResponse>

    @GET("/users/{user-id}")
    fun findUserPicture(@Header("authorization") token : String, @Path("user-id") userId: Int) : Call<FindUserPictureResponse>

    @GET("/users/{user-id}/team")
    fun findAllTeams(@Header("authorization") token : String, @Path("user-id") userId: Int) : Call<ResultResponse>

    @GET("/users/{user-id}/organization")
    fun findOrganization(@Header("authorization") token : String, @Path("user-id") userId: Int) : Call<NameResponse>

    @PATCH("users/{user-id}/organization")
    fun changeOrganization(@Header("authorization") token : String, @Path("user-id") userId: Int, @Body name : String) : Call<MessageResponse>

    @PATCH("/users/{user-id}/picture")
    fun changePicture(@Header("authorization") token : String, @Path("user-id") userId: Int, @Body content : String) : Call<MessageResponse>

    @PATCH("/users/{user-id}/name")
    fun changeName(@Header("authorization") token : String, @Path("user-id") userId: Int,@Body name : String) : Call<NameResponse>

    @GET("/users/{user-id}/todo?")
    fun getUserTodo(@Header("authorization") token : String, @Path("user-id") userId: Int, @Query("year") year:Int, @Query("month") month : Int, @Query("day") day : Int) : Call<TodayTodoResponse>
}