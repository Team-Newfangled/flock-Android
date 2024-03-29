package com.example.kkirikkiri.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kkirikkiri.module.network.RetrofitImpl
import com.example.kkirikkiri.module.network.dto.ApproveRequest
import com.example.kkirikkiri.module.network.dto.NameRequest
import com.example.kkirikkiri.module.info.UserInfo
import com.example.kkirikkiri.module.network.dto.TeamToProjectData
import com.example.kkirikkiri.module.network.dto.team.response.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamModel : ViewModel() {

    private val service = RetrofitImpl.teamService

    val teamMembers = MutableLiveData<List<FindMembersResponse.Results>>()
    val projects = MutableLiveData<TeamToProjectData>()
    val projectDefault = MutableLiveData<List<FindProjectResponse.Results>>()

    fun getTeamMember(team_id : Int, page : Int) {
        CoroutineScope(Dispatchers.IO).launch {
            service.findMembers(UserInfo.access_token, team_id,page).enqueue(object : Callback<FindMembersResponse>{
                override fun onResponse(
                    call: Call<FindMembersResponse>,
                    response: Response<FindMembersResponse>
                ) {
                    if(response.isSuccessful) teamMembers.value = response.body()!!.results
                    else Log.e("Fail", response.message() + " " + response.code())
                }

                override fun onFailure(call: Call<FindMembersResponse>, t: Throwable) {
                    Log.e("getTeamMember Fail",t.message.toString())
                }

            })

        }
    }

    fun getProject(id : Int, page : Int) {
        CoroutineScope(Dispatchers.IO).launch {
            service.findProjects(UserInfo.access_token, id, page).enqueue(object : Callback<FindProjectResponse>{
                override fun onResponse(
                    call: Call<FindProjectResponse>,
                    response: Response<FindProjectResponse>
                ) {
                    if (response.isSuccessful) {
                        projects.value = TeamToProjectData(id, response.body()!!.results)
                        Log.e("project data", id.toString() + " , " + response.body().toString())
                    }
                }

                override fun onFailure(call: Call<FindProjectResponse>, t: Throwable) {
                    Log.e("getProject Fail",t.message.toString())
                }
            })
        }
    }

    fun getProjectDefault(id : Int, page : Int) {
        CoroutineScope(Dispatchers.IO).launch {
            service.findProjects(UserInfo.access_token, id, page).enqueue(object : Callback<FindProjectResponse>{
                override fun onResponse(
                    call: Call<FindProjectResponse>,
                    response: Response<FindProjectResponse>
                ) {
                    if (response.isSuccessful) {
                        projectDefault.value = response.body()!!.results
                    }
                }

                override fun onFailure(call: Call<FindProjectResponse>, t: Throwable) {
                    Log.e("getProject Fail",t.message.toString())
                }

            })
        }
    }

    fun addProject(id : Int, name : NameRequest) {
        CoroutineScope(Dispatchers.IO).launch {
            service.addProject(UserInfo.access_token, id, name).enqueue(object : Callback<AddProjectResponse>{
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

    val teamId = MutableLiveData<CreateTeamResponse>()

    fun createTeam(name : String) {
        CoroutineScope(Dispatchers.IO).launch{
            service.createTeam(name, UserInfo.access_token).enqueue(object : Callback<CreateTeamResponse>{
                override fun onResponse(
                    call: Call<CreateTeamResponse>,
                    response: Response<CreateTeamResponse>
                ) {
                   if (response.isSuccessful) {
                       Log.e("팀", "팀 추가 성공")
                       teamId.value = response.body()
                   }
                   else Log.e("팀", "팀 추가 실패 " + response.raw().toString())
                }

                override fun onFailure(call: Call<CreateTeamResponse>, t: Throwable) {
                    Log.e("팀2", "팀 추가 실패, " + t.message.toString())
                }
            })
        }
    }

    fun deleteMember(teamId : Int, deleteUserId : Int) {
        CoroutineScope(Dispatchers.IO).launch {
            service.expulsionMember(UserInfo.access_token, teamId, deleteUserId).enqueue(object : Callback<Void>{
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Log.e("나가라", "나가라고 ${response.code()}")
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                }

            })
        }
    }

    fun approveMember(teamId : Int, memberId : Int) {
        CoroutineScope(Dispatchers.IO).launch {
            service.approveJoinMember(UserInfo.access_token, teamId, ApproveRequest(memberId)).enqueue(object : Callback<Void>{
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        Log.e("승인", "완료")
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {

                }

            })
        }
    }

    val waitingMember = MutableLiveData<List<FindMembersResponse.Results>>()
    fun getWaitingMember(teamId: Int, page: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            service.findWaitingMember(UserInfo.access_token, teamId, page).enqueue(object : Callback<FindMembersResponse>{
                override fun onResponse(
                    call: Call<FindMembersResponse>,
                    response: Response<FindMembersResponse>
                ) {
                    if (response.isSuccessful) waitingMember.value = response.body()?.results
                }

                override fun onFailure(call: Call<FindMembersResponse>, t: Throwable) {
                }

            })
        }
    }

    fun getRole(teamId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            service.findRoleById(UserInfo.access_token, teamId).enqueue(object : Callback<RoleResponse>{
                override fun onResponse(
                    call: Call<RoleResponse>,
                    response: Response<RoleResponse>
                ) {
                   if (response.isSuccessful) UserInfo.rule = response.body()?.role.toString()
                    Log.e("role", "${response.code()}, ${response.raw()}")
                }

                override fun onFailure(call: Call<RoleResponse>, t: Throwable) {

                }

            })
        }
    }

    fun deleteTeam(teamId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            service.deleteTeam(UserInfo.access_token, teamId).enqueue(object : Callback<Void>{
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) Log.e("팀 삭제", "성공")
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {

                }

            })
        }
    }

}