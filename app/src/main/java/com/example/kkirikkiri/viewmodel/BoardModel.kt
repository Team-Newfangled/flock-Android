package com.example.kkirikkiri.viewmodel

import androidx.lifecycle.ViewModel
import com.example.kkirikkiri.module.RetrofitImpl
import com.example.kkirikkiri.module.dto.ContentRequest
import com.example.kkirikkiri.module.dto.MessageResponse
import com.example.kkirikkiri.module.dto.board.BoardResponse
import com.example.kkirikkiri.module.dto.board.CommentResponse
import com.example.kkirikkiri.module.dto.board.response.FindBoardPageResponse
import com.example.kkirikkiri.module.dto.board.response.FindCommentsResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BoardModel : ViewModel() {

    private val service = RetrofitImpl.boardService

    fun findComment(boardId : Int, page : Int) {
        CoroutineScope(Dispatchers.IO).launch {
            service.findComment(boardId, page).enqueue(object : Callback<FindCommentsResponse>{
                override fun onResponse(
                    call: Call<FindCommentsResponse>,
                    response: Response<FindCommentsResponse>
                ) {

                }

                override fun onFailure(call: Call<FindCommentsResponse>, t: Throwable) {

                }

            })
        }
    }


    fun findBoard(boardId : Int) {
        CoroutineScope(Dispatchers.IO).launch {
            service.findBoard(boardId).enqueue(object : Callback<BoardResponse>{
                override fun onResponse(
                    call: Call<BoardResponse>,
                    response: Response<BoardResponse>
                ) {

                }

                override fun onFailure(call: Call<BoardResponse>, t: Throwable) {

                }

            })
        }
    }

    fun writeComment(boardId: Int, content : String) {
        CoroutineScope(Dispatchers.IO).launch {
            service.writeComment(boardId, content).enqueue(object : Callback<CommentResponse>{
                override fun onResponse(
                    call: Call<CommentResponse>,
                    response: Response<CommentResponse>
                ) {

                }

                override fun onFailure(call: Call<CommentResponse>, t: Throwable) {

                }

            })
        }
    }


    fun deleteBoard(boardId: Int) {
        CoroutineScope(Dispatchers.IO).launch { service.deleteBoard(boardId) }
    }


    fun modifyBoard(boardId: Int, request : ContentRequest) {
        CoroutineScope(Dispatchers.IO).launch {
            service.modifyBoard(boardId, request).enqueue(object : Callback<MessageResponse>{
                override fun onResponse(
                    call: Call<MessageResponse>,
                    response: Response<MessageResponse>
                ) {

                }

                override fun onFailure(call: Call<MessageResponse>, t: Throwable) {

                }

            })
        }
    }

    fun saveFile(boardId: Int, request : ContentRequest) {
        CoroutineScope(Dispatchers.IO).launch {
            service.saveFile(boardId, request).enqueue(object : Callback<MessageResponse>{
                override fun onResponse(
                    call: Call<MessageResponse>,
                    response: Response<MessageResponse>
                ) {

                }

                override fun onFailure(call: Call<MessageResponse>, t: Throwable) {

                }

            })
        }
    }

    fun deleteFile(boardId: Int, fileId : Int) {
        CoroutineScope(Dispatchers.IO).launch { service.deleteFile(boardId, fileId) }
    }

    fun deleteComment(commentsId : Int) {
        CoroutineScope(Dispatchers.IO).launch { service.deleteComment(commentsId) }
    }

    fun findBoardPage(projectsId : Int, page : Int) {
        CoroutineScope(Dispatchers.IO).launch {
            service.findBoardPage(projectsId, page).enqueue(object : Callback<FindBoardPageResponse>{
                override fun onResponse(
                    call: Call<FindBoardPageResponse>,
                    response: Response<FindBoardPageResponse>
                ) {

                }

                override fun onFailure(call: Call<FindBoardPageResponse>, t: Throwable) {

                }

            })
        }
    }

    fun saveBoard(projectsId : Int, request : ContentRequest) {
        CoroutineScope(Dispatchers.IO).launch {
            service.saveBoard(projectsId, request).enqueue(object : Callback<BoardResponse>{
                override fun onResponse(
                    call: Call<BoardResponse>,
                    response: Response<BoardResponse>
                ) {

                }

                override fun onFailure(call: Call<BoardResponse>, t: Throwable) {

                }

            })
        }
    }


}