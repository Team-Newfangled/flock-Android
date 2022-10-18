package com.example.kkirikkiri.viewmodel

import androidx.lifecycle.ViewModel
import com.example.kkirikkiri.module.RetrofitImpl
import com.example.kkirikkiri.module.dto.Default
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamJoinModel : ViewModel(){

    private val service = RetrofitImpl.teamJoinService

    fun joinMail() {
        CoroutineScope(Dispatchers.IO).launch {
            service.joinMail()
        }
    }

    fun joinComplete() {
        CoroutineScope(Dispatchers.IO).launch {
            service.joinComplete()
        }
    }

    fun join(teamId : Int) {
        CoroutineScope(Dispatchers.IO).launch {
            service.join(teamId)
        }
    }

    fun joinMember(teamId: Int, memberId : Int) {
        CoroutineScope(Dispatchers.IO).launch {
            service.joinMember(teamId, memberId)
        }
    }

    fun sendJoinMain(teamId : Int, email : String) {
        CoroutineScope(Dispatchers.IO).launch {
            service.sendJoinMail(teamId, email).enqueue(object : Callback<Default>{
                override fun onResponse(call: Call<Default>, response: Response<Default>) {
                }

                override fun onFailure(call: Call<Default>, t: Throwable) {
                }

            })
        }
    }
}