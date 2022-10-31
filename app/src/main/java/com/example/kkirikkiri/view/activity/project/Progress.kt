package com.example.kkirikkiri.view.activity.project

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kkirikkiri.calculater.Calculater
import com.example.kkirikkiri.databinding.ActivityProgressBinding
import com.example.kkirikkiri.module.info.UserInfo
import com.example.kkirikkiri.module.network.room.RoomImpl
import com.example.kkirikkiri.module.network.room.entity.TodoPercent
import com.example.kkirikkiri.module.network.room.helper.TodoHelper
import com.example.kkirikkiri.view.recyclerview.RecyclerDecorationHeight
import com.example.kkirikkiri.view.recyclerview.myteam.member.TeamMemberProjectItem
import com.example.kkirikkiri.view.recyclerview.progress.PartProgressAdapter
import com.example.kkirikkiri.viewmodel.TodoModel
import java.util.ArrayList

class Progress : AppCompatActivity() {

    private val binding by lazy { ActivityProgressBinding.inflate(layoutInflater) }

    private val model = TodoModel()

    private var list = ArrayList<TeamMemberProjectItem>()

    private var helper : TodoHelper? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        list.clear()

        helper = RoomImpl.getHelper(this)
        val roomTodo = helper?.todoPercentDao()?.getAllByProject(UserInfo.projectId!!)

        model.findAllTodos(UserInfo.projectId!!, 0)

        observe(roomTodo!!)

        binding.progressPartRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.progressPartRecyclerview.addItemDecoration(RecyclerDecorationHeight(30))

        binding.progressAddPart.setOnClickListener {
            Intent(this, AddProgressActivity::class.java).run { startActivity(this) }
            finish()
        }

        if (roomTodo.isNotEmpty()) {
            val sum = Calculater.calculate(roomTodo)

            binding.progressProjectCircle.progress = sum
            binding.progressProjectPercent.text = "$sum%"
        }
    }

    override fun onRestart() {
        super.onRestart()
        refresh()
    }

    private fun refresh() {
        finish()
        overridePendingTransition(0,0)
        startActivity(intent)
        overridePendingTransition(0,0)
    }

    private fun observe(roomTodo : List<TodoPercent>) {
        model.allTodos.observe(this) {

            if (it.results.isNotEmpty()){
                for (i in 0 until it.results.size) {
                    list.add(TeamMemberProjectItem(
                        it.results[i].content,
                        roomTodo[i].percent,
                        it.results[i].id,
                        roomTodo[i].id))

                    binding.progressPartRecyclerview.adapter = PartProgressAdapter(list, intent, this)
                }
            }
        }
    }
}