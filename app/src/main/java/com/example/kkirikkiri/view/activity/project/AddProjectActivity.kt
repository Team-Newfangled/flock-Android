package com.example.kkirikkiri.view.activity.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.kkirikkiri.databinding.ActivityAddProjectBinding
import com.example.kkirikkiri.module.network.dto.NameRequest
import com.example.kkirikkiri.module.info.UserInfo
import com.example.kkirikkiri.viewmodel.TeamModel

class AddProjectActivity : AppCompatActivity() {

    private val binding by lazy { ActivityAddProjectBinding.inflate(layoutInflater) }

    private val model = TeamModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.saveProject.setOnClickListener {
            if (binding.projectName.text.isNotEmpty()) {
                model.addProject(UserInfo.teamId!!, NameRequest(binding.projectName.text.toString()))
                finish()
            }
            else Toast.makeText(this, "프로젝트 이름을 입력해 주세요", Toast.LENGTH_SHORT).show()
        }
        binding.addProjectBack.root.setOnClickListener { finish() }
    }
}