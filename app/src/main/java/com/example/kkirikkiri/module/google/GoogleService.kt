package com.example.kkirikkiri.module.google

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleService {
    @GET("https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email&client_id=1065774488617-bqqnvgv8fi2ghqgq17pk3tshpmdalur9.apps.googleusercontent.com&response_type=code&redirect_uri=http://localhost:3000/redirect&access_type=offline")
    fun getCode(@Query("state") state:String,
                @Query("redirect_uri") redirect_uri:String,
                @Query("client_id") client_id:String) : Call<GoogleResponse>
}