package com.example.kkirikkiri.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.kkirikkiri.module.network.RetrofitImpl
import com.example.kkirikkiri.module.network.dto.ContentRequest
import com.example.kkirikkiri.module.network.dto.MessageResponse
import com.example.kkirikkiri.module.network.dto.NameRequest
import com.example.kkirikkiri.module.network.dto.project.response.FindProjectResponse
import com.example.kkirikkiri.module.info.UserInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProjectModel : ViewModel() {

    private val service = RetrofitImpl.projectService

    fun findProject(projectId : Int) {
        CoroutineScope(Dispatchers.IO).launch {
            service.findProject(UserInfo.access_token, projectId).enqueue(object : Callback<FindProjectResponse>{
                override fun onResponse(
                    call: Call<FindProjectResponse>,
                    response: Response<FindProjectResponse>
                ) {
                    if (response.isSuccessful) Log.e("성공", response.body().toString())
                    else Log.e("실패", "접속은 했음")
                }

                override fun onFailure(call: Call<FindProjectResponse>, t: Throwable) {

                }

            })
        }
    }

    fun deleteProject(projectId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            service.deleteProject(UserInfo.access_token, projectId).enqueue(object : Callback<Void>{
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Log.e("프로젝트", "삭-제 ${response.code()}")
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.e("프로젝트", t.printStackTrace().toString())
                }

            })
        }
    }

    fun modifyProjectName(projectId: Int, request : NameRequest) {
        CoroutineScope(Dispatchers.IO).launch {
            service.modifyProjectName(UserInfo.access_token, projectId, request).enqueue(object : Callback<MessageResponse>{
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

    fun modifyProjectCoverImage(projectId: Int, request: ContentRequest) {
        service.modifyProjectCoverImage(UserInfo.access_token, projectId, request).enqueue(object : Callback<MessageResponse> {
            override fun onResponse(
                call: Call<MessageResponse>,
                response: Response<MessageResponse>
            ) {
                if (response.isSuccessful) Log.e("성공", response.body().toString())
                else Log.e("실패", response.raw().toString())
            }

            override fun onFailure(call: Call<MessageResponse>, t: Throwable) {

            }

        })
    }

}