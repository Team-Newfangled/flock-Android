package com.example.kkirikkiri.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kkirikkiri.module.network.RetrofitImpl
import com.example.kkirikkiri.module.network.dto.account.response.FindUserPictureResponse
import com.example.kkirikkiri.module.network.dto.account.response.GoogleLoginResponse
import com.example.kkirikkiri.module.network.dto.account.response.ResultResponse
import com.example.kkirikkiri.module.google.GoogleResponse
import com.example.kkirikkiri.module.google.GoogleRetrofitImpl
import com.example.kkirikkiri.module.info.UserInfo
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
                        userid.value = response.body()
                        UserInfo.access_token = "Bearer " + response.body()!!.access_token
                        UserInfo.refresh_token = "Bearer " + response.body()!!.refresh_token
                        UserInfo.userId = response.body()!!.id
                        findName("Bearer " + response.body()!!.access_token, response.body()!!.id)

                        Log.e("token", response.body()!!.access_token)
                        Log.e("token", UserInfo.access_token)
                    }
                    else Log.e("실패", response.errorBody().toString() + code+ "  ," + response.message() + ", " + response.code())
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
}