package com.example.kkirikkiri.viewmodel

import androidx.lifecycle.ViewModel
import com.example.kkirikkiri.module.RetrofitImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
            service.sendJoinMail(teamId, email)
        }
    }
}