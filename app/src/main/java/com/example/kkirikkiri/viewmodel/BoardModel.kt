package com.example.kkirikkiri.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kkirikkiri.module.network.RetrofitImpl
import com.example.kkirikkiri.module.network.dto.ContentRequest
import com.example.kkirikkiri.module.network.dto.MessageResponse
import com.example.kkirikkiri.module.network.dto.board.BoardResponse
import com.example.kkirikkiri.module.network.dto.board.CommentResponse
import com.example.kkirikkiri.module.network.dto.board.response.FindBoardPageResponse
import com.example.kkirikkiri.module.network.dto.board.response.FindCommentsResponse
import com.example.kkirikkiri.module.info.UserInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BoardModel : ViewModel() {

    private val service = RetrofitImpl.boardService


    val findCommentData = MutableLiveData<List<CommentResponse>>()
    fun findComment(boardId : Int, page : Int) {
        CoroutineScope(Dispatchers.IO).launch {
            service.findComment(UserInfo.access_token, boardId, page).enqueue(object : Callback<FindCommentsResponse>{
                override fun onResponse(
                    call: Call<FindCommentsResponse>,
                    response: Response<FindCommentsResponse>
                ) {
                    if (response.isSuccessful) {
                        Log.e("성공", response.body().toString())
                        findCommentData.value = response.body()?.results
                    }
                    else Log.e("실패", "접속은 했음, ${response.raw()}")
                }

                override fun onFailure(call: Call<FindCommentsResponse>, t: Throwable) {

                }

            })
        }
    }

    fun findBoard(boardId : Int) {
        CoroutineScope(Dispatchers.IO).launch {
            service.findBoard(UserInfo.access_token, boardId).enqueue(object : Callback<BoardResponse>{
                override fun onResponse(
                    call: Call<BoardResponse>,
                    response: Response<BoardResponse>
                ) {
                    if (response.isSuccessful) Log.e("성공", response.body().toString())
                    else Log.e("실패", "접속은 했음")
                }

                override fun onFailure(call: Call<BoardResponse>, t: Throwable) {

                }

            })
        }
    }

    fun writeComment(boardId: Int, content : String) {
        CoroutineScope(Dispatchers.IO).launch {
            service.writeComment(UserInfo.access_token, boardId, content).enqueue(object : Callback<CommentResponse>{
                override fun onResponse(
                    call: Call<CommentResponse>,
                    response: Response<CommentResponse>
                ) {
                    if (response.isSuccessful) {
                        Log.e("성공", response.body().toString())
                    }
                    else Log.e("실패", "접속은 했음, ${response.raw()}")
                }

                override fun onFailure(call: Call<CommentResponse>, t: Throwable) {

                }

            })
        }
    }


    fun deleteBoard(boardId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            service.deleteBoard(UserInfo.access_token, boardId).enqueue(object : Callback<Void>{
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Log.e("code", "${response.code()}")
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {

                }
            })
        }
    }


    fun modifyBoard(boardId: Int, request : ContentRequest) {
        CoroutineScope(Dispatchers.IO).launch {
            service.modifyBoard(UserInfo.access_token, boardId, request).enqueue(object : Callback<MessageResponse>{
                override fun onResponse(
                    call: Call<MessageResponse>,
                    response: Response<MessageResponse>
                ) {
                    if (response.isSuccessful) Log.e("성공", response.body().toString())
                    else Log.e("실패", "접속은 했음")
                }

                override fun onFailure(call: Call<MessageResponse>, t: Throwable) {

                }

            })
        }
    }

    fun saveFile(boardId: Int, request : ContentRequest) {
        CoroutineScope(Dispatchers.IO).launch {
            service.saveFile(UserInfo.access_token, boardId, request).enqueue(object : Callback<MessageResponse>{
                override fun onResponse(
                    call: Call<MessageResponse>,
                    response: Response<MessageResponse>
                ) {
                    if (response.isSuccessful) Log.e("성공", response.body().toString())
                    else Log.e("실패", "접속은 했음")
                }

                override fun onFailure(call: Call<MessageResponse>, t: Throwable) {

                }

            })
        }
    }

    fun deleteFile(boardId: Int, fileId : Int) {
        CoroutineScope(Dispatchers.IO).launch { service.deleteFile(UserInfo.access_token, boardId, fileId) }
    }

    fun deleteComment(commentsId : Int) {
        CoroutineScope(Dispatchers.IO).launch { service.deleteComment(UserInfo.access_token, commentsId) }
    }


    val boardList = MutableLiveData<List<FindBoardPageResponse.Results>>()

    fun findBoardPage(projectsId : Int, page : Int) {
        CoroutineScope(Dispatchers.IO).launch {
            service.findBoardPage(UserInfo.access_token, projectsId, page).enqueue(object : Callback<FindBoardPageResponse>{
                override fun onResponse(
                    call: Call<FindBoardPageResponse>,
                    response: Response<FindBoardPageResponse>
                ) {
                    if (response.isSuccessful) {
                        Log.e("성공", response.body().toString())
                        boardList.value = response.body()!!.results
                    }
                    else Log.e("실패", "접속은 했음")
                }

                override fun onFailure(call: Call<FindBoardPageResponse>, t: Throwable) {

                }

            })
        }
    }

    fun saveBoard(projectsId : Int, request : ContentRequest) {
        CoroutineScope(Dispatchers.IO).launch {
            service.saveBoard(UserInfo.access_token, projectsId, request).enqueue(object : Callback<BoardResponse>{
                override fun onResponse(
                    call: Call<BoardResponse>,
                    response: Response<BoardResponse>
                ) {
                    if (response.isSuccessful) Log.e("성공", response.body().toString())
                    else Log.e("실패", "접속은 했음")
                }

                override fun onFailure(call: Call<BoardResponse>, t: Throwable) {

                }

            })
        }
    }


}