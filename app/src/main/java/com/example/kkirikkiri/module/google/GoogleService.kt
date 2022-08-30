package com.example.kkirikkiri.module.google

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleService {
    @GET("auth?scope=https%3A//www.googleapis.com/auth/drive.metadata.readonly&access_type=offline&include_granted_scopes=true&response_type=code&?&?&?")
    fun getCode(@Query("state") state:String,
                @Query("redirect_uri") redirect_uri:String,
                @Query("client_id") client_id:String) : Call<GoogleResponse>
}