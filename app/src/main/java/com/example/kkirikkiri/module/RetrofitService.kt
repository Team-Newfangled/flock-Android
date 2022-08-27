package com.example.kkirikkiri.module

import com.example.kkirikkiri.module.dto.response.GoogleLoginResponse
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {

    @GET("/auth/oauth?")
    fun loginWithGoogle(@Query("code") code : String) : Call<GoogleLoginResponse>
}