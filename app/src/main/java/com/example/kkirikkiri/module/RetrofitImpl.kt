package com.example.kkirikkiri.module

import com.example.kkirikkiri.module.service.AccountService
import com.example.kkirikkiri.module.service.BoardService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitImpl {
    private const val BASEURL = "http://10.80.161.145:8080"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASEURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val accountService: AccountService = retrofit.create(AccountService::class.java)
    val boardService: BoardService = retrofit.create(BoardService::class.java)

}