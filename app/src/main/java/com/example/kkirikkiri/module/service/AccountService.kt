package com.example.kkirikkiri.module.service

import com.example.kkirikkiri.module.dto.MessageResponse
import com.example.kkirikkiri.module.dto.account.NameResponse
import com.example.kkirikkiri.module.dto.account.response.FindUserPictureResponse
import com.example.kkirikkiri.module.dto.account.response.GoogleLoginResponse
import com.example.kkirikkiri.module.dto.account.response.ResultResponse
import com.example.kkirikkiri.module.dto.account.response.TokenResponse
import retrofit2.Call
import retrofit2.http.*

interface AccountService {

    @POST("/auth/refresh")
    fun refreshToken(@Body refresh_token : String) : Call<TokenResponse>

    @GET("/auth/oauth?")
    fun loginWithGoogle(@Query("code") code : String) : Call<GoogleLoginResponse>

    @GET("/users/{user-id}")
    fun findUserPicture(@Path("user-id") userId: Int) : Call<FindUserPictureResponse>

    @GET("/users/{user-id}/team")
    fun findAllTeams(@Path("user-id") userId: Int) : Call<ResultResponse>

    @GET("/users/{user-id}/organization")
    fun findOrganization(@Path("user-id") userId: Int) : Call<NameResponse>

    @PATCH("users/{user-id}/organization")
    fun changeOrganization(@Path("user-id") userId: Int, @Body name : String) : Call<MessageResponse>

    @PATCH("/users/{user-id}/picture")
    fun changePicture(@Path("user-id") userId: Int, @Body content : String) : Call<MessageResponse>

    @PATCH("/users/{user-id}/name")
    fun changeName(@Path("user-id") userId: Int,@Body name : String) : Call<NameResponse>

}