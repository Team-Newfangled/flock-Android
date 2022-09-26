package com.example.kkirikkiri.view.activity.team

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kkirikkiri.R
import com.example.kkirikkiri.databinding.ActivityMyTeamBinding
import com.example.kkirikkiri.module.dto.team.response.FindMembersResponse
import com.example.kkirikkiri.module.dto.team.response.FindProjectResponse
import com.example.kkirikkiri.view.recyclerview.RecyclerDecorationHeight
import com.example.kkirikkiri.view.recyclerview.myteam.team.MyTeamAdapter
import com.example.kkirikkiri.view.recyclerview.myteam.member.MyTeamProjectAdapter
import com.example.kkirikkiri.view.recyclerview.myteam.team.TeamMemberItem
import com.example.kkirikkiri.view.recyclerview.myteam.member.TeamMemberProjectItem
import com.example.kkirikkiri.viewmodel.TeamModel
import java.util.ArrayList

class MyTeam : AppCompatActivity() {

    private lateinit var binding: ActivityMyTeamBinding

    private val model = TeamModel()

    private var list = ArrayList<TeamMemberItem>()
    private var list1 = ArrayList<TeamMemberProjectItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_team)

        val intent = intent
        val id = intent.getIntExtra("id", 0)

        model.getTeamMember(id, 10)
        model.getProject(id, 10)

        binding.teamTeamManage.setOnClickListener { startActivity(Intent(applicationContext,
            TeamManage::class.java)) }


        binding.teamMember.adapter = MyTeamAdapter(list)
        binding.teamMember.layoutManager = LinearLayoutManager(this)
        binding.teamMember.addItemDecoration(RecyclerDecorationHeight(30))

        binding.teamProfile.setOnClickListener { startActivity(Intent(applicationContext, TeamProfile::class.java)) }

        binding.project.adapter = MyTeamProjectAdapter(list1)
        binding.project.layoutManager = LinearLayoutManager(this)
        binding.project.addItemDecoration(RecyclerDecorationHeight(30))

    }

    fun observe() {
        model.teamMembers.observe(this, Observer {
            for (i in it) {
                val entity = TeamMemberItem(i.name, i.id)
                list.add(entity)
            }
        })

        model.projects.observe(this, Observer {
            for (i in it) {
                val entity = TeamMemberProjectItem(i.name,1,i.id)
                list1.add(entity)
            }
        })
    }
}