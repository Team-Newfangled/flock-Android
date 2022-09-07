package com.example.kkirikkiri.view.activity.team

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kkirikkiri.R
import com.example.kkirikkiri.databinding.ActivityMyTeamBinding
import com.example.kkirikkiri.view.recyclerview.RecyclerDecorationHeight
import com.example.kkirikkiri.view.recyclerview.myteam.team.MyTeamAdapter
import com.example.kkirikkiri.view.recyclerview.myteam.team.MyTeamProjectAdapter
import com.example.kkirikkiri.view.recyclerview.myteam.member.TeamMemberItem
import com.example.kkirikkiri.view.recyclerview.myteam.member.TeamMemberProjectItem

class MyTeam : AppCompatActivity() {

    private lateinit var binding: ActivityMyTeamBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_team)

        binding.teamTeamManage.setOnClickListener { startActivity(Intent(applicationContext,
            TeamManage::class.java)) }

        val member1 = TeamMemberItem("서주영", 1)
        val member2 = TeamMemberItem("서주이", 1)
        val member3 = TeamMemberItem("서주오", 1)
        val member4 = TeamMemberItem("서주사", 1)
        val member5 = TeamMemberItem("서주지", 1)
        val member6 = TeamMemberItem("서주잔", 1)
        val list = listOf(member1, member2, member3, member4, member5, member6 )

        binding.teamMember.adapter = MyTeamAdapter(list)
        binding.teamMember.layoutManager = LinearLayoutManager(this)
        binding.teamMember.addItemDecoration(RecyclerDecorationHeight(30))

        binding.teamProfile.setOnClickListener { startActivity(Intent(applicationContext, TeamProfile::class.java)) }

        val project1 = TeamMemberProjectItem("끼리끼리", 10, 1)
        val project2 = TeamMemberProjectItem("서로서로", 100, 2)
        val project3 = TeamMemberProjectItem("우리끼리", 70, 3)
        val project4 = TeamMemberProjectItem("너같은놈", 60, 4)
        val project5 = TeamMemberProjectItem("아 망했다", 5, 5)
        val list1 = listOf(project1, project2, project3, project4, project5)
        binding.project.adapter = MyTeamProjectAdapter(list1)
        binding.project.layoutManager = LinearLayoutManager(this)
        binding.project.addItemDecoration(RecyclerDecorationHeight(30))

    }
}