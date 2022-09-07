package com.example.kkirikkiri.view.activity.project.pid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.kkirikkiri.R
import com.example.kkirikkiri.databinding.ActivityPidViewBinding

class PidView : AppCompatActivity() {

    private lateinit var binding:ActivityPidViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pid_view)
    }
}