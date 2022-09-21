package com.example.kkirikkiri.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.example.kkirikkiri.R
import com.example.kkirikkiri.databinding.ActivityMainBinding
import com.example.kkirikkiri.module.dto.NameRequest
import com.example.kkirikkiri.view.activity.team.MyTeam
import com.example.kkirikkiri.viewmodel.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val model1 = BoardModel()
    val model2 = LoginModel()
    val model3 = ProjectModel()
    val model4 = TeamJoinModel()
    val model5 = TeamModel()
    val model6 = TodoModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        model5.createTeam(NameRequest("test"))
        model5.getProject(1,1)
        model5.getTeamMember(1,1)
        model5.addProject(1, NameRequest("1"))
        model5.deleteMember(1,1)
        model1.deleteBoard(1)
        model1.deleteComment(1)
        model1.findBoard(1)
        model1.findBoardPage(1,1)
        model1.findComment(1,1)
        model1.writeComment(1,"1")


        binding.mainMyTeam.setOnClickListener{ startActivity(Intent(applicationContext, MyTeam::class.java)) }
    }


    fun setToolBar(layout:Int) {
        val toolbar = findViewById<Toolbar>(layout)
        setSupportActionBar(toolbar)
    }
}