package com.example.kkirikkiri.view.activity.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kkirikkiri.databinding.ActivityProjectBinding
import com.example.kkirikkiri.module.info.UserInfo
import com.example.kkirikkiri.view.activity.BaseActivity
import com.example.kkirikkiri.view.activity.project.pid.Pid
import com.example.kkirikkiri.view.recyclerview.RecyclerDecorationHeight
import com.example.kkirikkiri.view.recyclerview.RecyclerDecorationWidth
import com.example.kkirikkiri.view.recyclerview.pid.PidItem
import com.example.kkirikkiri.view.recyclerview.project.deadline.DeadLineItem
import com.example.kkirikkiri.view.recyclerview.project.deadline.DeadLineAdapter
import com.example.kkirikkiri.view.recyclerview.project.projectpid.ProjectPidAdapter
import com.example.kkirikkiri.viewmodel.BoardModel
import com.example.kkirikkiri.viewmodel.TodoModel
import java.text.SimpleDateFormat
import java.util.*

class Project : BaseActivity<ActivityProjectBinding>({ ActivityProjectBinding.inflate(it) }) {

    private val deadLineList = ArrayList<DeadLineItem>()
    private val pidList = ArrayList<PidItem>()

    private val model = BoardModel()
    private val todoModel = TodoModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        deadLineList.clear()
        pidList.clear()
        todoModel.findAllTodos(intent.getIntExtra("id",0), 0)

        UserInfo.projectId = intent.getIntExtra("id", 0)


        todoModel.findDeadLines(intent.getIntExtra("id",0))
        model.findBoardPage(intent.getIntExtra("id",0), 0)

        observe()
        binding.toolbarTitle.text = intent.getStringExtra("name")

        binding.pid.setOnClickListener{ startActivity(Intent(applicationContext, Pid::class.java)) }
        binding.textView10.setOnClickListener{ startActivity(Intent(applicationContext, Progress::class.java)) }
        binding.textView4.setOnClickListener{ startActivity(Intent(applicationContext, Progress::class.java)) }
        binding.deadline.setOnClickListener { startActivity(Intent(applicationContext, Progress::class.java)) }

        binding.deadline.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true)
        binding.deadline.addItemDecoration(RecyclerDecorationWidth(30))
        binding.deadline.scrollToPosition(deadLineList.size - 1)

        binding.pidRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.pidRecyclerview.addItemDecoration(RecyclerDecorationHeight(15))
        binding.pidRecyclerview.setOnClickListener { startActivity(Intent(applicationContext, Progress::class.java)) }



    }

    override fun onRestart() {
        super.onRestart()
        Log.e("restart", "restart")
        deadLineList.clear()
        pidList.clear()
        refresh()
    }

    private fun observe() {
        model.boardList.observe(this) {
            for (i in it) {
                pidList.add(PidItem(i.content, i.id, i.writerId))
                binding.pidRecyclerview.adapter = ProjectPidAdapter(pidList,intent, this)
            }
        }

        val today = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.time.time

        val dateFormat = SimpleDateFormat("yyyyMMdd")

        todoModel.deadLineList.observe(this) {
            for (i in it.results) {
                val replace = i.endDate.replace("-","")
                val end = dateFormat.parse(replace)?.time

                deadLineList.add(DeadLineItem(((end?.minus(today))?.div((24 * 60 * 60 * 1000))).toString(), i.content, i.id ))
                binding.deadline.adapter = DeadLineAdapter(deadLineList, this)
            }
        }

        todoModel.allTodos.observe(this) {
            var sum = 0;
            for (i in it.results) {
                sum += i.percent
            }
            if (it.results.isEmpty()){
                sum = 0
            }else sum /= it.results.size
            binding.progressPercent.text = "${sum}%"
            binding.progressBarAll.progress = sum
        }
    }

    private fun refresh() {
        finish()
        overridePendingTransition(0,0)
        startActivity(intent)
        overridePendingTransition(0,0)
    }
}