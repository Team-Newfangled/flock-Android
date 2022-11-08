package com.example.kkirikkiri.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kkirikkiri.module.network.RetrofitImpl
import com.example.kkirikkiri.module.network.dto.account.response.FindUserPictureResponse
import com.example.kkirikkiri.module.network.dto.account.response.GoogleLoginResponse
import com.example.kkirikkiri.module.network.dto.account.response.ResultResponse
import com.example.kkirikkiri.module.info.UserInfo
import com.example.kkirikkiri.module.network.dto.todo.TodayTodoResponse
import com.example.kkirikkiri.view.activity.LoadingApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginModel : ViewModel(){
    private val service = RetrofitImpl.accountService
    var userid = MutableLiveData<GoogleLoginResponse?>()
    var loading : LoadingApplication? = null

    fun signInResult(code : String) {
//        val account = task.getResult(ApiException::class.java)
         CoroutineScope(Dispatchers.IO).launch {
            service.loginWithGoogle("*/*", "gzip, deflate, br", code).enqueue(object : Callback<GoogleLoginResponse> {
                override fun onResponse(
                    call: Call<GoogleLoginResponse>,
                    response: Response<GoogleLoginResponse>
                ) {
                    if (response.isSuccessful) {
                        Log.e("성공", "성공함")
                        UserInfo.access_token = "Bearer " + response.body()!!.access_token
                        UserInfo.refresh_token = "Bearer " + response.body()!!.refresh_token
                        UserInfo.userId = response.body()!!.id
                        findName("Bearer " + response.body()!!.access_token, response.body()!!.id)

                        Log.e("token", response.body()!!.access_token)
                        Log.e("token", UserInfo.access_token)
                        loading?.progressOff()
                        userid.value = response.body()
                    }
                    else Log.e("실패", response.errorBody().toString() + code+ "  ," + response.message() + ", " + response.code())
                }

                override fun onFailure(call: Call<GoogleLoginResponse>, t: Throwable) {
                    Log.e("실패", "연결 실패" + t.printStackTrace())
                }

            })
            }

    }



    val teams = MutableLiveData<ResultResponse>()

    fun getAllTeam(userId : Int) {
        service.findAllTeams(UserInfo.access_token,userId).enqueue(object : Callback<ResultResponse>{
            override fun onResponse(
                call: Call<ResultResponse>,
                response: Response<ResultResponse>
            ) {
                if (response.isSuccessful) {
                    teams.value = response.body()
                } else {
                    Log.e("팀 실패", response.raw().toString())
                }
            }

            override fun onFailure(call: Call<ResultResponse>, t: Throwable) {
                Log.e("팀 가져오기 실패", "접속도 안됨")
            }

        })
    }

    private fun findName(token : String, userId: Int) {
        service.findUserPicture(token , userId).enqueue(object : Callback<FindUserPictureResponse>{
            override fun onResponse(
                call: Call<FindUserPictureResponse>,
                response: Response<FindUserPictureResponse>
            ) {
                if (response.isSuccessful) UserInfo.UserName = response.body()!!.nickname
                else Log.e("실패", response.raw().toString())
            }

            override fun onFailure(call: Call<FindUserPictureResponse>, t: Throwable) {
                Log.e("실패", t.printStackTrace().toString())
            }

        })
    }

    val todayTodos = MutableLiveData<List<TodayTodoResponse.TodoContentResponse>>()
    fun getTodayTodo(year : Int, month : Int, day : Int) {
        CoroutineScope(Dispatchers.IO).launch {
            service.getUserTodo(UserInfo.access_token, UserInfo.userId!!, year, month, day).enqueue(object : Callback<TodayTodoResponse>{
                override fun onResponse(
                    call: Call<TodayTodoResponse>,
                    response: Response<TodayTodoResponse>
                ) {
                    if (response.isSuccessful) todayTodos.value = response.body()!!.results
                    else Log.e("today Todos", response.raw().toString())
                }

                override fun onFailure(call: Call<TodayTodoResponse>, t: Throwable) {

                }

            })
        }
    }
}