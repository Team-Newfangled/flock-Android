package com.example.kkirikkiri.view.activity.project

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kkirikkiri.databinding.ActivityProgressBinding
import com.example.kkirikkiri.module.info.UserInfo
import com.example.kkirikkiri.view.activity.BaseActivity
import com.example.kkirikkiri.view.recyclerview.RecyclerDecorationHeight
import com.example.kkirikkiri.view.recyclerview.myteam.member.TeamMemberProjectItem
import com.example.kkirikkiri.view.recyclerview.progress.PartProgressAdapter
import com.example.kkirikkiri.viewmodel.TodoModel
import java.util.ArrayList

class Progress : BaseActivity<ActivityProgressBinding>({ ActivityProgressBinding.inflate(it) }) {

    private val model = TodoModel()
    private var list = ArrayList<TeamMemberProjectItem>()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        list.clear()

        model.findAllTodos(UserInfo.projectId!!, 0)



        observe()

        binding.progressPartRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.progressPartRecyclerview.addItemDecoration(RecyclerDecorationHeight(30))

        binding.progressAddPart.setOnClickListener {
            Intent(this, AddProgressActivity::class.java).run { startActivity(this) }
            finish()
        }
    }

    override fun onRestart() {
        super.onRestart()
        list.clear()
        model.findAllTodos(UserInfo.projectId!!, 0)
        observe()
        refresh()
    }

    private fun refresh() {
        finish()
        overridePendingTransition(0,0)
        startActivity(intent)
        overridePendingTransition(0,0)
    }

    private fun observe() {
        model.allTodos.observe(this) {
            var sum = 0
            if (it.results.isNotEmpty()){
                for (i in 0 until it.results.size) {
                    sum += it.results[i].percent
                    list.add(TeamMemberProjectItem(it.results[i].content, it.results[i].percent, it.results[i].id))
                    Log.e("list", "${list}  ${it.results[i].content}")
                    binding.progressPartRecyclerview.adapter = PartProgressAdapter(list, intent, this)
                }
                binding.progressProjectPercent.text = (sum / it.results.size).toString()
                binding.progressProjectCircle.progress = (sum / it.results.size)
            }
        }
    }
}