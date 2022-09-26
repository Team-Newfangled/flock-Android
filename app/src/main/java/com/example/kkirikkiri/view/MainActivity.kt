package com.example.kkirikkiri.view

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.example.kkirikkiri.R
import com.example.kkirikkiri.databinding.ActivityMainBinding
import com.example.kkirikkiri.module.dto.NameRequest
import com.example.kkirikkiri.view.activity.team.MyTeam
import com.example.kkirikkiri.viewmodel.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val model = TeamModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.mainMyTeam.setOnClickListener{ startActivity(Intent(applicationContext, MyTeam::class.java)) }
        binding.mainMakeTeam.setOnClickListener {
            val dialog = Dialog(applicationContext)

            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.dialog_addteam)

            dialog.show()

            val text = findViewById<EditText>(R.id.editText)
            val acp = findViewById<Button>(R.id.button)
            val cancel = findViewById<Button>(R.id.button2)

            acp.setOnClickListener {
                model.createTeam(NameRequest(text.text.toString()))
                dialog.dismiss()
            }

            cancel.setOnClickListener {
                dialog.dismiss()
            }
        }
    }


    fun setToolBar(layout:Int) {
        val toolbar = findViewById<Toolbar>(layout)
        setSupportActionBar(toolbar)
    }
}