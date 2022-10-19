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


    fun join(teamId : Int) {

    }
}