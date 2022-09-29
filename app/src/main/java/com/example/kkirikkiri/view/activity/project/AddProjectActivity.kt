package com.example.kkirikkiri.view.activity.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kkirikkiri.databinding.ActivityAddProjectBinding
import com.example.kkirikkiri.module.dto.NameRequest
import com.example.kkirikkiri.module.info.UserInfo
import com.example.kkirikkiri.view.activity.team.MyTeam
import com.example.kkirikkiri.viewmodel.TeamModel

class AddProjectActivity : AppCompatActivity() {

    private val binding by lazy { ActivityAddProjectBinding.inflate(layoutInflater) }

    private val model = TeamModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.saveProject.setOnClickListener {
            model.addProject(UserInfo.teamId!!, NameRequest(binding.projectName.text.toString()))
            Intent(this, MyTeam::class.java).run { startActivity(this) }
            finish()
        }
    }
}