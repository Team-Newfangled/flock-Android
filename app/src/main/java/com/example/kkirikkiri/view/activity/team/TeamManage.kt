package com.example.kkirikkiri.view.activity.team

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kkirikkiri.R
import com.example.kkirikkiri.databinding.ActivityTeamManageBinding
import com.example.kkirikkiri.view.recyclerview.RecyclerDecorationHeight
import com.example.kkirikkiri.view.recyclerview.myteam.team.MyTeamAdapter
import com.example.kkirikkiri.view.recyclerview.myteam.team.TeamMemberItem
import com.example.kkirikkiri.viewmodel.TeamModel
import java.util.ArrayList

class TeamManage : AppCompatActivity() {

    private lateinit var binding: ActivityTeamManageBinding

    private val model = TeamModel()

    private var list = ArrayList<TeamMemberItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_team_manage)

        val intent = intent
        val id = intent.getIntExtra("id", 0)

        model.getTeamMember(id, 0)

        observe()
        binding.teamManageRecyclerbiew.adapter = MyTeamAdapter(list)
        binding.teamManageRecyclerbiew.layoutManager = LinearLayoutManager(this)
        binding.teamManageRecyclerbiew.addItemDecoration(RecyclerDecorationHeight(30))


    }

    private fun observe() {
        model.teamMembers.observe(this, Observer {
            for (i in it) {
                val entity = TeamMemberItem(i.name, i.id)
                list.add(entity)
                binding.teamManageRecyclerbiew.adapter = MyTeamAdapter(list)
            }
        })
    }
}