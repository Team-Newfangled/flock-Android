package com.example.kkirikkiri.view.activity.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kkirikkiri.R
import com.example.kkirikkiri.databinding.ActivityProgressBinding
import com.example.kkirikkiri.module.dto.ContentRequest
import com.example.kkirikkiri.module.info.UserInfo
import com.example.kkirikkiri.view.recyclerview.RecyclerDecorationHeight
import com.example.kkirikkiri.view.recyclerview.myteam.member.TeamMemberProjectItem
import com.example.kkirikkiri.view.recyclerview.progress.PartProgressAdapter
import com.example.kkirikkiri.viewmodel.TodoModel
import java.util.ArrayList

class Progress : AppCompatActivity() {

    private val binding by lazy { ActivityProgressBinding.inflate(layoutInflater) }

    private val model = TodoModel()

    private var list = ArrayList<TeamMemberProjectItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        model.findAllTodos(UserInfo.projectId!!, UserInfo.userId!!, 0)

        observe()
        val adapter = PartProgressAdapter(list)

        binding.progressPartRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.progressPartRecyclerview.addItemDecoration(RecyclerDecorationHeight(30))
        binding.progressPartRecyclerview.adapter = adapter

        binding.progressAddPart.setOnClickListener {
            Intent(this, AddProgressActivity::class.java).run { startActivity(this) }
            finish()
        }
    }

    private fun observe() {
        model.allTodos.observe(this) {
            for (i in it.results) {
                list.add(TeamMemberProjectItem(i.content, 0, i.id))
                binding.progressPartRecyclerview.adapter = PartProgressAdapter(list)
            }
        }

    }
}