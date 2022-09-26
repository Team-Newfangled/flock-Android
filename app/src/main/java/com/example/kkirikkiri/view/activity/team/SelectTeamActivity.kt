package com.example.kkirikkiri.view.activity.team

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kkirikkiri.R
import com.example.kkirikkiri.databinding.ActivitySelectTeamBinding
import com.example.kkirikkiri.module.dto.account.response.ResultResponse
import com.example.kkirikkiri.module.info.UserInfo
import com.example.kkirikkiri.view.recyclerview.myteam.select.SelectTeamRecyclerView
import com.example.kkirikkiri.viewmodel.LoginModel

class SelectTeamActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySelectTeamBinding.inflate(layoutInflater) }

    private val model = LoginModel()

    private lateinit var list : List<ResultResponse.Result>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        observe()

        model.getAllTeam(UserInfo.userId!!)
        val adapter = SelectTeamRecyclerView(list)

        binding.selectTeam.adapter = adapter
        binding.selectTeam.layoutManager = LinearLayoutManager(this)


    }

    fun observe() {
        model.teams.observe(this, Observer {
            list = it.result
        })
    }
}