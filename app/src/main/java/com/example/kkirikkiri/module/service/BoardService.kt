package com.example.kkirikkiri.module.service

import com.example.kkirikkiri.module.dto.MessageResponse
import com.example.kkirikkiri.module.dto.board.CommentResponse
import com.example.kkirikkiri.module.dto.board.request.ContentRequest
import com.example.kkirikkiri.module.dto.board.response.FindBoardPageResponse
import com.example.kkirikkiri.module.dto.board.BoardResponse
import com.example.kkirikkiri.module.dto.board.response.FindCommentsResponse
import retrofit2.Call
import retrofit2.http.*

interface BoardService {

    @GET("/board/{id}/comments?")
    fun findComment(@Path("id") id:Int, @Query("page") page : Int) : Call<FindCommentsResponse>

    @POST("/board/{id}/comments")
    fun writeComment(@Path("id") id:Int, content : String) : Call<CommentResponse>

    @GET("/boards/{id}")
    fun findBoard(@Path("id") id:Int) : Call<BoardResponse>

    @DELETE("/boards/{id}")
    fun deleteBoard(@Path("id") id: Int)

    @PATCH("/boards/{id}")
    fun modifyBoard(@Path("id") id: Int, request: ContentRequest) : Call<MessageResponse>

    @POST("/boards/{id}/files")
    fun saveFile(@Path("id") id : Int, request: ContentRequest) : Call<MessageResponse>

    @DELETE("/boards/{id}/files?")
    fun deleteFile(@Path("id") id: Int, @Query("file_id") fileId : Int)

    @DELETE("/comments/{id}")
    fun deleteComment(@Path("id") id: Int)

    @GET("projects/{id}/boards?")
    fun findBoardPage(@Path("id") id : Int, @Query("page") page : Int) : Call<FindBoardPageResponse>

    @POST("/projects/{id}/boards")
    fun saveBoard(@Path("id") id : Int, request : ContentRequest) : Call<BoardResponse>
}