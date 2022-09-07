package com.example.kkirikkiri.view.activity.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kkirikkiri.R
import com.example.kkirikkiri.databinding.ActivityProgressBinding
import com.example.kkirikkiri.view.recyclerview.RecyclerDecorationHeight
import com.example.kkirikkiri.view.recyclerview.myteam.member.TeamMemberProjectItem
import com.example.kkirikkiri.view.recyclerview.progress.PartProgressAdapter

class Progress : AppCompatActivity() {

    private lateinit var binding: ActivityProgressBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_progress)

        val adapter = PartProgressAdapter(listOf(TeamMemberProjectItem("asdasd",1,1), TeamMemberProjectItem("asdasd",12,2), TeamMemberProjectItem("asdasd",42,3)))

        binding.progressPartRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.progressPartRecyclerview.addItemDecoration(RecyclerDecorationHeight(30))
        binding.progressPartRecyclerview.adapter = adapter
    }
}