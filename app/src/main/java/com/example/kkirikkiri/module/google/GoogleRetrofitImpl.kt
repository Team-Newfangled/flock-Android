package com.example.kkirikkiri.module.google

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GoogleRetrofitImpl {
    private const val BASEURL = "https://accounts.google.com/o/oauth2/v2/"

    val gson : Gson = GsonBuilder()
        .setLenient()
        .create()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASEURL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val service: GoogleService = retrofit.create(GoogleService::class.java)
}