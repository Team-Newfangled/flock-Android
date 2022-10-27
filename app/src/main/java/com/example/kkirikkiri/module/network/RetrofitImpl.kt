package com.example.kkirikkiri.module.network

import com.example.kkirikkiri.module.network.service.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitImpl {
    private const val BASEURL = "http://141.164.59.254:8080"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASEURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val accountService: AccountService = retrofit.create(AccountService::class.java)
    val boardService: BoardService = retrofit.create(BoardService::class.java)
    val projectService: ProjectService = retrofit.create(ProjectService::class.java)
    val teamService: TeamService = retrofit.create(TeamService::class.java)
    val teamJoinService: TeamJoinService = retrofit.create(TeamJoinService::class.java)
    val todoService: TodoService = retrofit.create(TodoService::class.java)
}