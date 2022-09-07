package com.example.kkirikkiri.view.activity.team

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kkirikkiri.R
import com.example.kkirikkiri.databinding.ActivityMyTeamBinding
import com.example.kkirikkiri.view.recyclerview.RecyclerDecorationHeight
import com.example.kkirikkiri.view.recyclerview.myteam.MyTeamAdapter
import com.example.kkirikkiri.view.recyclerview.myteam.MyTeamProjectAdapter
import com.example.kkirikkiri.view.recyclerview.myteam.TeamMember
import com.example.kkirikkiri.view.recyclerview.myteam.TeamMemberProject

class MyTeam : AppCompatActivity() {

    private lateinit var binding: ActivityMyTeamBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_team)

        binding.teamTeamManage.setOnClickListener { startActivity(Intent(applicationContext,
            TeamManage::class.java)) }

        val member1 = TeamMember("서주영", 1)
        val member2 = TeamMember("서주이", 1)
        val member3 = TeamMember("서주오", 1)
        val member4 = TeamMember("서주사", 1)
        val member5 = TeamMember("서주지", 1)
        val member6 = TeamMember("서주잔", 1)
        val list = listOf(member1, member2, member3, member4, member5, member6 )

        binding.teamMember.adapter = MyTeamAdapter(list)
        binding.teamMember.layoutManager = LinearLayoutManager(this)
        binding.teamMember.addItemDecoration(RecyclerDecorationHeight(30))

        binding.teamProfile.setOnClickListener { startActivity(Intent(applicationContext, TeamProfile::class.java)) }

        val project1 = TeamMemberProject("끼리끼리", 10, 1)
        val project2 = TeamMemberProject("서로서로", 100, 2)
        val project3 = TeamMemberProject("우리끼리", 70, 3)
        val project4 = TeamMemberProject("너같은놈", 60, 4)
        val project5 = TeamMemberProject("아 망했다", 5, 5)
        val list1 = listOf(project1, project2, project3, project4, project5)
        binding.project.adapter = MyTeamProjectAdapter(list1)
        binding.project.layoutManager = LinearLayoutManager(this)
        binding.project.addItemDecoration(RecyclerDecorationHeight(30))

    }
}