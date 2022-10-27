package com.example.kkirikkiri.view.activity.project.pid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kkirikkiri.R
import com.example.kkirikkiri.databinding.ActivityWritePidBinding
import com.example.kkirikkiri.module.network.dto.ContentRequest
import com.example.kkirikkiri.viewmodel.BoardModel

class ChangePid : AppCompatActivity() {

    private val binding by lazy { ActivityWritePidBinding.inflate(layoutInflater) }

    private val model = BoardModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_pid)

        val id = intent.getIntExtra("id", 0)

        binding.writePid.setOnClickListener {
            model.modifyBoard(id, ContentRequest(binding.pidWriteContent.text.toString()))
            finish()
        }
    }
}