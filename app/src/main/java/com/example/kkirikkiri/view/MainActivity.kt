package com.example.kkirikkiri.view

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
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
import com.example.kkirikkiri.module.info.UserInfo
import com.example.kkirikkiri.view.activity.team.SelectTeamActivity
import com.example.kkirikkiri.viewmodel.*

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val model = TeamModel()
    private val userIdModel = LoginModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        observe()

        binding.mainMyTeam.setOnClickListener{ startActivity(Intent(applicationContext, SelectTeamActivity::class.java)) }
        binding.mainMakeTeam.setOnClickListener {
            val dialog = Dialog(this)

            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.dialog_addteam)

            dialog.show()

            val text = dialog.findViewById<EditText>(R.id.editText)
            val acp = dialog.findViewById<Button>(R.id.button)
            val cancel = dialog.findViewById<Button>(R.id.button2)

            acp.setOnClickListener {
                model.createTeam(text.text.toString())
                dialog.dismiss()
            }

            cancel.setOnClickListener {
                dialog.dismiss()
            }
        }
    }


//    fun setToolBar(layout:Int) {
//        val toolbar = findViewById<Toolbar>(layout)
//        setSupportActionBar(toolbar)
//    }

    private fun observe() {
        userIdModel.userid.observe(this) {
            UserInfo.userId = it?.id
        }

        model.teamId.observe(this) {
            UserInfo.teamId = it?.teamId
        }
    }
}