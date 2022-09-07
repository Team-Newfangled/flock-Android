package com.example.kkirikkiri.view.activity.team

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.kkirikkiri.R
import com.example.kkirikkiri.databinding.ActivityTeamProfileBinding

class TeamProfile : AppCompatActivity() {

    private lateinit var binding: ActivityTeamProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_team_profile)
    }
}