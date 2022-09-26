package com.example.kkirikkiri.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kkirikkiri.module.RetrofitImpl
import com.example.kkirikkiri.module.dto.account.response.GoogleLoginResponse
import com.example.kkirikkiri.module.dto.account.response.ResultResponse
import com.example.kkirikkiri.module.google.GoogleResponse
import com.example.kkirikkiri.module.google.GoogleRetrofitImpl
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginModel : ViewModel(){
    private val service = RetrofitImpl.accountService
    private val google = GoogleRetrofitImpl.service

    var userid = MutableLiveData<GoogleLoginResponse?>()

     // code의 %2F는 /로 치완해줘야함
    fun signInResult() {
//        val account = task.getResult(ApiException::class.java)
        val code = "4/0ARtbsJoSc_KwfJTa13zq50wW0qJOjNyn9GFxn7FPo0k9eYHlMenr-di2l9EwjfqX89FMDA"
            CoroutineScope(Dispatchers.IO).launch {

            service.loginWithGoogle("*/*", "gzip, deflate, br", code.toString()).enqueue(object : Callback<GoogleLoginResponse> {
                override fun onResponse(
                    call: Call<GoogleLoginResponse>,
                    response: Response<GoogleLoginResponse>
                ) {
                    if (response.isSuccessful) {
                        Log.e("성공", "성공함")
                        userid.value = response.body()
                    }
                    else Log.e("실패", response.errorBody().toString() + code.toString() + "  ," + response.message() + ", " + response.code())
                }

                override fun onFailure(call: Call<GoogleLoginResponse>, t: Throwable) {
                    Log.e("실패", "연결 실패" + t.printStackTrace())
                }

            })
            }

    }


    fun googleGetToken(state: String, redirectUri:String, clintId:String) {
        google.getCode(state, redirectUri, clintId).enqueue(object : Callback<GoogleResponse>{
            override fun onResponse(
                call: Call<GoogleResponse>,
                response: Response<GoogleResponse>
            ) {
                if (response.isSuccessful) Log.e("token",response.body()!!.access_token.path.toString())
                else Log.e("실패", "onResponse는 성공")
            }

            override fun onFailure(call: Call<GoogleResponse>, t: Throwable) {
                Log.e("실패", "그냥 연결 실패함 ㅇㅇ" + t.message)
            }

        })
    }


    val teams = MutableLiveData<ResultResponse>()

    fun getAllTeam(userId : Int) {
        service.findAllTeams(userId).enqueue(object : Callback<ResultResponse>{
            override fun onResponse(
                call: Call<ResultResponse>,
                response: Response<ResultResponse>
            ) {
                if (response.isSuccessful) {
                    teams.value = response.body()
                }
            }

            override fun onFailure(call: Call<ResultResponse>, t: Throwable) {

            }

        })
    }



}