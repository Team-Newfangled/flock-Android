package com.example.kkirikkiri.viewmodel

import androidx.lifecycle.ViewModel
import com.example.kkirikkiri.module.network.RetrofitImpl

class TeamJoinModel : ViewModel(){

    private val service = RetrofitImpl.teamJoinService


}