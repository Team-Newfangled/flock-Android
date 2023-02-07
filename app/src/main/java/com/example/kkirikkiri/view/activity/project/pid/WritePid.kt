package com.example.kkirikkiri.view.activity.project.pid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.kkirikkiri.databinding.ActivityWritePidBinding
import com.example.kkirikkiri.module.network.dto.ContentRequest
import com.example.kkirikkiri.module.info.UserInfo
import com.example.kkirikkiri.view.activity.BaseActivity
import com.example.kkirikkiri.viewmodel.BoardModel

class WritePid : BaseActivity<ActivityWritePidBinding>({ ActivityWritePidBinding.inflate(it) }) {

    private val model = BoardModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.writePid.setOnClickListener {
            if (binding.pidWriteContent.text.isNotEmpty()){
            model.saveBoard(UserInfo.projectId!!, ContentRequest(binding.pidWriteContent.text.toString()))
            Intent(this, Pid::class.java).run { startActivity(this) }
            finish()
            } else Toast.makeText(this, "피드의 내용을 입력해주세요", Toast.LENGTH_SHORT).show()
        }

        binding.addProjectBack.root.setOnClickListener { finish() }
    }
}