package com.example.kkirikkiri.view.activity.team

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kkirikkiri.databinding.ActivitySelectTeamBinding
import com.example.kkirikkiri.module.info.UserInfo
import com.example.kkirikkiri.module.network.dto.TeamToProjectData
import com.example.kkirikkiri.module.network.dto.account.response.ResultResponse
import com.example.kkirikkiri.module.network.dto.team.response.FindProjectResponse
import com.example.kkirikkiri.view.recyclerview.RecyclerDecorationHeight
import com.example.kkirikkiri.view.recyclerview.myteam.select.SelectTeamRecyclerView
import com.example.kkirikkiri.view.recyclerview.myteam.select.TodayTodoAdapter
import com.example.kkirikkiri.viewmodel.LoginModel
import com.example.kkirikkiri.viewmodel.ProjectModel
import com.example.kkirikkiri.viewmodel.TeamModel
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class SelectTeamActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySelectTeamBinding.inflate(layoutInflater) }

    private val model = LoginModel()
    private val projectModel = TeamModel()

    private var teamList = ArrayList<ResultResponse.Result>()
    private var list = ArrayList<TeamToProjectData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val calendar = Calendar.getInstance()
        var day = "1"

        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1
        val today = calendar.get(Calendar.DAY_OF_MONTH)

        when(calendar.get(Calendar.DAY_OF_WEEK)) {
            1 -> day = "SUNDAY"
            2 -> day = "MONDAY"
            3 -> day = "TUESDAY"
            4 -> day = "WEDNESDAY"
            5 -> day = "THURSDAY"
            6 -> day = "FRIDAY"
            7 -> day = "SATURDAY"
        }

        model.getAllTeam(UserInfo.userId!!)
        model.getTodayTodo(year, month, today)
        observe()

        binding.mainYear.text = year.toString()
        binding.month.text = month.toString()
        binding.day.text = today.toString()
        binding.dayText.text = day

        binding.selectTeam.layoutManager = LinearLayoutManager(this)
        binding.selectTeam.addItemDecoration(RecyclerDecorationHeight(30))

        binding.todayTodos.layoutManager = LinearLayoutManager(this)
        binding.todayTodos.addItemDecoration(RecyclerDecorationHeight(10))
    }

    override fun onRestart() {
        super.onRestart()
        teamList.clear()
        list.clear().apply { refresh() }
    }

    private fun observe() {
        model.teams.observe(this, Observer{ result ->
            teamList.addAll(result.result)
            for (i in teamList){
                projectModel.getProject(i.teamId, 0)
            }
        })
        projectModel.projects.observe(this) {
            list.add(it)
            binding.selectTeam.adapter = SelectTeamRecyclerView(teamList, this, intent, list)
        }

        model.todayTodos.observe(this) {
            binding.todayTodos.adapter = TodayTodoAdapter(it)
        }
    }

    private fun refresh() {
        finish()
        overridePendingTransition(0,0)
        startActivity(intent)
        overridePendingTransition(0,0)
    }
}