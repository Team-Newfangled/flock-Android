package com.example.kkirikkiri.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.kkirikkiri.R
import com.example.kkirikkiri.module.RetrofitImpl
import com.example.kkirikkiri.module.dto.response.GoogleLoginResponse
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginModel : ViewModel(){
    private val service = RetrofitImpl.service


    fun signInResult(task : Task<GoogleSignInAccount>) {
        val account = task.getResult(ApiException::class.java)
        val code = account.serverAuthCode

            service.loginWithGoogle(code.toString()).enqueue(object : Callback<GoogleLoginResponse> {
                override fun onResponse(
                    call: Call<GoogleLoginResponse>,
                    response: Response<GoogleLoginResponse>
                ) {
                    if (response.isSuccessful) Log.e("성공", "성공함")
                    else Log.e("실패", code.toString())
                }

                override fun onFailure(call: Call<GoogleLoginResponse>, t: Throwable) {
                    Log.e("실패", "연결 실패" + code.toString())
                }

            })

    }


}