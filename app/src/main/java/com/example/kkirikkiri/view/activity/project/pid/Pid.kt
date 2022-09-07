package com.example.kkirikkiri.view.activity.project.pid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kkirikkiri.R
import com.example.kkirikkiri.databinding.ActivityPidBinding
import com.example.kkirikkiri.view.recyclerview.RecyclerDecorationHeight
import com.example.kkirikkiri.view.recyclerview.pid.PidAdapter
import com.example.kkirikkiri.view.recyclerview.pid.PidItem

class Pid : AppCompatActivity() {

    private lateinit var binding: ActivityPidBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pid)


        val adapter = PidAdapter(listOf(PidItem("힘드러....", 1)))
        binding.pidRecyclerview.adapter = adapter
        binding.pidRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.pidRecyclerview.addItemDecoration(RecyclerDecorationHeight(30))
    }
}