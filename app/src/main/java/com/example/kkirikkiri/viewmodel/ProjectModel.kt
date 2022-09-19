package com.example.kkirikkiri.viewmodel

import androidx.lifecycle.ViewModel
import com.example.kkirikkiri.module.RetrofitImpl
import com.example.kkirikkiri.module.dto.ContentRequest
import com.example.kkirikkiri.module.dto.MessageResponse
import com.example.kkirikkiri.module.dto.NameRequest
import com.example.kkirikkiri.module.dto.project.response.FindProjectResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProjectModel : ViewModel() {

    private val service = RetrofitImpl.projectService

    fun findProject(projectId : Int) {
        CoroutineScope(Dispatchers.IO).launch {
            service.findProject(projectId).enqueue(object : Callback<FindProjectResponse>{
                override fun onResponse(
                    call: Call<FindProjectResponse>,
                    response: Response<FindProjectResponse>
                ) {

                }

                override fun onFailure(call: Call<FindProjectResponse>, t: Throwable) {

                }

            })
        }
    }

    fun deleteProject(projectId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            service.deleteProject(projectId)
        }
    }

    fun modifyProjectName(projectId: Int, request : NameRequest) {
        CoroutineScope(Dispatchers.IO).launch {
            service.modifyProjectName(projectId, request).enqueue(object : Callback<MessageResponse>{
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

    fun modifyProjectCoverImage(projectId: Int, request: ContentRequest) {
        service.modifyProjectCoverImage(projectId, request).enqueue(object : Callback<MessageResponse> {
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