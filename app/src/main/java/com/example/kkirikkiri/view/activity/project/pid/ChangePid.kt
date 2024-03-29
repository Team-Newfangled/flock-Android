package com.example.kkirikkiri.view.activity.project.pid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kkirikkiri.R
import com.example.kkirikkiri.databinding.ActivityWritePidBinding
import com.example.kkirikkiri.module.network.dto.ContentRequest
import com.example.kkirikkiri.view.activity.BaseActivity
import com.example.kkirikkiri.viewmodel.BoardModel

class ChangePid : BaseActivity<ActivityWritePidBinding>({ ActivityWritePidBinding.inflate(it) }) {


    private val model = BoardModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val id = intent.getIntExtra("id", 0)
        val name = intent.getStringExtra("name")

        binding.writePid.setOnClickListener {
            if (binding.pidWriteContent.text.isEmpty()) {
                model.modifyBoard(id, ContentRequest(name.toString()))
            }else model.modifyBoard(id, ContentRequest(binding.pidWriteContent.text.toString()))
            finish()
        }

        binding.addProjectBack.root.setOnClickListener { finish() }
    }
}