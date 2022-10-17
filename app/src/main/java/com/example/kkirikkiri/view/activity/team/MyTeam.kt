package com.example.kkirikkiri.view.activity.team

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kkirikkiri.R
import com.example.kkirikkiri.databinding.ActivityMyTeamBinding
import com.example.kkirikkiri.module.info.UserInfo
import com.example.kkirikkiri.view.activity.project.AddProjectActivity
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
        model.getTeamMember(UserInfo.teamId!!, 0)
        model.getProject(UserInfo.teamId!!, 0)

        observe()

        binding.teamTeamManage.setOnClickListener {
            startActivity(Intent(applicationContext, TeamManage::class.java).putExtra("id", id))
        }

        binding.teamMember.layoutManager = LinearLayoutManager(this)
        binding.teamMember.addItemDecoration(RecyclerDecorationHeight(30))

        binding.teamProfile.setOnClickListener { startActivity(Intent(applicationContext, TeamProfile::class.java)) }

         binding.project.layoutManager = LinearLayoutManager(this)
        binding.project.addItemDecoration(RecyclerDecorationHeight(30))

        binding.addProjectButton.setOnClickListener {
            Intent(this, AddProjectActivity::class.java).run { startActivity(this) }
        }

    }

    private fun observe() {
        model.teamMembers.observe(this) {
            for (i in it) {
                val entity = TeamMemberItem(i.name, i.id)
                list.add(entity)
                binding.teamMember.adapter = MyTeamAdapter(list)
            }
        }

        model.projects.observe(this) {
            for (i in it) {
                val entity = TeamMemberProjectItem(i.name,1,i.id)
                list1.add(entity)
                binding.project.adapter = MyTeamProjectAdapter(list1)
            }
        }
    }
}