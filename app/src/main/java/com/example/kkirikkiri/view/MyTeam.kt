package com.example.kkirikkiri.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.kkirikkiri.R
import com.example.kkirikkiri.databinding.ActivityMyTeamBinding

class MyTeam : AppCompatActivity() {

    private lateinit var binding: ActivityMyTeamBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_team)

        binding.teamTeamManage.setOnClickListener { startActivity(Intent(applicationContext,
            TeamManage::class.java)) }

        binding.teamProfile.setOnClickListener { startActivity(Intent(applicationContext, TeamProfile::class.java)) }


    }
}