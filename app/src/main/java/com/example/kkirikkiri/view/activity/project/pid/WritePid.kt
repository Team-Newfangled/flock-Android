package com.example.kkirikkiri.view.activity.project.pid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kkirikkiri.databinding.ActivityWritePidBinding
import com.example.kkirikkiri.module.network.dto.ContentRequest
import com.example.kkirikkiri.module.info.UserInfo
import com.example.kkirikkiri.viewmodel.BoardModel

class WritePid : AppCompatActivity() {

    private val binding by lazy { ActivityWritePidBinding.inflate(layoutInflater) }

    private val model = BoardModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.writePid.setOnClickListener {
            model.saveBoard(UserInfo.projectId!!, ContentRequest(binding.pidWriteContent.text.toString()))
            Intent(this, Pid::class.java).run { startActivity(this) }
            finish()
        }
    }
}