package com.example.kkirikkiri.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.kkirikkiri.R
import com.example.kkirikkiri.databinding.ActivityProjectBinding

class Project : AppCompatActivity() {

    private lateinit var binding: ActivityProjectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_project)

        binding.pid.setOnClickListener{ startActivity(Intent(applicationContext, Pid::class.java)) }

        binding.progress.setOnClickListener{ startActivity(Intent(applicationContext, Progress::class.java))}
    }
}