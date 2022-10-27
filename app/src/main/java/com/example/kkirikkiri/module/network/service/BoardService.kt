package com.example.kkirikkiri.module.network.service

import com.example.kkirikkiri.module.network.dto.MessageResponse
import com.example.kkirikkiri.module.network.dto.board.CommentResponse
import com.example.kkirikkiri.module.network.dto.ContentRequest
import com.example.kkirikkiri.module.network.dto.board.response.FindBoardPageResponse
import com.example.kkirikkiri.module.network.dto.board.BoardResponse
import com.example.kkirikkiri.module.network.dto.board.response.FindCommentsResponse
import retrofit2.Call
import retrofit2.http.*

interface BoardService {

    @GET("/boards/{id}/comments?")
    fun findComment(@Header("authorization") token : String, @Path("id") id:Int, @Query("page") page : Int) : Call<FindCommentsResponse>

    @POST("/boards/{id}/comments")
    fun writeComment(@Header("authorization") token : String, @Path("id") id:Int,@Body content : String) : Call<CommentResponse>

    @GET("/boards/{id}")
    fun findBoard(@Header("authorization") token : String, @Path("id") id:Int) : Call<BoardResponse>

    @DELETE("/boards/{id}")
    fun deleteBoard(@Header("authorization") token : String, @Path("id") id: Int ) : Call<Void>

    @PATCH("/boards/{id}")
    fun modifyBoard(@Header("authorization") token : String, @Path("id") id: Int,@Body request: ContentRequest) : Call<MessageResponse>

    @POST("/boards/{id}/files")
    fun saveFile(@Header("authorization") token : String, @Path("id") id : Int, @Body request: ContentRequest) : Call<MessageResponse>

    @DELETE("/boards/{id}/files?")
    fun deleteFile(@Header("authorization") token : String, @Path("id") id: Int, @Query("file_id") fileId : Int) : Call<Void>

    @DELETE("/comments/{id}")
    fun deleteComment(@Header("authorization") token : String, @Path("id") id: Int) : Call<Void>

    @GET("projects/{id}/boards?")
    fun findBoardPage(@Header("authorization") token : String, @Path("id") id : Int, @Query("page") page : Int) : Call<FindBoardPageResponse>

    @POST("/projects/{id}/boards")
    fun saveBoard(@Header("authorization") token : String, @Path("id") id : Int, @Body request : ContentRequest) : Call<BoardResponse>
}