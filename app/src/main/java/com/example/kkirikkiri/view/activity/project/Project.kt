package com.example.kkirikkiri.view.activity.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kkirikkiri.R
import com.example.kkirikkiri.databinding.ActivityProjectBinding
import com.example.kkirikkiri.module.dto.todo.response.FindDeadLineResponse
import com.example.kkirikkiri.module.info.UserInfo
import com.example.kkirikkiri.view.activity.project.pid.Pid
import com.example.kkirikkiri.view.recyclerview.RecyclerDecorationHeight
import com.example.kkirikkiri.view.recyclerview.RecyclerDecorationWidth
import com.example.kkirikkiri.view.recyclerview.pid.PidItem
import com.example.kkirikkiri.view.recyclerview.project.deadline.DeadLineItem
import com.example.kkirikkiri.view.recyclerview.project.deadline.DeadLineAdapter
import com.example.kkirikkiri.view.recyclerview.project.projectpid.ProjectPidAdapter
import com.example.kkirikkiri.view.recyclerview.project.projectpid.ProjectPidItem
import com.example.kkirikkiri.viewmodel.BoardModel
import com.example.kkirikkiri.viewmodel.ProjectModel
import com.example.kkirikkiri.viewmodel.TodoModel
import java.util.ArrayList

class Project : AppCompatActivity() {

    private lateinit var binding: ActivityProjectBinding

    private val deadLineList = ArrayList<DeadLineItem>()
    private val pidList = ArrayList<PidItem>()

    private val model = BoardModel()
    private val todoModel = TodoModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_project)

        UserInfo.projectId = intent.getIntExtra("id", 0)

        observe()

        todoModel.findDeadLines(intent.getIntExtra("id",0))
        model.findBoardPage(intent.getIntExtra("id",0), 0)

        binding.pid.setOnClickListener{ startActivity(Intent(applicationContext, Pid::class.java)) }
        binding.progress.setOnClickListener{ startActivity(Intent(applicationContext, Progress::class.java))}
        binding.deadline.setOnClickListener { startActivity(Intent(applicationContext, Progress::class.java)) }

        val adapter = DeadLineAdapter(deadLineList)

        binding.deadline.adapter = adapter
        binding.deadline.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true)
        binding.deadline.addItemDecoration(RecyclerDecorationWidth(30))
        binding.deadline.scrollToPosition(deadLineList.size - 1)

        val pidAdapter = ProjectPidAdapter(pidList)

        binding.pidRecyclerview.adapter = pidAdapter
        binding.pidRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.pidRecyclerview.addItemDecoration(RecyclerDecorationHeight(15))
    }

    private fun observe() {
        model.boardList.observe(this) {
            for (i in it) {
                pidList.add(PidItem(i.content, i.id, i.writerId))
                binding.pidRecyclerview.adapter = ProjectPidAdapter(pidList)
            }
        }

        todoModel.deadLineList.observe(this) {
            for (i in it.results) {
                deadLineList.add(DeadLineItem(i.endDate.toString(), i.content, i.id ))
                binding.deadline.adapter = DeadLineAdapter(deadLineList)
            }
        }
    }
}