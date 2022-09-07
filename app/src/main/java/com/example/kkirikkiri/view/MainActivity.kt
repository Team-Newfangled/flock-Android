package com.example.kkirikkiri.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.example.kkirikkiri.R
import com.example.kkirikkiri.databinding.ActivityMainBinding
import com.example.kkirikkiri.view.activity.team.MyTeam

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.mainMyTeam.setOnClickListener{ startActivity(Intent(applicationContext, MyTeam::class.java)) }
    }


    fun setToolBar(layout:Int) {
        val toolbar = findViewById<Toolbar>(layout)
        setSupportActionBar(toolbar)
    }
}