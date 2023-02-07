package com.example.kkirikkiri.view.activity.team

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kkirikkiri.R
import com.example.kkirikkiri.databinding.ActivityMyTeamBinding
import com.example.kkirikkiri.module.info.UserInfo
import com.example.kkirikkiri.view.activity.BaseActivity
import com.example.kkirikkiri.view.activity.project.AddProjectActivity
import com.example.kkirikkiri.view.recyclerview.RecyclerDecorationHeight
import com.example.kkirikkiri.view.recyclerview.myteam.team.MyTeamAdapter
import com.example.kkirikkiri.view.recyclerview.myteam.member.MyTeamProjectAdapter
import com.example.kkirikkiri.view.recyclerview.myteam.team.TeamMemberItem
import com.example.kkirikkiri.view.recyclerview.myteam.member.TeamMemberProjectItem
import com.example.kkirikkiri.viewmodel.TeamModel
import java.util.ArrayList

class MyTeam : BaseActivity<ActivityMyTeamBinding> ({ ActivityMyTeamBinding.inflate(it) }) {

    private val model = TeamModel()

    private var list = ArrayList<TeamMemberItem>()
    private var list1 = ArrayList<TeamMemberProjectItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        list.clear()

        val intent = intent
        val id = intent.getIntExtra("id", 0)
        val name = intent.getStringExtra("name")
        model.getRole(id)
        model.getTeamMember(id, 0)
        model.getProjectDefault(id, 0)

        observe()

        binding.teamTeamManage.setOnClickListener { startActivity(Intent(applicationContext, TeamManage::class.java).putExtra("id", id)) }
        binding.toolbarTitle.text = name

        binding.teamMember.layoutManager = LinearLayoutManager(this)
        binding.teamMember.addItemDecoration(RecyclerDecorationHeight(30))

         binding.project.layoutManager = LinearLayoutManager(this)
        binding.project.addItemDecoration(RecyclerDecorationHeight(30))

        binding.addProjectButton.setOnClickListener {
            Intent(this, AddProjectActivity::class.java).run { startActivity(this) }
        }

    }

    override fun onRestart() {
        super.onRestart()
        refresh()
    }

    private fun observe() {
        model.teamMembers.observe(this) {
            for (i in it) {
                val entity = TeamMemberItem(i.name, i.id, i.role, i.approved)
                list.add(entity)
                binding.teamMember.adapter = MyTeamAdapter(list, intent, this)
            }
        }

        model.projectDefault.observe(this) {
            for (i in it) {
                val entity = TeamMemberProjectItem(i.name,0,i.id)
                list1.add(entity)
                binding.project.adapter = MyTeamProjectAdapter(list1, intent, this)
            }
        }
    }

    private fun refresh() {
        finish()
        overridePendingTransition(0,0)
        startActivity(intent)
        overridePendingTransition(0,0)
    }
}