package com.example.kkirikkiri.view.activity.project.pid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.kkirikkiri.R
import com.example.kkirikkiri.databinding.ActivityWritePidBinding
import com.example.kkirikkiri.module.dto.ContentRequest
import com.example.kkirikkiri.module.info.UserInfo
import com.example.kkirikkiri.view.activity.project.Project
import com.example.kkirikkiri.viewmodel.BoardModel
import com.example.kkirikkiri.viewmodel.ProjectModel

class WritePid : AppCompatActivity() {

    private lateinit var binding:ActivityWritePidBinding

    private val model = BoardModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_write_pid)

        binding.writePid.setOnClickListener {
            model.saveBoard(UserInfo.projectId!!, ContentRequest(binding.pidWriteContent.text.toString()))
            Intent(this, Pid::class.java).run { startActivity(this) }
            finish()
        }
    }
}