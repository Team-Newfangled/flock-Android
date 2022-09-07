package com.example.kkirikkiri.view.activity.team

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.kkirikkiri.R
import com.example.kkirikkiri.databinding.ActivityTeamProfileChangeBinding

class TeamProfileChange : AppCompatActivity() {

    private lateinit var binding: ActivityTeamProfileChangeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_team_profile_change)
    }
}