package com.example.kkirikkiri.view.activity.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kkirikkiri.R
import com.example.kkirikkiri.databinding.ActivityProjectBinding
import com.example.kkirikkiri.view.activity.project.pid.Pid
import com.example.kkirikkiri.view.recyclerview.RecyclerDecorationHeight
import com.example.kkirikkiri.view.recyclerview.RecyclerDecorationWidth
import com.example.kkirikkiri.view.recyclerview.project.deadline.DeadLineItem
import com.example.kkirikkiri.view.recyclerview.project.deadline.DeadLineAdapter
import com.example.kkirikkiri.view.recyclerview.project.projectpid.PidAdapter
import com.example.kkirikkiri.view.recyclerview.project.projectpid.PidItem

class Project : AppCompatActivity() {

    private lateinit var binding: ActivityProjectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_project)

        binding.pid.setOnClickListener{ startActivity(Intent(applicationContext, Pid::class.java)) }

        binding.progress.setOnClickListener{ startActivity(Intent(applicationContext, Progress::class.java))}

        val dead1 = DeadLineItem("10","병신",1)
        val dead2 = DeadLineItem("9","진짜",2)
        val dead3 = DeadLineItem("8","집",3)
        val dead4 = DeadLineItem("7","가고",4)
        val dead5 = DeadLineItem("6","싶",5)
        val dead6 = DeadLineItem("5","다",6)
        val dead7 = DeadLineItem("4",".",7)
        val lise = listOf(dead1, dead2, dead3 ,dead4, dead5,dead6,dead7)
        val adapter = DeadLineAdapter(lise)

        binding.deadline.adapter = adapter
        binding.deadline.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true)
        binding.deadline.addItemDecoration(RecyclerDecorationWidth(30))
        binding.deadline.scrollToPosition(lise.size - 1)

        val pid1 = PidItem("제목임", 1)
        val pid2 = PidItem("제목", 2)
        val pid3 = PidItem("제", 3)
        val pid4 = PidItem("ㅈ", 4)
        val pid5 = PidItem("ㅁ", 5)
        val list = listOf(pid1, pid2, pid3, pid4, pid5)
        val pidAdapter = PidAdapter(list)
        binding.pidRecyclerview.adapter = pidAdapter
        binding.pidRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.pidRecyclerview.addItemDecoration(RecyclerDecorationHeight(15))


    }
}