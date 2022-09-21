package com.example.kkirikkiri.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kkirikkiri.module.RetrofitImpl
import com.example.kkirikkiri.module.dto.NameRequest
import com.example.kkirikkiri.module.dto.team.response.AddProjectResponse
import com.example.kkirikkiri.module.dto.team.response.CreateTeamResponse
import com.example.kkirikkiri.module.dto.team.response.FindMembersResponse
import com.example.kkirikkiri.module.dto.team.response.FindProjectResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Path

class TeamModel : ViewModel() {

    private val service = RetrofitImpl.teamService

    val teamMembers = MutableLiveData<List<FindMembersResponse.Results>>()
    val projects = MutableLiveData<List<FindProjectResponse.Results>>()

    fun getTeamMember(id : Int, page : Int) {
        CoroutineScope(Dispatchers.IO).launch {
            service.findMembers(id,page).enqueue(object : Callback<FindMembersResponse>{
                override fun onResponse(
                    call: Call<FindMembersResponse>,
                    response: Response<FindMembersResponse>
                ) {
                    if(response.isSuccessful) teamMembers.value = response.body()!!.results
                }

                override fun onFailure(call: Call<FindMembersResponse>, t: Throwable) {
                    Log.e("getTeamMember Fail",t.message.toString())
                }

            })

        }
    }

    fun getProject(id : Int, page : Int) {
        CoroutineScope(Dispatchers.IO).launch {
            service.findProjects(id, page).enqueue(object : Callback<FindProjectResponse>{
                override fun onResponse(
                    call: Call<FindProjectResponse>,
                    response: Response<FindProjectResponse>
                ) {
                    if (response.isSuccessful) projects.value = response.body()!!.results
                }

                override fun onFailure(call: Call<FindProjectResponse>, t: Throwable) {
                    Log.e("getProject Fail",t.message.toString())
                }

            })
        }
    }

    fun addProject(id : Int, name : NameRequest ) {
        CoroutineScope(Dispatchers.IO).launch {
            service.addProject(id, name).enqueue(object : Callback<AddProjectResponse>{
                override fun onResponse(
                    call: Call<AddProjectResponse>,
                    response: Response<AddProjectResponse>
                ) {
                    if (response.isSuccessful) Log.e("프로젝트", "프로젝트 추가 성공")
                    else Log.e("프로젝트", "프로젝트 추가 실패")
                }

                override fun onFailure(call: Call<AddProjectResponse>, t: Throwable) {
                    Log.e("프로젝트", "프로젝트 추가 실패, " + t.message.toString())
                }

            })
        }
    }

    fun createTeam(name : NameRequest) {
        CoroutineScope(Dispatchers.IO).launch{
            service.createTeam(name).enqueue(object : Callback<CreateTeamResponse>{
                override fun onResponse(
                    call: Call<CreateTeamResponse>,
                    response: Response<CreateTeamResponse>
                ) {
                   if (response.isSuccessful) Log.e("팀", "팀 추가 성공")
                   else Log.e("팀", "팀 추가 실패")
                }

                override fun onFailure(call: Call<CreateTeamResponse>, t: Throwable) {
                    Log.e("팀", "팀 추가 실패, " + t.message.toString())
                }
            })
        }
    }

    fun deleteMember(id : Int, deleteUserId : Int) {
        CoroutineScope(Dispatchers.IO).launch {
            service.expulsionMember(id, deleteUserId)
        }
    }


}