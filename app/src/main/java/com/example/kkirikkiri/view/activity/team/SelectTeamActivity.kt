package com.example.kkirikkiri.view.activity.team

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kkirikkiri.R
import com.example.kkirikkiri.databinding.ActivitySelectTeamBinding
import com.example.kkirikkiri.module.dto.account.response.ResultResponse
import com.example.kkirikkiri.module.info.UserInfo
import com.example.kkirikkiri.view.recyclerview.RecyclerDecorationHeight
import com.example.kkirikkiri.view.recyclerview.myteam.select.SelectTeamRecyclerView
import com.example.kkirikkiri.viewmodel.LoginModel

class SelectTeamActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySelectTeamBinding.inflate(layoutInflater) }

    private val model = LoginModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        model.getAllTeam(UserInfo.userId!!)

        observe()

        binding.selectTeam.layoutManager = LinearLayoutManager(this)
        binding.selectTeam.addItemDecoration(RecyclerDecorationHeight(30))
    }

    fun observe() {
        model.teams.observe(this, Observer{
            binding.selectTeam.adapter = SelectTeamRecyclerView(it.result)
        })
    }
}