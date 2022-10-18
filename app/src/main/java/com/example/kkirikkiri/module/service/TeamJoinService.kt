package com.example.kkirikkiri.module.service

import com.example.kkirikkiri.module.dto.Default
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TeamJoinService {
    @GET("join-mail")
    fun joinMail()

    @GET("/join-member")
    fun joinComplete()

    @GET("/teams/{id}/join")
    fun join(@Path("id") id : Int)

    @POST("/teams/{id}/join?")
    fun joinMember(@Path("id") id : Int, @Body memberId : Int)

    @POST("/teams/{id}/join-mail")
    fun sendJoinMail(@Path("id") id : Int, @Body email : String) : Call<Default>
}