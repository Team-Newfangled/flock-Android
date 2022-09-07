package com.example.kkirikkiri.view.activity.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kkirikkiri.R
import com.example.kkirikkiri.databinding.ActivityProjectBinding
import com.example.kkirikkiri.view.activity.project.pid.Pid
import com.example.kkirikkiri.view.recyclerview.RecyclerDecorationWidth
import com.example.kkirikkiri.view.recyclerview.project.DeadLine
import com.example.kkirikkiri.view.recyclerview.project.DeadLineAdapter

class Project : AppCompatActivity() {

    private lateinit var binding: ActivityProjectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_project)

        binding.pid.setOnClickListener{ startActivity(Intent(applicationContext, Pid::class.java)) }

        binding.progress.setOnClickListener{ startActivity(Intent(applicationContext, Progress::class.java))}

        val dead1 = DeadLine("10","병신",1)
        val dead2 =DeadLine("9","진짜",2)
        val dead3 =DeadLine("8","집",3)
        val dead4 =DeadLine("7","가고",4)
        val dead5 =DeadLine("6","싶",5)
        val dead6 =DeadLine("5","다",6)
        val dead7 =DeadLine("4",".",7)
        val lise = listOf(dead1, dead2, dead3 ,dead4, dead5,dead6,dead7)
        val adapter = DeadLineAdapter(lise)

        binding.deadline.adapter = adapter
        binding.deadline.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true)
        binding.deadline.addItemDecoration(RecyclerDecorationWidth(30))
        binding.deadline.scrollToPosition(lise.size - 1)


    }
}